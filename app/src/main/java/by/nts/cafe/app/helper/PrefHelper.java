package by.nts.cafe.app.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import by.nts.cafe.app.R;

public class PrefHelper {
    public static String getEndpointHost(Context ctx) {
        return getSharedPreferences(ctx).getString(ctx.getString(R.string.pref_host_key), ctx.getString(R.string.pref_host_default_value));
    }

    public static String getEndpointPath(Context ctx) {
        return getSharedPreferences(ctx).getString(ctx.getString(R.string.pref_path_key), ctx.getString(R.string.pref_path_default_value));
    }

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
}
