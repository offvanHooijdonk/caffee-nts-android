package by.nts.cafe.app.presentation.ui.order

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.R
import by.nts.cafe.app.helper.rx.schedulersIOMaybe
import by.nts.cafe.app.model.db.OrderItemModel
import kotlinx.android.synthetic.main.frag_order.*
import java.util.*

class OrderFragment : Fragment(), IOrderView {

    private val dishList = ArrayList<OrderItemModel>()
    private var adapter: OrderAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OrderAdapter(activity!!, dishList)
        rvOrder!!.layoutManager = LinearLayoutManager(activity)
        rvOrder!!.adapter = adapter

        onOrderLoaded()
    }

    private fun onOrderLoaded() {
        iniData()
        txtEmpty!!.visibility = if (dishList.isEmpty()) View.VISIBLE else View.GONE
        rvOrder!!.visibility = if (dishList.isEmpty()) View.GONE else View.VISIBLE
        adapter!!.notifyDataSetChanged()
    }

    @Deprecated("")
    private fun iniData() {
        dishList.clear()
        CafeApp.appDatabase.orderDao().findByTable(1)
                .compose(schedulersIOMaybe())
                .subscribe({ orderItems ->
                    dishList.addAll(orderItems.items)
                    adapter!!.notifyDataSetChanged()
                }, { th -> Toast.makeText(activity, th.toString(), Toast.LENGTH_LONG).show() })
        /*dishList.add(new DishModel("1", "Pasta Blah-blah-oza", 12.49f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("41", "Sauce salsa", 1.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("641", "Orange fresh", 2.99f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("971", "Cheese cake strawberry Alberto", 5.20f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("72", "Cappuccino small", 2.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("5", "Cigarettes 'Belomor'", 6.80f, getString(R.string.fish_dish_descr)));*/
    }

    companion object {
        private val TAG_DIALOG_MENU = "dialog_menu"
    }
}
