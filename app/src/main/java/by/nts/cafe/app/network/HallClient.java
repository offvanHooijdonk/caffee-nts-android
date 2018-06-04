package by.nts.cafe.app.network;

import java.util.List;

import by.nts.cafe.app.model.db.HallModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HallClient {
    @GET("hall")
    Observable<List<HallModel>> getHalls();
}
