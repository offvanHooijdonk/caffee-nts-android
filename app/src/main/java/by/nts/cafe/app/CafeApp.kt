package by.nts.cafe.app

import android.app.Application
import android.preference.PreferenceManager

import by.nts.cafe.app.dao.AppDatabase
import by.nts.cafe.app.helper.rx.Transformers
import by.nts.cafe.app.locator.ServiceLocator
import by.nts.cafe.app.model.db.HallModel
import by.nts.cafe.app.presentation.presenter.PresenterFactory
import io.reactivex.disposables.Disposable

class CafeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        PreferenceManager.setDefaultValues(this, R.xml.pref, false)
        appDatabase = AppDatabase.buildDatabase(applicationContext)

        PresenterFactory.instance.setContext(applicationContext)
        ServiceLocator.instance.setContext(applicationContext)
        initDBIfEmpty();
    }


    fun initDBIfEmpty() {
        val d = appDatabase.hallDao().all
                .doOnSuccess { list ->
                    if (list.isEmpty()) {
                        AppDatabase.initTempData(appDatabase)
                    }
                }
                .compose<List<HallModel>>(Transformers.schedulersIOMaybe<List<HallModel>>())
                .subscribe()
    }

    companion object {
        const val LOG = "cafe-nts-log"

        lateinit var appDatabase: AppDatabase// = null
            private set
    }

}
