package by.nts.cafe.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import java.util.List;

import by.nts.cafe.app.model.db.DishModel;

@Dao
public interface DishDao {

    @Insert
    void saveAll(List<DishModel> dishes);
}
