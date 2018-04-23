package by.nts.cafe.app.model;

import java.util.Objects;

public class DishModel {
    private String id;
    private String name;
    private float price;

    public DishModel() {
    }

    public DishModel(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
