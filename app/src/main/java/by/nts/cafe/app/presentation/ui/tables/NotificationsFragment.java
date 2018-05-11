package by.nts.cafe.app.presentation.ui.tables;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.nts.cafe.app.R;

public class NotificationsFragment extends Fragment {

    private RecyclerView rvNotifications;
    private NotificationAdapter adapter;
    private List<String> notifications = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_notifications, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        rvNotifications = v.findViewById(R.id.rvNotifications);

        iniData();
        adapter = new NotificationAdapter(getContext(), notifications);
        rvNotifications.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNotifications.setAdapter(adapter);
    }

    @Deprecated
    private void iniData() {
        notifications.add("Ready: Pizza");
        notifications.add("Ready: Cappuccino");
        notifications.add("Ready: Pasta");
    }

    private static class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
        private Context ctx;
        private List<String> notifications;

        NotificationAdapter(@NonNull Context ctx, @NonNull List<String> notifications) {
            this.ctx = ctx;
            this.notifications = notifications;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_notification, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder vh, int position) {
            String text = notifications.get(position);

            vh.txtNotificationText.setText(text);
        }

        @Override
        public int getItemCount() {
            return notifications.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtNotificationText;

            public ViewHolder(View v) {
                super(v);

                txtNotificationText = v.findViewById(R.id.txtNotificationText);
            }
        }

    }
}
