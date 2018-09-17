package by.nts.cafe.app.presentation.ui.menu

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.nts.cafe.app.R
import by.nts.cafe.app.model.db.DishModel
import kotlinx.android.synthetic.main.item_dish_add.view.*
import java.text.NumberFormat

class MenuAdapter(private val ctx: Context, private val dishList: List<DishModel>, private val listener: (DishModel) ->  Unit) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    init {
        lastCheckedPosition = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dish_add, parent, false))
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val dish = dishList[position]

        with(vh.itemView) {
            txtDishName.text = dish.name
            txtDishDescription.text = dish.description
            txtDishPrice.text = NumberFormat.getCurrencyInstance().format(dish.price.toDouble())

            root.isSelected = lastCheckedPosition == position

            root.setOnClickListener { _ ->
                listener(dish)
                lastCheckedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return dishList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private var lastCheckedPosition: Int = 0
    }

}
