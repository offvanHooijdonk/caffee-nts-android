package by.nts.cafe.app.presentation.ui.tables

import by.nts.cafe.app.model.db.TableModel

interface ITableListView {
    fun handleError(th: Throwable)

    fun onTablesLoaded(list: List<TableModel>)

    fun showRefreshing(isShow: Boolean)
}
