package hali.pro.com.haliyikama.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;

import hali.pro.com.haliyikama.Annotation.IliskiProcessor;

/**
 * Created by ramazancesur on 07/05/2017.
 */

public class LocalDb extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "sqllite_database";//database adı
    public static SQLiteDatabase db;
    public static Context context;
    public static Cursor cursor;
    private Utility utility;
    private IliskiProcessor iliskiProcessor;


    public LocalDb(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = c;
        utility = new Utility();
        iliskiProcessor = new IliskiProcessor();

    }


    public Cursor select(String s) throws SQLException {
        db = this.getReadableDatabase();      //database sorguları yapılıyor.Cursor e atılıp oradan veriler okunuyor.
        cursor = db.rawQuery(s, null);
        return cursor;
    }

    private <T extends BaseDTO<BaseDTO>> ContentValues staticData(T data) {
        ContentValues contentValues = new ContentValues();
        data.setCreatedDate(new Date());
        data.setEntityState(EnumUtil.EntityState.ACTIVE);
        data.setUpdatedDate(new Date());
        data.setOid(Utility.generateUnique());
        return contentValues;
    }

    public <T extends BaseDTO<BaseDTO>> T insertData(T data) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Field field : data.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value instanceof String) {
                contentValues.put(field.getName(), (String) value);
            } else {
                contentValues.put(field.getName(), String.valueOf(value));
            }
        }
        db.insert(data.getClass().getSimpleName(), null, contentValues);
        return data;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        LinkedList<Class<?>> lstClass = utility.findAllClassinPackage("hali.pro.com.haliyikama.DTO");
        for (Class clazz : lstClass) {
            db.execSQL(iliskiProcessor.queryCreate(clazz));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LinkedList<Class<?>> lstClass = utility.findAllClassinPackage("hali.pro.com.haliyikama.DTO");
        for (Class clazz : lstClass) {
            db.execSQL(iliskiProcessor.queryCreate(clazz));
        }
    }
}
