package by.nts.cafe.app.locator

import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.network.TableClient
import java.util.NoSuchElementException

fun <T> locateService(clazz: Class<T>): T = with(ServiceLocator.instance) {
    when (clazz) {
        TableDao::class.java -> getTableDao() as T
        TableClient::class.java -> getTableClient() as T
        else -> throw NoSuchElementException("No such class in Locator ${clazz.name}")
    }
}