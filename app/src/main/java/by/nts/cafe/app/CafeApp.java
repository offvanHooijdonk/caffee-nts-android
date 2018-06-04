package by.nts.cafe.app;

import android.app.Application;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import by.nts.cafe.app.dao.AppDatabase;
import by.nts.cafe.app.helper.rx.Transformsers;
import by.nts.cafe.app.model.db.HallModel;
import io.reactivex.disposables.Disposable;

public class CafeApp extends Application {
    public static final String LOG = "cafe-nts-log";

    private static AppDatabase APP_DB;

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.pref, false);
        APP_DB = AppDatabase.buildDatabase(getApplicationContext());

        initDBIfEmpty();
    }


    public void initDBIfEmpty() {
        Disposable d = getAppDatabase().hallDao().getAll()
                .doOnSuccess(list -> {
                    if (list.isEmpty()) {
                        List<HallModel> hallModels = new ArrayList<>();
                        hallModels.add(new HallModel("1", "Bowling Hall"));
                        hallModels.add(new HallModel("51", "Primary Hall"));
                        hallModels.add(new HallModel("985", "Hookah Hall"));
                        hallModels.add(new HallModel("6233", "Bar Hall"));
                        hallModels.add(new HallModel("63", "Hall #2"));

                        getAppDatabase().hallDao().saveAll(hallModels);
                    }
                })
                .compose(Transformsers.schedulersIOMaybe())
                .subscribe();
    }

    public static AppDatabase getAppDatabase() {
        return APP_DB;
    }

}
