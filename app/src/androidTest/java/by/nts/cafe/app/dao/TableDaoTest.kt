package by.nts.cafe.app.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import by.nts.cafe.app.model.db.TableModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

/**
 * Created by Yahor_Fralou on 9/14/2018 12:23 PM.
 */

@RunWith(AndroidJUnit4::class)
class TableDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tableDao: TableDao

    companion object {
        private const val HALL_ID = "1"
        private const val TABLE_ID_ONE = 1
        private const val TABLE_NAME_ONE = "Table number one"
        private const val TABLE_ID_TWO = 2
        private const val TABLE_NAME_TWO = "The second table"
    }

    @Before
    fun setUp() {
        //classSetUp()

        MockitoAnnotations.initMocks(this)

        val database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java).allowMainThreadQueries().build()
        tableDao = database.tableDao()
    }

    @Test
    fun testListUpdate() {
        //Mockito.`when`(tableClient.getTables(HALL_ID)).thenReturn(Single.just(prepareList()))

        /*val flwTables: Flowable<List<TableModel>> */
        val newData = prepareList()
        val flwTables = tableDao.getAll(HALL_ID)
        tableDao.saveAll(newData)
        flwTables.test().assertValue { it.size == newData.size }
                .assertValue { it[0].status == newData[0].status }
                .assertValue { it[0].name == newData[0].name }
                .assertValue { it[1].id == newData[1].id }
                .assertValue { it[1].hallId == newData[1].hallId }

    }

    private fun prepareList() = listOf(
            TableModel(TABLE_ID_ONE, TABLE_NAME_ONE, HALL_ID, TableModel.STATUS.VACANT),
            TableModel(TABLE_ID_TWO, TABLE_NAME_TWO, HALL_ID, TableModel.STATUS.VACANT)
    )
}