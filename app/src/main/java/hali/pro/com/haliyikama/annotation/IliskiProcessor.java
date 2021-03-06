package hali.pro.com.haliyikama.annotation;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ramazancesur on 09/05/2017.
 */

public class IliskiProcessor {

    // Bu bölümde sadece
    public String queryCreate(Class clazz) {
        List<String> foreingKeyName = new LinkedList<>();

        String createQuery = "";
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals("oid")) {
                createQuery = "CREATE TABLE " + clazz.getSimpleName() + " (Oid varchar(100) PRIMARY KEY NOT NULL ";
            }
            f.setAccessible(true);
            IliskiAnnotation fAnnotation = f.getAnnotation(IliskiAnnotation.class);

            if (fAnnotation != null) {
                createQuery += " , " + f.getType().getSimpleName() + "_Oid  varchar(100) ";
            }
            createQuery += " , " + f.getName() + " varchar(2000) ";
        }
        createQuery += ")";
        return createQuery;
    }
}
