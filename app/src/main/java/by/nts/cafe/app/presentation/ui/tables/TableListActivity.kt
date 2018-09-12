package by.nts.cafe.app.presentation.ui.tables

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import by.nts.cafe.app.R
import by.nts.cafe.app.helper.UIHelper
import by.nts.cafe.app.model.db.TableModel
import by.nts.cafe.app.presentation.presenter.PF
import by.nts.cafe.app.presentation.presenter.tables.TableListPresenter
import kotlinx.android.synthetic.main.activity_tables.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.longSnackbar

/**
 * Created by Yahor_Fralou on 9/10/2018 5:16 PM.
 */
class TableListActivity : AppCompatActivity(), ITableListView {
    companion object {
        const val EXTRA_HALL_ID = "EXTRA_HALL_ID"
        const val EXTRA_HALL_NAME = "EXTRA_HALL_NAME"
    }

    private var hallId: String? = null
    private lateinit var adapter: TablesAdapter
    private val tableList = mutableListOf<TableModel>()
    private lateinit var presenter: TableListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        presenter = PF.instance.getTableListPresenter(this)

        hallId = intent.getStringExtra(EXTRA_HALL_ID)

        if (hallId == null) {
            showNoHall()
        } else {
            initTitle()
            initList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onTablesLoaded(list: List<TableModel>) {
        tableList.clear()
        tableList.addAll(list)

        adapter.notifyDataSetChanged()
        showEmptyListView(tableList.isEmpty())
    }

    override fun handleError(th: Throwable) {
        Toast.makeText(this, th.message, Toast.LENGTH_LONG).show() // TODO use for debug only
        longSnackbar(rvTables, getString(R.string.error_load_data))
    }

    private fun onTablePicked(tableModel: TableModel) {
        startActivity(Intent(this, TableActivity::class.java))
    }

    override fun showRefreshing(isShow: Boolean) {
        refreshTables.isRefreshing = isShow
    }

    private fun initList() {
        adapter = TablesAdapter(this, tableList, this::onTablePicked)
        rvTables.adapter = adapter
        rvTables.layoutManager = GridLayoutManager(this, 3) // TODO make number configurable/adaptive

        refreshTables.setOnRefreshListener { presenter.updateTablesList(hallId!!) }
        UIHelper.setupRefreshLayout(refreshTables) // todo move to functions

        presenter.loadTableList(hallId!!)
    }

    private fun initTitle() {
        val hallName: String? = intent.getStringExtra(EXTRA_HALL_NAME)
        supportActionBar?.title = hallName ?: getString(R.string.activity_title_tables)
        actionBar?.title = hallName ?: getString(R.string.activity_title_tables)
    }

    private fun showEmptyListView(isShow: Boolean) {
        // todo
    }

    private fun showNoHall() {
        alert {
            titleResource = R.string.error_title_hall_not_picked
            messageResource = R.string.error_msg_hall_not_picked
            positiveButton(android.R.string.ok, onClicked = { it.dismiss() })
            isCancelable = true
        }.show()
        refreshTables.isEnabled = false
    }
}