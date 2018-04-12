package by.nts.cafe.app.presentation.ui.tables;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import by.nts.cafe.app.R;
import by.nts.cafe.app.model.TableModel;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {
    private Context ctx;
    private List<TableModel> tables;
    private OnTableClickListener listener;

    public TablesAdapter(Context ctx, List<TableModel> tables, @Nullable OnTableClickListener listener) {
        this.ctx = ctx;
        this.tables = tables;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnTableClickListener {
        void onTableClick(TableModel tableModel);
    }
}
