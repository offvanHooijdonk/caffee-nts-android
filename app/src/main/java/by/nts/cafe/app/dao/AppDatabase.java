package by.nts.cafe.app.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.nts.cafe.app.model.db.DishModel;
import by.nts.cafe.app.model.db.HallModel;
import by.nts.cafe.app.model.db.OrderItemModel;
import by.nts.cafe.app.model.db.OrderModel;
import by.nts.cafe.app.model.db.TableModel;

@Database(entities = {HallModel.class, OrderModel.class, TableModel.class, OrderItemModel.class, DishModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "cafe-nts-v0.28";

    public static AppDatabase buildDatabase(Context ctx) {
        return Room.databaseBuilder(ctx, AppDatabase.class, DB_NAME).build();
    }

    public abstract HallDao hallDao();

    public abstract OrderDao orderDao();

    public abstract TableDao tableDao();

    public abstract DishDao dishDao();

    // region Init DB
    public static void initTempData(AppDatabase db) {
        db.hallDao().saveAll(getTempHalls());
        db.tableDao().saveAll(getTempTables());
        db.dishDao().saveAll(getTempDishes());
        db.orderDao().saveAllOrders(getTempOrders());
        db.orderDao().saveAllOrderItems(getTempOrderItems());
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

    private static List<DishModel> getTempDishes() {
        return Arrays.asList(
                new DishModel("1", "Pasta Blah-blah-oza", 12.49f, ""),
                new DishModel("41", "Sauce salsa", 1.00f, "Lorem ipsum dolor sit amet, consectetur adipiscing"),
                new DishModel("641", "Orange fresh", 2.99f, "Lorem ipsum dolor sit amet, consectetur adipiscing"),
                new DishModel("971", "Cheese cake strawberry Alberto", 5.20f, "Lorem ipsum dolor sit amet, consectetur adipiscing"),
                new DishModel("72", "Cappuccino small", 2.00f, "Lorem ipsum dolor sit amet, consectetur adipiscing"),
                new DishModel("5", "Cigarettes 'Belomor'", 6.80f, "Lorem ipsum dolor sit amet, consectetur adipiscing")
        );
    }

    private static List<OrderModel> getTempOrders() {
        return Arrays.asList(
                new OrderModel(1, 1656862L, 1, 235f, 6289f)
        );
    }

    private static List<OrderItemModel> getTempOrderItems() {
        return Arrays.asList(
                new OrderItemModel(1, "1", 1, 2, 1265496L, 23f),
                new OrderItemModel(2, "5", 1, 1, 1265496L, 6.8f)
        );
    }
    // endregion

}
