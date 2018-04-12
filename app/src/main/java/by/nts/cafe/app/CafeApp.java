package by.nts.cafe.app;

import android.app.Application;
import android.preference.PreferenceManager;

import by.nts.cafe.app.dao.AppDatabase;

public class CafeApp extends Application {
    public static final String LOG = "cafe-nts-log";

    private static AppDatabase APP_DB;

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.pref, false);
        APP_DB = AppDatabase.buildDatabase(getApplicationContext());
    }

    public static AppDatabase getAppDatabase() {
        return APP_DB;
    }

}
