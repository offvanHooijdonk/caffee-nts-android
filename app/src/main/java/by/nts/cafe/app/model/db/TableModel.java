package by.nts.cafe.app.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import by.nts.cafe.app.model.db.converter.TypeConverter;

@Entity(tableName = "tables")
public class TableModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "hallId")
    private String hallId;
    @ColumnInfo(name = "status")
    @TypeConverters({TypeConverter.class})
    private STATUS status;

    public TableModel() {
    }

    public TableModel(int id, String name, String hallId, STATUS status) {
        this.id = id;
        this.name = name;
        this.hallId = hallId;
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

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
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

        public static STATUS fromCode(int code) {
            STATUS status;
            switch (code) {
                case 0: status = VACANT; break;
                case 1: status = OCCUPIED; break;
                case 2: status = RESERVED; break;
                case -1: status = NOT_AVAILABLE; break;
                default: status = VACANT;
            }

            return status;
        }
    }
}
