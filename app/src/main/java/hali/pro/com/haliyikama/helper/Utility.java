package hali.pro.com.haliyikama.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import hali.pro.com.haliyikama.helper.interfaces.IUtility;

/**
 * Created by ramazancesur on 07/05/2017.
 */

public class Utility implements IUtility {

    private static Utility newInstance;

    private Utility() {

    }

    public static Utility createInstance() {
        if (newInstance == null) {
            newInstance = new Utility();
        }
        return newInstance;
    }


    public String generateUnique() {
        String ts = String.valueOf(System.currentTimeMillis());
        String rand = UUID.randomUUID().toString();
        return new String(Hex.encodeHex(DigestUtils.sha384(ts + rand)));
    }

    public boolean internetControl(Context ctx) {
        ConnectivityManager conMgr = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

    public String firstLetterUpper(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    public Method searchMethodName(List<Method> lstMethod, final String methodName) {
        Method method = Iterables.find(lstMethod, new Predicate<Method>() {
            @Override
            public boolean apply(Method input) {
                input.setAccessible(true);
                return input.getName().equals("set" + firstLetterUpper(methodName));
            }
        });
        return method;
    }

    public LinkedList<Class<?>> findAllClassinPackage(String packageName) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        return new LinkedList<>(classes);
    }

}
