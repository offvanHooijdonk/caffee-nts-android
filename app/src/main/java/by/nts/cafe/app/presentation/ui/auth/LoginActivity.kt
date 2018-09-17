package by.nts.cafe.app.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.EditText
import by.nts.cafe.app.R
import by.nts.cafe.app.helper.UIHelper
import by.nts.cafe.app.model.db.UserModel
import by.nts.cafe.app.presentation.ui.halls.HallsActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import java.util.*

class LoginActivity : AppCompatActivity(), ILoginView {

    private var adapter: UsersAdapter? = null
    private val userList = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initTestData()
        adapter = UsersAdapter(applicationContext, userList, this::onUserClicked)

        rvUsers!!.layoutManager = LinearLayoutManager(applicationContext)
        rvUsers!!.adapter = adapter

        UIHelper.setupRefreshLayout(rflUsers)
        initRefreshLayout()
    }

    private fun onUserClicked(userModel: UserModel) {
        val inputPassword = EditText(this)
        inputPassword.setHint(R.string.dialog_password_title)

        alert {
            customView = inputPassword
            titleResource = R.string.dialog_password_title
            okButton { dialog ->
                dialog.dismiss()
                goToMain()
            }
            cancelButton { dialog -> dialog.dismiss() }
            isCancelable = false
        }.show()
    }

    private fun initRefreshLayout() {
        rflUsers.setOnRefreshListener {
            userList.add(UserModel("Sample User".hashCode().toString(), "Sample User"))
            adapter?.notifyDataSetChanged()
            rflUsers.isRefreshing = false
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, HallsActivity::class.java))
    }

    @Deprecated("")
    private fun initTestData() {
        userList.add(UserModel("1", "John Doe"))
        userList.add(UserModel("3", "Jane Sue"))
        userList.add(UserModel("354", "Mohandas Gandhi"))
    }
}
