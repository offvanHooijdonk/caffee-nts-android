package by.nts.cafe.app.presentation.ui.halls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.CafeApp;
import by.nts.cafe.app.R;
import by.nts.cafe.app.helper.UIHelper;
import by.nts.cafe.app.model.db.HallModel;
import by.nts.cafe.app.presentation.presenter.halls.HallsPresenter;
import by.nts.cafe.app.presentation.ui.pref.PreferenceActivity;
import by.nts.cafe.app.presentation.ui.tables.TablesActivity;

public class HallsActivity extends AppCompatActivity implements IHallsView, HallsAdapter.OnHallClickListener {
    // region declarations
    @BindView(R.id.rvHalls)
    RecyclerView rvHalls;
    @BindView(R.id.rflHalls)
    SwipeRefreshLayout rflHalls;

    private HallsPresenter presenter;

    private HallsAdapter adapter;
    private List<HallModel> hallList = new ArrayList<>();
    // endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halls);
        ButterKnife.bind(this);

        presenter = new HallsPresenter(this, getApplicationContext());
        adapter = new HallsAdapter(this, hallList, this);
        rvHalls.setAdapter(adapter);
        rvHalls.setLayoutManager(new GridLayoutManager(this, 4)); // TODO remove magic number

        UIHelper.setupRefreshLayout(rflHalls);
        rflHalls.setOnRefreshListener(() -> presenter.updateHalls());
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.loadHalls();
    }

    // region Lifecycle
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_settings) {
            startActivity(new Intent(this, PreferenceActivity.class));
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detach();
    }
    //endregion

    // region Callbacks
    @Override
    public void onHallClick(HallModel hallModel) {
        startActivity(new Intent(this, TablesActivity.class)
                .putExtra(TablesActivity.EXTRA_TABLE_ID, hallModel.getId())
                .putExtra(TablesActivity.EXTRA_TABLE_TABLE, hallModel.getName())
        );
    }
    // endregion

    // region Interaction
    @Override
    public void onHallsLoaded(List<HallModel> halls) {
        hallList.clear();
        hallList.addAll(halls);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable th) {
        rflHalls.setRefreshing(false);
        Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
        Log.i(CafeApp.LOG, "Error getting halls.", th);
    }

    @Override
    public void showUpdateProcess(boolean isShow) {
        rflHalls.setRefreshing(isShow);
    }
    // endregion
}
