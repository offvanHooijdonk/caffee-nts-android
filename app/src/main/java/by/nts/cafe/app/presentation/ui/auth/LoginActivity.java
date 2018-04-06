package by.nts.cafe.app.presentation.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.UserModel;
import by.nts.cafe.app.presentation.ui.halls.HallsActivity;

public class LoginActivity extends AppCompatActivity implements UsersAdapter.OnUserClickListener {
    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;
    @BindView(R.id.rflUsers)
    SwipeRefreshLayout rflUsers;

    private UsersAdapter adapter;
    private List<UserModel> userList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initTestData();
        adapter = new UsersAdapter(getApplicationContext(), userList, this);

        rvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvUsers.setAdapter(adapter);

        initRefreshLayout();
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        EditText inputPassword = new EditText(this);
        inputPassword.setHint(R.string.dialog_password_title);

        new AlertDialog.Builder(this)
                .setView(inputPassword)
                .setTitle(R.string.dialog_password_title)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    goToMain();
                })
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private void initRefreshLayout() {
        rflUsers.setColorSchemeResources(R.color.refresh_1, R.color.refresh_2, R.color.refresh_3);
        rflUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userList.add(new UserModel(String.valueOf("Sample User".hashCode()), "Sample User"));
                adapter.notifyDataSetChanged();
                rflUsers.setRefreshing(false);
            }
        });
    }

    private void goToMain() {
        startActivity(new Intent(this, HallsActivity.class));
    }

    @Deprecated
    private void initTestData() {
        userList.add(new UserModel("1", "John Doe"));
        userList.add(new UserModel("3", "Jane Sue"));
        userList.add(new UserModel("354", "Mohandas Gandhi"));
    }
}
