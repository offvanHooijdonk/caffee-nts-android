package by.nts.cafe.app.presentation.presenter.halls

import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.dao.HallDao
import by.nts.cafe.app.helper.rx.attachTo
import by.nts.cafe.app.helper.rx.schedulersIOFlowable
import by.nts.cafe.app.helper.rx.schedulersIOSingle
import by.nts.cafe.app.locator.locateService
import by.nts.cafe.app.model.db.HallModel
import by.nts.cafe.app.network.HallClient
import by.nts.cafe.app.presentation.ui.halls.IHallsView
import io.reactivex.disposables.CompositeDisposable

class HallsPresenter(private var view: IHallsView?, private val hallClient: HallClient, private val hallDao: HallDao) {
    private val cd: CompositeDisposable = CompositeDisposable()

    fun loadHalls() {
        CafeApp.appDatabase.hallDao().all
                .compose<List<HallModel>>(schedulersIOFlowable<List<HallModel>>())
                .doOnNext { view!!.showUpdateProcess(false) }
                .subscribe({ view!!.onHallsLoaded(it) }, { view!!.onError(it) })
                .attachTo(cd)
    }

    fun updateHalls() {
        view?.showUpdateProcess(true)

        locateService(HallClient::class.java).halls
                .doOnSuccess { list -> CafeApp.appDatabase.hallDao().saveAll(list) }
                .compose<List<HallModel>>(schedulersIOSingle<List<HallModel>>())
                .subscribe({ view?.onHallsLoaded(it) }, { view?.onError(it) })
                .attachTo(cd)
    }

    fun detach() {
        view = null
        cd.addAll()
    }
}
