package by.nts.cafe.app.presentation.ui.tables

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import by.nts.cafe.app.R
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.locator.IServiceLocator
import by.nts.cafe.app.model.db.TableModel
import by.nts.cafe.app.network.TableClient
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Yahor_Fralou on 9/10/2018 4:43 PM.
 */
@RunWith(MockitoJUnitRunner::class)
class TableListActivityTest {

    companion object {
        private const val HALL_ID = "1000"
        private const val TABLE_ID_ONE = 1
        private const val TABLE_NAME_ONE = "Table number one"
        private const val TABLE_ID_TWO = 2
        private const val TABLE_NAME_TWO = "The second table"
    }

    @Mock
    lateinit var tableDao: TableDao
    @Mock
    lateinit var tableClient: TableClient
    @Mock
    lateinit var serviceLocator: IServiceLocator
    /*@Mock
    private lateinit var presenterFactory: PresenterFactory*/

    @Rule
    @JvmField
    var rule = ActivityTestRule<TableListActivity>(TableListActivity::class.java, false, false)/* {
        override fun getActivityIntent() = Intent().apply { putExtra(TableListActivity.EXTRA_HALL_ID, HALL_ID) }
    }*/

    //private TableListActivity activity;

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test()
    fun testUsersUpdate() {
        /*doReturn(Flowable.just(prepareList()))
                .`when`(tableClient).getTables(HALL_ID)*/
        doReturn(tableClient).`when`(serviceLocator).getTableClient()
        doReturn(tableDao).`when`(serviceLocator).getTableDao()

        //doReturn(Flowable.just(listOf<TableModel>())).`when`(tableDao).getAll(HALL_ID)

        startTableListActivity()

        Espresso.onView(ViewMatchers.withId(R.id.rvTables)).check { view, noViewExc ->
            if (noViewExc != null) throw noViewExc

            val adapter = (view as RecyclerView).adapter
            Assert.assertNotNull(adapter)
            Assert.assertEquals(0, adapter!!.itemCount.toLong())
        }
    }

    private fun startTableListActivity() {
        val intent = Intent()
        intent.putExtra(TableListActivity.EXTRA_HALL_ID, HALL_ID)
        rule.launchActivity(intent)
    }

    private fun prepareList() = listOf(
            TableModel(TABLE_ID_ONE, TABLE_NAME_ONE, HALL_ID, TableModel.STATUS.VACANT),
            TableModel(TABLE_ID_TWO, TABLE_NAME_TWO, HALL_ID, TableModel.STATUS.VACANT)
    )

}