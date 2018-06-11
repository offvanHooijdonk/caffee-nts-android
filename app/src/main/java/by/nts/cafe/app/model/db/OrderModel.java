package by.nts.cafe.app.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Orders")
public class OrderModel {
    @PrimaryKey()
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "dateCreated")
    private long dateCreated;
    @ColumnInfo(name = "tableId")
    private long tableId;
    @ColumnInfo(name = "priceSummary")
    private float priceSummary;
    @ColumnInfo(name = "priceTotal")
    private float priceTotal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public float getPriceSummary() {
        return priceSummary;
    }

    public void setPriceSummary(float priceSummary) {
        this.priceSummary = priceSummary;
    }

    public float getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(float priceTotal) {
        this.priceTotal = priceTotal;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof OrderModel && id == ((OrderModel) o).id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }
}
