package hali.pro.com.haliyikama;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by ramazancesur on 02/05/2017.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

}