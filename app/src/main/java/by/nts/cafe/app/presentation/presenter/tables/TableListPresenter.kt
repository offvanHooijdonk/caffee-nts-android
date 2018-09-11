package by.nts.cafe.app.presentation.presenter.tables

import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.helper.rx.attachTo
import by.nts.cafe.app.helper.rx.schedulersIOFlowable
import by.nts.cafe.app.helper.rx.schedulersIOSingle
import by.nts.cafe.app.model.db.TableModel
import by.nts.cafe.app.network.TableClient
import by.nts.cafe.app.presentation.ui.tables.ITableListView
import io.reactivex.disposables.CompositeDisposable

class TableListPresenter(var view: ITableListView?, var tableDao: TableDao, var tableClient: TableClient) {
    private val cd = CompositeDisposable()

    /**
     * Loads a list from DB
     */
    fun loadTableList(hallId: String) {
        tableDao.getAll(hallId)
                .compose(schedulersIOFlowable())
                .doOnNext { view?.showRefreshing(false) }
                .subscribe({ list -> this.tablesLoaded(list) }, { th -> view?.handleError(th) }) // todo use local method and switch refresh progress off
                .attachTo(cd)
    }

    /**
     * Updates the recent data from server
     */
    fun updateTablesList(hallId: String) {
        view?.showRefreshing(true)

        tableClient.getTables(hallId)
                .doOnSuccess { list -> CafeApp.appDatabase.tableDao().saveAll(list) }
                .compose(schedulersIOSingle())
                .subscribe({ /*list -> this.tablesLoaded(list)*/ }, { th -> view?.handleError(th) }) // must update automatically due to Flowable usage
                .attachTo(cd)
    }

    fun detachView() {
        cd.dispose()
        view = null
    }

    private fun tablesLoaded(list: List<TableModel>) {
        view?.onTablesLoaded(list)
    }
}