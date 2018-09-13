package by.nts.cafe.app.locator

import android.content.Context
import by.nts.cafe.app.CafeApp
import by.nts.cafe.app.dao.HallDao
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.network.HallClient
import by.nts.cafe.app.network.NetworkFactory
import by.nts.cafe.app.network.TableClient
import kotlin.reflect.KProperty

open class ServiceLocator private constructor() : IServiceLocator {
    companion object {
        var instance : IServiceLocator by lazy { ServiceLocator() }
    }

    private lateinit var ctx: Context;
    private val serviceMap = mutableMapOf<String, Any>()

    override fun setContext(context: Context) {
        ctx = context
    }

    override fun getContext() = ctx

    override fun getTableDao(): TableDao = locate(TableDao::class.java) {
        CafeApp.appDatabase.tableDao()
    }


    override fun getTableClient(): TableClient = locate(TableClient::class.java) {
        NetworkFactory.retrofit.create(TableClient::class.java)
    }

    override fun getHallDao(): HallDao = locate(HallDao::class.java) {
        CafeApp.appDatabase.hallDao()
    }

    override fun getHallClient(): HallClient = locate(HallClient::class.java) {
        NetworkFactory.retrofit.create(HallClient::class.java)
    }

    private fun <T : Any> locate(clazz: Class<T>, init: () -> T): T =
            serviceMap.getOrPut(key(clazz), init) as T

    private fun <T> key(clazz: Class<T>) = clazz.name

}

operator fun <T> Lazy<T>.setValue(companion: ServiceLocator.Companion, property: KProperty<*>, serviceLocator: IServiceLocator) {

}
