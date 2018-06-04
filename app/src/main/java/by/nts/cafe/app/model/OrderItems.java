package by.nts.cafe.app.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import by.nts.cafe.app.model.db.OrderItemModel;
import by.nts.cafe.app.model.db.OrderModel;

public class OrderItems {
    @Embedded
    private OrderModel order;
    @Relation(parentColumn = "id", entityColumn = "orderId")
    private List<OrderItemModel> items;

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public List<OrderItemModel> getItems() {
        return items;
    }

    public void setItems(List<OrderItemModel> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof OrderItems && order.equals(((OrderItems) o).order);
    }

    @Override
    public int hashCode() {
        return order.hashCode();
    }
}
