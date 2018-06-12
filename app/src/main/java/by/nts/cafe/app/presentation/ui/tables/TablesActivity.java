package by.nts.cafe.app.presentation.ui.tables;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.helper.UIHelper;
import by.nts.cafe.app.model.db.TableModel;
import by.nts.cafe.app.presentation.presenter.tables.TablesPresenter;

public class TablesActivity extends AppCompatActivity implements ITablesView, TablesAdapter.OnTableClickListener {
    public static String EXTRA_TABLE_ID = "EXTRA_TABLE_ID";
    public static String EXTRA_TABLE_TABLE = "EXTRA_TABLE_ID";

    @BindView(R.id.rvTables)
    RecyclerView rvTable;
    @BindView(R.id.refreshTables)
    SwipeRefreshLayout refreshTables;

    private String hallId;
    private TablesAdapter adapter;
    private List<TableModel> tableList = new ArrayList<>();
    private TablesPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        ButterKnife.bind(this);

        presenter = new TablesPresenter(this);

        String hallName = getIntent().getStringExtra(EXTRA_TABLE_TABLE);
        if (hallName != null) {
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(hallName);
            if (getActionBar() != null) getActionBar().setTitle(hallName);
        }

        adapter = new TablesAdapter(this, tableList, this);
        rvTable.setAdapter(adapter);
        rvTable.setLayoutManager(new GridLayoutManager(this, 3)); // TODO make number configurable/adaptive

        hallId = getIntent().getStringExtra(EXTRA_TABLE_ID);
        if (hallId != null) {
            presenter.loadTableList(hallId);
        } else {
            // todo show message
        }

        UIHelper.setupRefreshLayout(refreshTables);
        refreshTables.setOnRefreshListener(() -> refreshTables.setRefreshing(false));
    }

    @Override
    public void onTableClick(TableModel tableModel) {
        startActivity(new Intent(this, TableActivity.class));
    }

    @Override
    public void onTablesLoaded(List<TableModel> list) {

    }

    @Override
    public void handleError(Throwable th) {
        Toast.makeText(this, th.getMessage(), Toast.LENGTH_LONG).show(); // TODO handle better
    }
}
