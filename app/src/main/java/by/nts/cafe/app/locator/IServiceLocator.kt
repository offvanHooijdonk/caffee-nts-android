package by.nts.cafe.app.locator

import android.content.Context
import by.nts.cafe.app.dao.HallDao
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.network.HallClient
import by.nts.cafe.app.network.TableClient

/**
 * Created by Yahor_Fralou on 9/12/2018 6:27 PM.
 */
interface IServiceLocator {
    fun getTableDao(): TableDao
    fun getTableClient(): TableClient
    fun getHallDao(): HallDao
    fun getHallClient(): HallClient
    fun getContext(): Context
    fun setContext(context: Context)
}