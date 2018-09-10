package by.nts.cafe.app.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Dish")
public class DishModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "price")
    private float price;
    @ColumnInfo(name = "description")
    private String description;

    public DishModel() {
    }

    public DishModel(String id, String name, float price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                o instanceof DishModel && id.equals(((DishModel) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
