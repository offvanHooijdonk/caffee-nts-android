package by.nts.cafe.app.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import by.nts.cafe.app.R
import by.nts.cafe.app.locator.getAppContext

/**
 * Created by Yahor_Fralou on 9/11/2018 11:15 AM.
 */

class PrefHelper {
    private var ctx: Context = getAppContext()

    companion object {
        val instance by lazy { PrefHelper() }
    }

    fun getEndpointHost(): String =
            getSharedPreferences().getString(ctx.getString(R.string.pref_host_key), ctx.getString(R.string.pref_host_default_value))!!


    fun getEndpointPath(): String =
            getSharedPreferences().getString(ctx.getString(R.string.pref_path_key), ctx.getString(R.string.pref_path_default_value))!!

    private fun getSharedPreferences(): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(ctx)

}

fun getPref() = PrefHelper.instance