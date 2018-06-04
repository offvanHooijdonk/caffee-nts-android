package by.nts.cafe.app.presentation.ui.auth;

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
import by.nts.cafe.app.model.db.UserModel;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context ctx;
    private List<UserModel> users;
    private OnUserClickListener listener;

    public UsersAdapter(@NonNull Context ctx, @NonNull List<UserModel> users, @Nullable OnUserClickListener listener) {
        this.ctx = ctx;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        UserModel user = users.get(position);
        vh.txtUserName.setText(user.getName());

        vh.root.setOnClickListener(v -> {
            if (listener != null) listener.onUserClicked(user);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View root;
        @BindView(R.id.txtUserName)
        TextView txtUserName;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnUserClickListener {
        void onUserClicked(UserModel userModel);
    }
}
