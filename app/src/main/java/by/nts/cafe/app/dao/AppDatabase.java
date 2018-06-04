package by.nts.cafe.app.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import by.nts.cafe.app.model.db.HallModel;

@Database(entities = {HallModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "cafe-nts-v0.21";

    public static AppDatabase buildDatabase(Context ctx) {
        return Room.databaseBuilder(ctx, AppDatabase.class, DB_NAME).build();
    }

    public abstract HallDao hallDao();

    public abstract OrderDao orderDao();
}
