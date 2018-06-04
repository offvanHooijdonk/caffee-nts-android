package by.nts.cafe.app.presentation.ui.halls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.nts.cafe.app.R;
import by.nts.cafe.app.model.db.HallModel;

public class HallsAdapter extends RecyclerView.Adapter<HallsAdapter.ViewHolder> {
    private Context ctx;
    private List<HallModel> halls;
    private OnHallClickListener listener;

    public HallsAdapter(@NonNull Context ctx, @NonNull List<HallModel> halls, @Nullable OnHallClickListener listener) {
        this.ctx = ctx;
        this.halls = halls;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_hall, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        HallModel hall = halls.get(position);

        vh.txtHallName.setText(hall.getName());
        vh.cardRoot.setOnClickListener(v -> {
            if (listener != null) listener.onHallClick(hall);
        });
    }

    @Override
    public int getItemCount() {
        return halls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardRoot)
        View cardRoot;
        @BindView(R.id.txtHallName)
        TextView txtHallName;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnHallClickListener {
        void onHallClick(HallModel hallModel);
    }
}
