package by.nts.cafe.app.presentation.ui.tables.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.DishModel;

public class AddDishDialogFragment extends DialogFragment implements MenuFragment.OnDishSelectedListener {

    //region Declarations
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.blockAmount)
    View blockAmount;
    @BindView(R.id.txtDishName)
    TextView txtDishName;
    @BindView(R.id.btnPlus)
    ImageButton btnPlus;
    @BindView(R.id.btnMinus)
    ImageButton btnMinus;
    @BindView(R.id.fabAddDish)
    FloatingActionButton fabAddDish;

    private DishModel dishSelected;
    //endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme_DishDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_dish_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new MenuFragment();
            }

            @Override
            public int getCount() {
                return 6;
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        tabLayout.addTab(tabLayout.newTab().setText("First"));
        tabLayout.addTab(tabLayout.newTab().setText("Soups"));
        tabLayout.addTab(tabLayout.newTab().setText("Drinks"));
        tabLayout.addTab(tabLayout.newTab().setText("Alcohol"));
        tabLayout.addTab(tabLayout.newTab().setText("Deserts"));
        tabLayout.addTab(tabLayout.newTab().setText("Misc."));
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() != null) {
            getDialog().setCancelable(true);
            getDialog().setCanceledOnTouchOutside(true);
            getDialog().getWindow().setLayout(getContext().getResources().getDimensionPixelOffset(R.dimen.dialog_dish_width),
                    WindowManager.LayoutParams.MATCH_PARENT);
        }
    }

    @OnClick(R.id.fabAddDish)
    protected void addDishToOrder() {
        // todo call for presenter

        dishSelected = null; // call when saved and hide the block
    }

    @Override
    public void onDishSelect(DishModel dishModel) {
        dishSelected = dishModel;
        blockAmount.setVisibility(View.VISIBLE);
        txtDishName.setText(dishModel.getName());
    }
}
