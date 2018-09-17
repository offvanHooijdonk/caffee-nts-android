package by.nts.cafe.app.presentation.ui.menu

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import by.nts.cafe.app.R
import by.nts.cafe.app.model.db.DishModel
import kotlinx.android.synthetic.main.dialog_dish_add.*

class AddDishDialogFragment : DialogFragment(), MenuFragment.OnDishSelectedListener {

    //region Declarations
    private var dishSelected: DishModel? = null
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme_DishDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_dish_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return MenuFragment()
            }

            override fun getCount(): Int {
                return 6
            }
        }
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        tabs.addTab(tabs.newTab().setText("First"))
        tabs.addTab(tabs.newTab().setText("Soups"))
        tabs.addTab(tabs.newTab().setText("Drinks"))
        tabs.addTab(tabs.newTab().setText("Alcohol"))
        tabs.addTab(tabs.newTab().setText("Deserts"))
        tabs.addTab(tabs.newTab().setText("Misc."))
        fabAddDish.setOnClickListener { addDishToOrder() }
    }

    override fun onStart() {
        super.onStart()

        if (dialog != null) {
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.window!!.setLayout(context!!.resources.getDimensionPixelOffset(R.dimen.dialog_dish_width),
                    WindowManager.LayoutParams.MATCH_PARENT)
        }
    }

    fun addDishToOrder() {
        // todo call for presenter

        dishSelected = null // call when saved and hide the block
    }

    override fun onDishSelect(dishModel: DishModel) {
        dishSelected = dishModel
        blockAmount!!.visibility = View.VISIBLE
        txtDishName!!.text = dishModel.name
    }
}
