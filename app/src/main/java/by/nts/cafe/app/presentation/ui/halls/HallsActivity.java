package by.nts.cafe.app.presentation.ui.halls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import by.nts.cafe.app.model.HallModel;
import by.nts.cafe.app.network.NetworkClientFactory;
import by.nts.cafe.app.presentation.ui.pref.PreferenceActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HallsActivity extends AppCompatActivity implements HallsAdapter.OnHallClickListener {
    @BindView(R.id.rvHalls)
    RecyclerView rvHalls;

    private Disposable subscrHalls;

    private HallsAdapter adapter;
    private List<HallModel> hallList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halls);
        ButterKnife.bind(this);

        initTestData();
        adapter = new HallsAdapter(this, hallList, this);
        rvHalls.setAdapter(adapter);
        rvHalls.setLayoutManager(new GridLayoutManager(this, 4)); // TODO remove magic number

    }

    @Override
    protected void onStart() {
        super.onStart();

        subscrHalls = NetworkClientFactory.getHallClient(getApplicationContext()).getHalls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onHallsUpdated, this::onError);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (subscrHalls != null && !subscrHalls.isDisposed()) subscrHalls.dispose();
    }

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
    public void onHallClick(HallModel hallModel) {
        Toast.makeText(this, hallModel.getName(), Toast.LENGTH_LONG).show();
    }

    private void onHallsUpdated(List<HallModel> halls) {
        hallList.clear();
        hallList.addAll(halls);
        adapter.notifyDataSetChanged();
    }

    private void onError(Throwable th) {
        Toast.makeText(this, "Updated failed", Toast.LENGTH_LONG).show();
        Log.i(CafeApp.LOG, "Error getting halls.", th);
    }

    @Deprecated
    private void initTestData() {
        hallList.add(new HallModel("1", "Bowling Hall"));
        hallList.add(new HallModel("1", "Bar Cafe"));
        hallList.add(new HallModel("1", "Hall #1"));
        hallList.add(new HallModel("1", "Mint Hall"));
        hallList.add(new HallModel("1", "Hookah Hall Blah Blah"));
    }
}
