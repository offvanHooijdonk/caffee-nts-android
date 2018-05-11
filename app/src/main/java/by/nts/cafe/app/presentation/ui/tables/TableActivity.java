package by.nts.cafe.app.presentation.ui.tables;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.nts.cafe.app.R;

public class TableActivity extends AppCompatActivity {

    @BindView(R.id.root)
    View root;
    @BindView(R.id.blockNotifications)
    View blockNotifications;
    @BindView(R.id.blockSummary)
    View blockSummary;
    @BindView(R.id.blockMenu)
    View blockMenu;
    @BindView(R.id.fabMenu)
    FloatingActionButton fabMenu;

    private boolean isMenuShown = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.fabMenu)
    protected void onMenuClick() {
        if (isMenuShown) {
            blockMenu.getLayoutParams().width = 0;
            blockNotifications.setVisibility(View.VISIBLE);
            blockSummary.setVisibility(View.VISIBLE);
            //blockMenu.setVisibility(View.GONE);

            fabMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_24));
        } else {
            //blockMenu.setVisibility(View.VISIBLE);
            blockMenu.getLayoutParams().width = root.getWidth() / 2;
            blockNotifications.setVisibility(View.GONE);
            blockSummary.setVisibility(View.GONE);

            fabMenu.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_24));
        }

        isMenuShown = !isMenuShown;
    }

}
