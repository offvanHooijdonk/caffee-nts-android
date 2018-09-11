package by.nts.cafe.app.locator

import android.content.Context
import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.dao.HallDao
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.network.HallClient
import by.nts.cafe.app.network.NetworkClientFactory
import by.nts.cafe.app.network.TableClient

class ServiceLocator private constructor() {
    companion object {
        val instance by lazy { ServiceLocator() }
    }

    private lateinit var ctx: Context;
    private val serviceMap = mutableMapOf<String, Any>()

    fun setContext(context: Context) {
        ctx = context
    }

    internal fun getContext() = ctx

    fun getTableDao(): TableDao = locate(TableDao::class.java) {
        CafeApp.appDatabase.tableDao()
    }


    fun getTableClient(): TableClient = locate(TableClient::class.java) { // todo try 'internal' when tests are in kotlin
        NetworkClientFactory.retrofit.create(TableClient::class.java)
    }

    fun getHallDao(): HallDao = locate(HallDao::class.java) {
        CafeApp.appDatabase.hallDao()
    }

    fun getHallClient(): HallClient = locate(HallClient::class.java) {
        NetworkClientFactory.retrofit.create(HallClient::class.java)
    }

    private fun <T: Any> locate(clazz: Class<T>, init: () -> T): T =
            serviceMap.getOrPut(key(clazz), init) as T

    private fun <T> key(clazz: Class<T>) = clazz.name

}