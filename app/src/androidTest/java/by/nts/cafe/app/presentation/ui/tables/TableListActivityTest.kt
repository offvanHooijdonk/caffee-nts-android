package by.nts.cafe.app.presentation.ui.tables

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import by.nts.cafe.app.R
import by.nts.cafe.app.dao.TableDao
import by.nts.cafe.app.locator.ServiceLocator
import by.nts.cafe.app.model.db.TableModel
import by.nts.cafe.app.network.TableClient
import by.nts.cafe.app.presentation.presenter.PresenterFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Yahor_Fralou on 9/10/2018 4:43 PM.
 */
@RunWith(MockitoJUnitRunner::class)
class TableListActivityTest {

    companion object {
        private const val HALL_ID = "1"
        private const val TABLE_ID_ONE = 1
        private const val TABLE_NAME_ONE = "Table number one"
        private const val TABLE_ID_TWO = 2
        private const val TABLE_NAME_TWO = "The second table"
    }

    @Mock
    private val tableDao: TableDao? = null
    @Mock
    private val tableClient: TableClient? = null
    @Mock
    private val serviceLocator: ServiceLocator? = null
    @Mock
    private val presenterFactory: PresenterFactory? = null

    @Rule
    var rule = ActivityTestRule(TableListActivity::class.java)

    //private TableListActivity activity;

    @Before
    fun setUp() {
        val intent = Intent()
        intent.putExtra(TableListActivity.EXTRA_HALL_ID, HALL_ID)

        //activity = rule.launchActivity(intent);
    }

    @Test
    fun testUsersUpdate() {
        /*doReturn(Flowable.just(Collections.emptyList()))
                .when(tableDao).getAll(HALL_ID);
        doReturn(Flowable.just(prepareList()))
                .when(tableClient).getTables(HALL_ID);*/
        Mockito.`when`(ServiceLocator.instance.getTableClient()).thenReturn(tableClient)

        Espresso.onView(ViewMatchers.withId(R.id.rvUsers)).check { view, noViewExc ->
            if (noViewExc != null) throw noViewExc

            val adapter = (view as RecyclerView).adapter
            Assert.assertNotNull(adapter)
            Assert.assertEquals(adapter!!.itemCount.toLong(), 0)
        }
    }

    private fun prepareList() = listOf(
            TableModel(TABLE_ID_ONE, TABLE_NAME_ONE, HALL_ID, TableModel.STATUS.VACANT),
            TableModel(TABLE_ID_TWO, TABLE_NAME_TWO, HALL_ID, TableModel.STATUS.VACANT)
    )

}