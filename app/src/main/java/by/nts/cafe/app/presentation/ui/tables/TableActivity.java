package by.nts.cafe.app.presentation.ui.tables;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.presentation.ui.tables.order.GuestOrderFragment;

public class TableActivity extends AppCompatActivity {
    @BindView(R.id.listGuests)
    ListView listGuests;
    @BindView(R.id.txtNoGuestSelected)
    TextView txtNoGuestSelected;

    private List<Integer> guestList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        ButterKnife.bind(this);

        initData();
        listGuests.setAdapter(new ArrayAdapter<>(this, R.layout.item_guest, R.id.txtGuestNumber, guestList));
        listGuests.setOnItemClickListener((parent, view, position, id) -> {
            view.setSelected(true);
            txtNoGuestSelected.setVisibility(View.GONE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.blockGuestOrder, new GuestOrderFragment())
                    .commit();
        });
    }

    @Deprecated
    private void initData() {
        for (int i = 1; i < 9; i++) guestList.add(i);
    }
}
