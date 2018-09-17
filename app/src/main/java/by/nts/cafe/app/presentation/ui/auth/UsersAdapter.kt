package by.nts.cafe.app.presentation.ui.auth

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.nts.cafe.app.R
import by.nts.cafe.app.model.db.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(private val ctx: Context, private val users: List<UserModel>, private val listener: (UserModel) -> Unit) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_user, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val user = users[position]
        vh.itemView.txtUserName.text = user.name

        vh.itemView.root.setOnClickListener { listener(user) }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
