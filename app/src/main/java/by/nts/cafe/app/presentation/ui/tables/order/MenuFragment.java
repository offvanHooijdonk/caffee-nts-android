package by.nts.cafe.app.presentation.ui.tables.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.DishModel;

public class MenuFragment extends Fragment implements MenuAdapter.OnDishSelectedListener {
    @BindView(R.id.rvMenu)
    RecyclerView rvMenu;

    private List<DishModel> dishList = new ArrayList<>();
    private MenuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_menu_pick, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        initData();
        adapter = new MenuAdapter(getContext(), dishList, this);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Deprecated
    private void initData() {
        dishList.clear();
        dishList.add(new DishModel("1", "Pasta Blah-blah-oza", 12.49f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("41", "Sauce salsa", 1.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("641", "Orange fresh", 2.99f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("971", "Cheese cake strawberry Alberto", 5.20f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("72", "Cappuccino small", 2.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("72", "Draniki with mushrooms", 2.00f, getString(R.string.fish_dish_descr)));
        dishList.add(new DishModel("5", "Cigarettes 'Belomor'", 6.80f, getString(R.string.fish_dish_descr)));
    }

    @Override
    public void onDishSelect(DishModel dishModel) {
        ((OnDishSelectedListener) getParentFragment()).onDishSelect(dishModel);
    }

    public interface OnDishSelectedListener {
        void onDishSelect(DishModel dishModel);
    }
}
