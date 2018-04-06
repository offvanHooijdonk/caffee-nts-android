package by.nts.cafe.app.model;

public class UserModel {
    private String id;
    private String name;

    public UserModel() {
    }

    public UserModel(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        return this == o ||
                o instanceof UserModel && this.id.equals(((UserModel) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
