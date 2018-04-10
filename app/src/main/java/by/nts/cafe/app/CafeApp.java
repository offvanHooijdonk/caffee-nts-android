package by.nts.cafe.app;

import android.app.Application;
import android.preference.PreferenceManager;

public class CafeApp extends Application {
    public static final String LOG = "cafe-nts-log";

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.pref, false);
    }

}
