package hali.pro.com.haliyikama.helper.interfaces;

import android.content.Context;

import java.util.LinkedList;

/**
 * Created by ramazancesur on 27/06/2017.
 */

public interface IUtility {
    String generateUnique();

    boolean internetControl(Context ctx);

    LinkedList<Class<?>> findAllClassinPackage(String packageName);
}