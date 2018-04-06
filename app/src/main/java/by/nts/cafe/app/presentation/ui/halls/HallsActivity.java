package by.nts.cafe.app.presentation.ui.halls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.HallModel;

public class HallsActivity extends AppCompatActivity implements HallsAdapter.OnHallClickListener {
    @BindView(R.id.rvHalls)
    RecyclerView rvHalls;

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
    public void onHallClick(HallModel hallModel) {
        Toast.makeText(this, hallModel.getName(), Toast.LENGTH_LONG).show();
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
