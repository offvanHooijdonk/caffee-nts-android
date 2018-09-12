package by.nts.cafe.app.presentation.ui.tables

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.nts.cafe.app.R
import by.nts.cafe.app.helper.UIHelper
import by.nts.cafe.app.model.db.TableModel
import kotlinx.android.synthetic.main.item_table.view.*

class TablesAdapter(private val ctx: Context, private val tables: List<TableModel>, private val listener: (TableModel) -> Unit) : RecyclerView.Adapter<TablesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_table, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val table = tables[position]

        with (vh.itemView) {
            txtTableName!!.text = table.name
            txtTableStatus!!.setText(UIHelper.getTableStatusTitleRes(table.status)!!)
            txtTableStatus!!.setTextColor(ctx.resources.getColor(
                    UIHelper.getTableStatusColorRes(table.status)!!
            ))

            cardRoot!!.setOnClickListener { _ -> listener(table) }
        }
    }

    override fun getItemCount(): Int {
        return tables.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
