package by.nts.cafe.app.presentation.ui.tables.order;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    @NonNull
    private Context ctx;
    @NonNull
    private List<DishModel> dishList;

    public OrderAdapter(@NonNull Context ctx, @NonNull List<DishModel> dishList) {
        this.ctx = ctx;
        this.dishList = dishList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dish_in_order, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        DishModel dish = dishList.get(position);

        vh.txtDishName.setText(dish.getName());
        vh.txtDishDescription.setText(dish.getDescription());
        vh.txtDishPrice.setText(NumberFormat.getCurrencyInstance().format(dish.getPrice()));
        vh.txtAmount.setText("1");
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
}
