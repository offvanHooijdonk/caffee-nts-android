package by.nts.cafe.app.network

import by.nts.cafe.app.model.db.TableModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TableClient {
    @GET
    fun getTables(@Query("hallId") hallId: String): Observable<List<TableModel>>
}