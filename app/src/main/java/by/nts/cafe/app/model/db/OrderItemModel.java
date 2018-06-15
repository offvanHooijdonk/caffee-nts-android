package by.nts.cafe.app.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "OrderItem")
public class OrderItemModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "dishId")
    private long dishId;
    @Ignore
    private DishModel dish;
    @ColumnInfo(name = "orderId")
    private long orderId;
    @ColumnInfo(name = "amount")
    private float amount;
    @ColumnInfo(name = "dateCreated")
    private long dateCreated;
    @ColumnInfo(name = "priceTotal")
    private float priceTotal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public DishModel getDish() {
        return dish;
    }

    public void setDish(DishModel dish) {
        this.dish = dish;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public float getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(float priceTotal) {
        this.priceTotal = priceTotal;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof OrderItemModel && id == ((OrderItemModel) o).id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }
}