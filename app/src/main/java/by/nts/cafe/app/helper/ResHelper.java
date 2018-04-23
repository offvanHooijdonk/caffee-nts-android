package by.nts.cafe.app.helper;

import java.util.HashMap;
import java.util.Map;

import by.nts.cafe.app.R;
import by.nts.cafe.app.model.TableModel;

public class ResHelper {

    private static Map<TableModel.STATUS, Integer> STATUS_TABLE_TITLES = new HashMap<>();
    private static Map<TableModel.STATUS, Integer> STATUS_TABLE_COLORS = new HashMap<>();

    static {
        STATUS_TABLE_TITLES.put(TableModel.STATUS.VACANT, R.string.status_table_vacant);
        STATUS_TABLE_TITLES.put(TableModel.STATUS.OCCUPIED, R.string.status_table_occupied);
        STATUS_TABLE_TITLES.put(TableModel.STATUS.RESERVED, R.string.status_table_reserved);
        STATUS_TABLE_TITLES.put(TableModel.STATUS.NOT_AVAILABLE, R.string.status_table_not_available);

        STATUS_TABLE_COLORS.put(TableModel.STATUS.VACANT, R.color.status_table_vacant);
        STATUS_TABLE_COLORS.put(TableModel.STATUS.OCCUPIED, R.color.status_table_occupied);
        STATUS_TABLE_COLORS.put(TableModel.STATUS.RESERVED, R.color.status_table_reserved);
        STATUS_TABLE_COLORS.put(TableModel.STATUS.NOT_AVAILABLE, R.color.status_table_not_available);
    }

    public static Integer getTableStatusTitleRes(TableModel.STATUS status) {
        return STATUS_TABLE_TITLES.get(status);
    }

    public static Integer getTableStatusColorRes(TableModel.STATUS status) {
        return STATUS_TABLE_COLORS.get(status);
    }
}
