package by.nts.cafe.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import by.nts.cafe.app.model.db.TableModel;
import io.reactivex.Flowable;

@Dao
public interface TableDao {

    @Query("SELECT * FROM tables WHERE hallId = :hallId ORDER BY name")
    Flowable<List<TableModel>> getAll(String hallId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TableModel> tableList);
}
