package by.nts.cafe.app.presentation.ui.tables

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import by.nts.cafe.app.R

class TableActivity : AppCompatActivity() {

    @BindView(R.id.root)
    internal var root: View? = null
    @BindView(R.id.blockNotifications)
    internal var blockNotifications: View? = null
    @BindView(R.id.blockSummary)
    internal var blockSummary: View? = null
    @BindView(R.id.blockMenu)
    internal var blockMenu: View? = null
    @BindView(R.id.fabMenu)
    internal var fabMenu: FloatingActionButton? = null

    private var isMenuShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        ButterKnife.bind(this)
    }

    @OnClick(R.id.fabMenu)
    fun onMenuClick() {
        if (isMenuShown) {
            blockMenu!!.layoutParams.width = 0
            blockNotifications!!.visibility = View.VISIBLE
            blockSummary!!.visibility = View.VISIBLE
            //blockMenu.setVisibility(View.GONE);

            fabMenu!!.setImageDrawable(resources.getDrawable(R.drawable.ic_menu_24))
        } else {
            //blockMenu.setVisibility(View.VISIBLE);
            blockMenu!!.layoutParams.width = root!!.width / 2
            blockNotifications!!.visibility = View.GONE
            blockSummary!!.visibility = View.GONE

            fabMenu!!.setImageDrawable(resources.getDrawable(R.drawable.ic_close_24))
        }

        isMenuShown = !isMenuShown
    }

}
