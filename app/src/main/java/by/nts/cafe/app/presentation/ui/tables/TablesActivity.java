package by.nts.cafe.app.presentation.ui.tables;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.TableModel;

public class TablesActivity extends AppCompatActivity implements TablesAdapter.OnTableClickListener {

    @BindView(R.id.rvTables)
    RecyclerView rvTable;
    @BindView(R.id.refreshTables)
    SwipeRefreshLayout refreshTables;

    private TablesAdapter adapter;
    private List<TableModel> tableList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        ButterKnife.bind(this);

        initData();
        adapter = new TablesAdapter(this, tableList, this);
        rvTable.setAdapter(adapter);
        rvTable.setLayoutManager(new GridLayoutManager(this, 3)); // TODO make number configurable/adaptive


        refreshTables.setColorSchemeResources(R.color.refresh_1, R.color.refresh_2, R.color.refresh_3);
        refreshTables.setOnRefreshListener(() -> {refreshTables.setRefreshing(false);});
    }

    @Override
    public void onTableClick(TableModel tableModel) {
        startActivity(new Intent(this, TableActivity.class));
    }

    @Deprecated
    private void initData() {
        tableList.add(new TableModel(1, "table 1", TableModel.STATUS.VACANT));
        tableList.add(new TableModel(2, "table 2", TableModel.STATUS.OCCUPIED));
        tableList.add(new TableModel(3, "table 3", TableModel.STATUS.RESERVED));
        tableList.add(new TableModel(4, "table 4", TableModel.STATUS.VACANT));
        tableList.add(new TableModel(5, "table 5", TableModel.STATUS.NOT_AVAILABLE));
    }
}
