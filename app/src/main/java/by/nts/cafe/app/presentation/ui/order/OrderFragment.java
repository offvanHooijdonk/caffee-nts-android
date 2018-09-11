package by.nts.cafe.app.presentation.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.CafeApp;
import by.nts.cafe.app.R;
import by.nts.cafe.app.dao.AppDatabase;
import by.nts.cafe.app.helper.rx.Transformers;
import by.nts.cafe.app.model.db.DishModel;
import by.nts.cafe.app.model.db.OrderItemModel;

public class OrderFragment extends Fragment implements IOrderView {
    private static final String TAG_DIALOG_MENU = "dialog_menu";

    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;

    private List<OrderItemModel> dishList = new ArrayList<>();
    private OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        adapter = new OrderAdapter(getActivity(), dishList);
        rvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvOrder.setAdapter(adapter);

        onOrderLoaded();
    }

    private void onOrderLoaded() {
        iniData();
        txtEmpty.setVisibility(dishList.isEmpty() ? View.VISIBLE : View.GONE);
        rvOrder.setVisibility(dishList.isEmpty() ? View.GONE : View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Deprecated
    private void iniData() {
        dishList.clear();
        CafeApp.Companion.getAppDatabase().orderDao().findByTable(1)
                .compose(Transformers.schedulersIOMaybe())
                .subscribe(orderItems -> {
                    dishList.addAll(orderItems.getItems());
                    adapter.notifyDataSetChanged();
                }, th -> {
                    Toast.makeText(getActivity(), th.toString(), Toast.LENGTH_LONG).show();
                });
        /*dishList.add(new DishModel("1", "Pasta Blah-blah-oza", 12.49f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("41", "Sauce salsa", 1.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("641", "Orange fresh", 2.99f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("971", "Cheese cake strawberry Alberto", 5.20f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("72", "Cappuccino small", 2.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("5", "Cigarettes 'Belomor'", 6.80f, getString(R.string.fish_dish_descr)));*/
    }
}
