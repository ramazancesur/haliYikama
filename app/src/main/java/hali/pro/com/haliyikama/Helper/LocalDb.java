package hali.pro.com.haliyikama.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

import hali.pro.com.haliyikama.Annotation.IliskiProcessor;

/**
 * Created by ramazancesur on 07/05/2017.
 */

public class LocalDb extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "hali_db";//database adı
    public static SQLiteDatabase db;
    public static Context context;
    public static Cursor cursor;
    private Utility utility;
    private IliskiProcessor iliskiProcessor;


    public LocalDb(Context c) {
        super(c, DATABASE_NAME , null, DATABASE_VERSION);

        context = c;
        utility = new Utility();
        iliskiProcessor = new IliskiProcessor();

    }


    private Object GetObjectFromCursor(Class<?> tipo, Cursor cursor) throws Exception {
        Field[] fields = tipo.getFields();
        Object o = tipo.newInstance();
        for (int i = 0; i < fields.length; i++) {
            Object fieldValue = GetCursorFieldValue(cursor, i);
            if (fieldValue != null) {
                o = SetUnknownFieldValue(o, cursor.getColumnName(i), fieldValue);
            }
        }
        return o;
    }

    private Object SetUnknownFieldValue(Object object, String fieldName, Object fieldValue) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object fieldCasted = CastField(field.getType(), fieldValue);
        field.set(object, fieldCasted);
        return object;

    }


    private Object CastField(Class fieldType, Object fieldValue) throws Exception {
        switch (fieldType.getSimpleName().toLowerCase()) {
            case "boolean":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(fieldValue.getClass().getSimpleName(), "String")) {
                        return Objects.equals(fieldValue, "true");
                    }
                } else {
                    if (fieldValue.getClass().getSimpleName().equals("String")) {
                        return fieldValue.equals("true");
                    }
                }
                break;
            case "double":
                if (fieldValue == null) {
                    return null;
                }
                return (double) Float.parseFloat(fieldValue.toString());
            case "date":
                return new Date((long) fieldValue);
            case "uuid":
                return UUID.fromString((String) fieldValue);
            default:
                return fieldValue;

        }
        return fieldValue;
    }

    public ArrayList<?> SelectAll(Class<?> type, String whereClause) {
        if (whereClause == null) {
            whereClause = "";
        }
        String query = "select * from " + type.getSimpleName() + " " + whereClause;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList list = new ArrayList();
        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Object o = GetObjectFromCursor(type, cursor);
                    list.add(o);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            return list;
        } catch (Exception ex) {
            return null;
        }
    }


    private Object GetCursorFieldValue(Cursor cursor, int i) {
        switch (cursor.getType(i)) {
            /*  FIELD_TYPE_NULL
                FIELD_TYPE_INTEGER
                FIELD_TYPE_FLOAT
                FIELD_TYPE_STRING
                FIELD_TYPE_BLOB  */
            case 0:
                return null;
            case 1:
                return cursor.getInt(i);
            case 2:
                return cursor.getFloat(i);
            case 3:
                return cursor.getString(i);
            case 4:
                return cursor.getBlob(i);
            default:
                cursor.close();
                return null;
        }
    }


    private <T extends BaseDTO> T staticData(T data) {
        data.setCreatedDate(new Date());
        data.setEntityState(EnumUtil.EntityState.ACTIVE);
        data.setUpdatedDate(new Date());
        data.setOid(Utility.generateUnique());
        return data;
    }

    public <T extends BaseDTO> boolean insertData(T data) {
        db = this.getWritableDatabase();
        data = staticData(data);
        //we build the query for each object
        String insertQuery = "insert into " + data.getClass().getSimpleName() + "(";
        try {
            ArrayList<Pair<String, Object>> name_value = GetFieldNameValue(data);

            String tableNames = "";
            String tableValues = "";
            //for each record we add the values and the field names
            for (Pair<String, Object> pair : name_value) {
                tableNames += pair.first + ",";
                tableValues += "'" + pair.second.toString() + "'" + ",";
            }

            //remove the last comma
            tableNames = tableNames.substring(0, tableNames.length() - 1);
            tableValues = tableValues.substring(0, tableValues.length() - 1);

            //finished adjusting query
            insertQuery += tableNames + ")values(" + tableValues + ");";
            db.execSQL(insertQuery);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public <T extends BaseDTO> boolean UpdateObject(T objToUpdate) {
        objToUpdate.setEntityState(EnumUtil.EntityState.ACTIVE);
        objToUpdate.setUpdatedDate(new Date());
        try {
            Field field = objToUpdate.getClass().getField("id");
            int id = (int) field.get(objToUpdate);
            String whereClause = "where id = " + id;

            String sqlQuery = "update " + objToUpdate.getClass().getSimpleName() + " set ";
            ArrayList<Pair<String, Object>> name_value = GetFieldNameValue(objToUpdate);

            //for each field we add name and value
            for (Pair<String, Object> pair : name_value) {
                if (!pair.first.equals("id")) {
                    sqlQuery += pair.first + "=";
                    sqlQuery += "'" + pair.second.toString() + "'" + ",";
                }
            }

            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1);

            sqlQuery += " ";
            sqlQuery += whereClause;

            db.execSQL(sqlQuery);
            return true;


        } catch (Exception ex) {
            return false;
        }
    }

    private ArrayList<Pair<String, Object>> GetFieldNameValue(Object object) throws Exception {
        Field[] fields = object.getClass().getFields();
        ArrayList<Pair<String, Object>> pairs = new ArrayList<>();
        for (Field f : fields) {
            Object value = GetUnknownObjectFieldValue(f, object);
            String fieldName = f.getName();

            if (value == null || fieldName.isEmpty()) {
                continue;
            }
            pairs.add(new Pair(fieldName, value));
        }
        return pairs;
    }

    //returns from an object and a field name, the value
    private Object GetUnknownObjectFieldValue(Field field, Object object) throws Exception {
        field.setAccessible(true);
        Object o = field.get(object);

        //we save dates as longs
        Date date = new Date();
        UUID uuid = UUID.randomUUID();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (o != null && Objects.equals(o.getClass(), date.getClass())) {
                return new Date((long) o);
            }
            if (o != null && Objects.equals(o.getClass(), uuid.getClass())) {
                return o.toString();
            }
        } else {
            if (o != null && o.getClass().equals(date.getClass())) {
                return new Date((long) o);
            }
            if (o != null && o.getClass().equals(uuid.getClass())) {
                return o.toString();
            }
        }
        return o;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LinkedList<Class<?>> lstClass = utility.findAllClassinPackage("hali.pro.com.haliyikama.DTO");
        for (Class clazz : lstClass) {
            db.execSQL(iliskiProcessor.queryCreate(clazz));
        }
    }

    public boolean DeleteAll(Class<?> type) {
        String sql = "delete from " + type.getSimpleName();
        db.execSQL(sql);
        return true;
    }

    //select a record from id
    public Object SelectById(Class<?> type, UUID id) {

        String query = "select * from " + type.getSimpleName() + " where id='" + id.toString() + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            try {
                Object object = GetObjectFromCursor(type, cursor);
                cursor.close();
                return object;
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // İlk olarak servisi çağır daha sonra db kaydını al yeni bir database oluştur
        LinkedList<Class<?>> lstClass = utility.findAllClassinPackage("hali.pro.com.haliyikama.DTO");
        for (Class clazz : lstClass) {
            db.execSQL("DROP TABLE IF EXISTS " + clazz.getSimpleName());
        }
        onCreate(db);
    }



}
