package by.nts.cafe.app.presentation.presenter.tables

import android.content.Context
import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.helper.rx.Transformers
import by.nts.cafe.app.helper.rx.attachTo
import by.nts.cafe.app.model.db.TableModel
import by.nts.cafe.app.network.NetworkClientFactory
import by.nts.cafe.app.presentation.ui.tables.ITableListView
import io.reactivex.disposables.CompositeDisposable

class TableListPresenter(var view: ITableListView?, var ctx: Context) {
    private val cd: CompositeDisposable = CompositeDisposable()

    /**
     * Loads a list from DB
     */
    fun loadTableList(hallId: String) {
        CafeApp.getAppDatabase().tableDao()
                .getAll(hallId)
                .compose(Transformers.schedulersIOFlowable())
                .subscribe({ list -> this.tablesLoaded(list) }, { th -> view?.handleError(th) })
                .attachTo(cd)
    }

    /**
     * Updates the recent data from server
     */
    fun updateTablesList(hallId: String) {
        view?.showRefreshing(true)
        NetworkClientFactory.getTableClient(ctx).getTables(hallId)
                .doOnNext { list -> CafeApp.getAppDatabase().tableDao().saveAll(list) }
                .compose(Transformers.schedulersIO())
                .subscribe({/*list -> this.tablesLoaded(list)*/}, { th -> view?.handleError(th) }) // must update automatically due to Flowable usage
                .attachTo(cd)
    }

    fun detachView() {
        cd.dispose()
        view = null
    }

    private fun tablesLoaded(list: List<TableModel>) {
        view?.showRefreshing(false)
        view?.onTablesLoaded(list)
    }
}