package by.nts.cafe.app.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import by.nts.cafe.app.model.db.HallModel;
import by.nts.cafe.app.model.db.OrderModel;
import by.nts.cafe.app.model.db.TableModel;

@Database(entities = {HallModel.class, OrderModel.class, TableModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "cafe-nts-v0.27";

    public static AppDatabase buildDatabase(Context ctx) {
        return Room.databaseBuilder(ctx, AppDatabase.class, DB_NAME).build();
    }

    public abstract HallDao hallDao();

    public abstract OrderDao orderDao();

    public abstract TableDao tableDao();

    // region Init DB
    public static void initTempData(AppDatabase db) {
        db.hallDao().saveAll(getTempHalls());
        db.tableDao().saveAll(getTempTables());
    }

    private static List<HallModel> getTempHalls() {
        List<HallModel> halls = new ArrayList<>();

        halls.add(new HallModel("1", "Bowling Hall"));
        halls.add(new HallModel("51", "Primary Hall"));
        halls.add(new HallModel("985", "Hookah Hall"));
        halls.add(new HallModel("6233", "Bar Hall"));
        halls.add(new HallModel("63", "Hall #2"));

        return halls;
    }

    private static List<TableModel> getTempTables() {
        List<TableModel> tables = new ArrayList<>();

        tables.add(new TableModel(1, "table 1", "1", TableModel.STATUS.VACANT));
        tables.add(new TableModel(2, "table 2", "1", TableModel.STATUS.OCCUPIED));
        tables.add(new TableModel(3, "table 3", "1", TableModel.STATUS.RESERVED));
        tables.add(new TableModel(4, "table 4", "1", TableModel.STATUS.VACANT));
        tables.add(new TableModel(5, "table 5", "1", TableModel.STATUS.NOT_AVAILABLE));

        return tables;
    }
    // endregion

}
