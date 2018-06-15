package by.nts.cafe.app.presentation.presenter.tables

import android.content.Context
import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.helper.rx.Transformsers
import by.nts.cafe.app.model.db.TableModel
import by.nts.cafe.app.network.NetworkClientFactory
import by.nts.cafe.app.presentation.ui.tables.ITablesView
import io.reactivex.disposables.CompositeDisposable

class TablesPresenter(var view: ITablesView?, var ctx: Context) {
    private val cd: CompositeDisposable = CompositeDisposable()

    fun loadTableList(hallId: String) {
        CafeApp.getAppDatabase().tableDao()
                .getAll(hallId)
                .compose(Transformsers.schedulersIOMaybe())
                .subscribe({ list -> this.tablesLoaded(list) }, { th -> view?.handleError(th) })
                .also { cd.add(it) }
    }

    fun updateTablesList(hallId: String) {
        view?.showRefreshing(true)
        NetworkClientFactory.getTableClient(ctx).getTables(hallId)
                .doOnNext { list -> CafeApp.getAppDatabase().tableDao().saveAll(list) }
                .compose(Transformsers.schedulersIO())
                .subscribe({list -> this.tablesLoaded(list)}, { th -> view?.handleError(th) })
                .also { cd.add(it) }
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