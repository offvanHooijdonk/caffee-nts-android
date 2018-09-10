package by.nts.cafe.app.presentation.presenter

import android.content.Context
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.locator.ServiceLocator
import by.nts.cafe.app.locator.locateService
import by.nts.cafe.app.network.TableClient
import by.nts.cafe.app.presentation.presenter.tables.TableListPresenter
import by.nts.cafe.app.presentation.ui.tables.ITableListView

typealias PF = PresenterFactory

class PresenterFactory private constructor() {
    private lateinit var context: Context

    companion object {
        val instance by lazy { PresenterFactory() }
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun getTableListPresenter(view: ITableListView) = TableListPresenter(view,
            locateService(TableDao::class.java),
            locateService(TableClient::class.java))
}
