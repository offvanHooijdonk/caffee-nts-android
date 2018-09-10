package by.nts.cafe.app;

import android.app.Application;
import android.preference.PreferenceManager;

import by.nts.cafe.app.dao.AppDatabase;
import by.nts.cafe.app.helper.rx.Transformers;
import io.reactivex.disposables.Disposable;

public class CafeApp extends Application {
    public static final String LOG = "cafe-nts-log";

    private static AppDatabase APP_DB;

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.pref, false);
        APP_DB = AppDatabase.buildDatabase(getApplicationContext());

        //initDBIfEmpty();
    }


    public void initDBIfEmpty() {
        Disposable d = getAppDatabase().hallDao().getAll()
                .doOnSuccess(list -> {
                    if (list.isEmpty()) {
                        AppDatabase.initTempData(getAppDatabase());
                    }
                })
                .compose(Transformers.schedulersIOMaybe())
                .subscribe();
    }

    public static AppDatabase getAppDatabase() {
        return APP_DB;
    }

}
