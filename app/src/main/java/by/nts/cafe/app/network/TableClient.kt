package by.nts.cafe.app.network

import by.nts.cafe.app.model.db.TableModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TableClient {
    @GET("tables")
    fun getTables(@Query("hallId") hallId: String): Single<List<TableModel>>
}