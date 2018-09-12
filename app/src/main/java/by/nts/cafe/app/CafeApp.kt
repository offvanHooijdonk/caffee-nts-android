package by.nts.cafe.app

import android.app.Application
import android.preference.PreferenceManager
import by.nts.cafe.app.dao.AppDatabase
import by.nts.cafe.app.helper.rx.attachTo
import by.nts.cafe.app.helper.rx.schedulersIOFlowable
import by.nts.cafe.app.locator.ServiceLocator
import by.nts.cafe.app.model.db.HallModel
import by.nts.cafe.app.presentation.presenter.PresenterFactory
import io.reactivex.disposables.CompositeDisposable

class CafeApp : Application() {
    companion object {
        const val LOG = "cafe-nts-log"

        lateinit var appDatabase: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        PreferenceManager.setDefaultValues(this, R.xml.pref, false)
        appDatabase = AppDatabase.buildDatabase(applicationContext)

        PresenterFactory.instance.setContext(applicationContext)
        ServiceLocator.instance.setContext(applicationContext)


        initDBIfEmpty();
    }

    private fun initDBIfEmpty() {
        val compositeDisposable = CompositeDisposable()
        ServiceLocator.instance.getHallDao().all
                .doOnNext { list ->
                    if (list.isEmpty()) {
                        AppDatabase.initTempData(appDatabase)
                    }
                }
                .compose<List<HallModel>>(schedulersIOFlowable<List<HallModel>>())
                .subscribe { compositeDisposable.dispose() }
                .attachTo(compositeDisposable)
    }

}
