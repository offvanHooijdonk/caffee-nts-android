package by.nts.cafe.app.presentation.ui.halls

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.R
import by.nts.cafe.app.helper.UIHelper
import by.nts.cafe.app.model.db.HallModel
import by.nts.cafe.app.presentation.presenter.PresenterFactory
import by.nts.cafe.app.presentation.presenter.halls.HallsPresenter
import by.nts.cafe.app.presentation.ui.pref.PreferenceActivity
import by.nts.cafe.app.presentation.ui.tables.TableListActivity
import kotlinx.android.synthetic.main.activity_halls.*
import java.util.*

class HallsActivity : AppCompatActivity(), IHallsView {
    // region declarations
    private lateinit var presenter: HallsPresenter

    private lateinit var adapter: HallsAdapter
    private val hallList = ArrayList<HallModel>()
    // endregion

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halls)

        presenter = PresenterFactory.instance.getHallsPresenter(this)
        adapter = HallsAdapter(this, hallList, this::onHallPicked)
        rvHalls.adapter = adapter
        rvHalls.layoutManager = GridLayoutManager(this, 4) // TODO remove magic number

        UIHelper.setupRefreshLayout(rflHalls)
        rflHalls.setOnRefreshListener { presenter.updateHalls() }
    }

    override fun onStart() {
        super.onStart()

        presenter.loadHalls()
    }

    // region Lifecycle
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_settings) {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detach()
    }
    //endregion

    // region Callbacks
    private fun onHallPicked(hallModel: HallModel) {
        startActivity(Intent(this, TableListActivity::class.java)
                .putExtra(TableListActivity.EXTRA_HALL_ID, hallModel.id)
                .putExtra(TableListActivity.EXTRA_HALL_NAME, hallModel.name)
        )
    }
    // endregion

    // region Interaction
    override fun onHallsLoaded(halls: List<HallModel>) {
        hallList.clear()
        hallList.addAll(halls)
        adapter.notifyDataSetChanged()
    }

    override fun onError(th: Throwable) {
        rflHalls.isRefreshing = false
        Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show()
        Log.i(CafeApp.LOG, "Error getting halls.", th)
    }

    override fun showUpdateProcess(isShow: Boolean) {
        rflHalls.isRefreshing = isShow
    }
    // endregion
}
