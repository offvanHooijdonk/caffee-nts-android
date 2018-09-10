package by.nts.cafe.app.presentation.ui.order;

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
import by.nts.cafe.app.model.db.DishModel;
import by.nts.cafe.app.model.db.OrderItemModel;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    @NonNull
    private Context ctx;
    @NonNull
    private List<OrderItemModel> orderItemList;

    public OrderAdapter(@NonNull Context ctx, @NonNull List<OrderItemModel> orderItemList) {
        this.ctx = ctx;
        this.orderItemList = orderItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_dish_in_order, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        OrderItemModel orderItem = orderItemList.get(position);
        DishModel dish = orderItem.getDish();

        vh.txtDishName.setText(dish.getName());
        vh.txtDishPrice.setText(NumberFormat.getCurrencyInstance().format(dish.getPrice()));
        vh.txtAmount.setText("1");
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtDishName)
        TextView txtDishName;
        @BindView(R.id.txtDishPrice)
        TextView txtDishPrice;
        @BindView(R.id.txtAmount)
        TextView txtAmount;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
