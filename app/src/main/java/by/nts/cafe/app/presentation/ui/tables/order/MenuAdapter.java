package by.nts.cafe.app.presentation.ui.tables.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.DishModel;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private static int lastCheckedPosition;
    @NonNull
    private Context ctx;
    @NonNull
    private List<DishModel> dishList;
    @Nullable
    private OnDishSelectedListener listener; // todo replace with the local interface

    public MenuAdapter(@NonNull Context ctx, @NonNull List<DishModel> dishList, OnDishSelectedListener listener) {
        this.ctx = ctx;
        this.dishList = dishList;
        this.listener = listener;

        lastCheckedPosition = -1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dish_add, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        DishModel dish = dishList.get(position);

        vh.txtDishName.setText(dish.getName());
        vh.txtDishDescription.setText(dish.getDescription());
        vh.txtDishPrice.setText(NumberFormat.getCurrencyInstance().format(dish.getPrice()));
        vh.txtAmount.setText("1");

        vh.root.setSelected(lastCheckedPosition == position);

        vh.root.setOnClickListener(v -> {
            if (listener != null) listener.onDishSelect(dish);
            lastCheckedPosition = position;
            this.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View root;
        @BindView(R.id.txtDishName)
        TextView txtDishName;
        @BindView(R.id.txtDishPrice)
        TextView txtDishPrice;
        @BindView(R.id.txtDishDescription)
        TextView txtDishDescription;
        @BindView(R.id.txtAmount)
        TextView txtAmount;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnDishSelectedListener {
        void onDishSelect(DishModel dishModel);
    }

}
