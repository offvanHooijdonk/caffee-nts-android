package by.nts.cafe.app.model;

public class TableModel {
    private int id;
    private String name;
    private STATUS status;

    public TableModel() {
    }

    public TableModel(int id, String name, STATUS status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || o instanceof TableModel && id == ((TableModel) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public enum STATUS {
        VACANT(0), OCCUPIED(1), RESERVED(2), NOT_AVAILABLE(-1);
        private int code;

        STATUS(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}