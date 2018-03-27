package hali.pro.com.haliyikama.helper;

/**
 * Created by ramazancesur on 23/06/2017.
 */

public class Settings {
    private static final String serverUrl = "http://192.168.1.10:8080/haliYikama";
    //private static final String serverUrl = "http://192.168.1.44:8080/haliYikama";
    // private static final String serverUrl = "http://192.168.43.213:8090/haliYikama";
    //private static final String serverUrl = "http://192.168.120.119:8080/haliYikama";


    public static String getServerUrl() {
        return serverUrl;
    }
}