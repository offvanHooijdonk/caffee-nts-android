package by.nts.cafe.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import by.nts.cafe.app.model.OrderModel;
import io.reactivex.Maybe;

@Dao
public interface OrderDao {
    @Query("SELECT * from `Order`")
    Maybe<OrderModel> findById();
}
