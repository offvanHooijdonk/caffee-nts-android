package by.nts.cafe.app.presentation.ui.order

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.nts.cafe.app.R
import by.nts.cafe.app.model.db.OrderItemModel
import kotlinx.android.synthetic.main.item_dish_in_order.view.*
import java.text.NumberFormat

class OrderAdapter(private val ctx: Context, private val orderItemList: List<OrderItemModel>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dish_in_order, parent, false))
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val orderItem = orderItemList[position]
        val dish = orderItem.dish

        vh.itemView.txtDishName!!.text = dish.name
        vh.itemView.txtDishPrice!!.text = NumberFormat.getCurrencyInstance().format(dish.price.toDouble())
        vh.itemView.txtAmount!!.text = "1"
    }

    override fun getItemCount(): Int {
        return orderItemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
