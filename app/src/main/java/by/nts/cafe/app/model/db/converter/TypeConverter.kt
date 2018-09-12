package by.nts.cafe.app.model.db.converter

import android.arch.persistence.room.TypeConverter
import by.nts.cafe.app.model.db.TableModel

/**
 * Created by Yahor_Fralou on 6/19/2018 6:16 PM.
 */
class TypeConverter {

    @TypeConverter
    fun fromTableStatus(status: TableModel.STATUS) = status.code

    @TypeConverter
    fun toTableStatus(code: Int): TableModel.STATUS = TableModel.STATUS.fromCode(code)
}