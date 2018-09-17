package by.nts.cafe.app.presentation.ui.menu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.nts.cafe.app.R
import by.nts.cafe.app.model.db.DishModel
import kotlinx.android.synthetic.main.frag_menu_pick.*
import java.util.*

class MenuFragment : Fragment() {
    lateinit var dishSelected: (DishModel) -> Any

    private val dishList = ArrayList<DishModel>()
    private var adapter: MenuAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_menu_pick, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initData()
        adapter = MenuAdapter(context!!, dishList, this::onDishSelect)
        rvMenu.adapter = adapter
        rvMenu.layoutManager = LinearLayoutManager(context)
    }

    @Deprecated("")
    private fun initData() {
        dishList.clear()
        dishList.add(DishModel("1", "Pasta Blah-blah-oza", 12.49f, getString(R.string.fish_dish_descr)))
        dishList.add(DishModel("41", "Sauce salsa", 1.00f, getString(R.string.fish_dish_descr)))
        dishList.add(DishModel("641", "Orange fresh", 2.99f, getString(R.string.fish_dish_descr)))
        dishList.add(DishModel("971", "Cheese cake strawberry Alberto", 5.20f, getString(R.string.fish_dish_descr)))
        dishList.add(DishModel("72", "Cappuccino small", 2.00f, getString(R.string.fish_dish_descr)))
        dishList.add(DishModel("72", "Draniki with mushrooms", 2.00f, getString(R.string.fish_dish_descr)))
        dishList.add(DishModel("5", "Cigarettes 'Belomor'", 6.80f, getString(R.string.fish_dish_descr)))
    }

    private fun onDishSelect(dishModel: DishModel) {
        (parentFragment as OnDishSelectedListener).onDishSelect(dishModel)
        dishSelected(dishModel)
    }

    interface OnDishSelectedListener {
        fun onDishSelect(dishModel: DishModel)
    }
}
