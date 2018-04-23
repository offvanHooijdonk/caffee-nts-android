package by.nts.cafe.app.presentation.ui.tables;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.DishModel;

public class GuestOrderFragment extends Fragment {

    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;


    private List<DishModel> dishList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_guest_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        onOrderLoaded();
    }

    private void onOrderLoaded() {
        txtEmpty.setVisibility(dishList.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
