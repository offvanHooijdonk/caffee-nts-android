package by.nts.cafe.app.presentation.ui.halls

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.nts.cafe.app.R
import by.nts.cafe.app.model.db.HallModel
import kotlinx.android.synthetic.main.item_hall.view.*

class HallsAdapter(private val ctx: Context, private val halls: List<HallModel>, private val listener: (HallModel) -> Unit) : RecyclerView.Adapter<HallsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_hall, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val hall = halls[position]

        vh.itemView.txtHallName.text = hall.name
        vh.itemView.cardRoot.setOnClickListener { _ -> listener(hall) }
    }

    override fun getItemCount(): Int {
        return halls.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
