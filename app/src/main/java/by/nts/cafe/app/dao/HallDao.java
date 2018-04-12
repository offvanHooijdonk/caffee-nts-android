package by.nts.cafe.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import by.nts.cafe.app.model.HallModel;
import io.reactivex.Maybe;

@Dao
public interface HallDao {

    @Query("SELECT * from halls ORDER BY name")
    Maybe<List<HallModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<HallModel> list);
}
