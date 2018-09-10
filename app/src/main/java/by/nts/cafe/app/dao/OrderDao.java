package by.nts.cafe.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import by.nts.cafe.app.model.OrderItems;
import by.nts.cafe.app.model.db.OrderItemModel;
import by.nts.cafe.app.model.db.OrderModel;
import io.reactivex.Maybe;

@Dao
public interface OrderDao {
    @Insert
    void saveAllOrders(List<OrderModel> orders);

    @Insert
    void saveAllOrderItems(List<OrderItemModel> orderItems);

    @Query("SELECT * from Orders where id = :tableId")
    Maybe<OrderItems> findByTable(Integer tableId);
}
