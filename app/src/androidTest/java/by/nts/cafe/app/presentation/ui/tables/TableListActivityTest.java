package by.nts.cafe.app.presentation.ui.tables;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import by.nts.cafe.app.R;
import by.nts.cafe.app.dao.TableDao;
import by.nts.cafe.app.locator.ServiceLocator;
import by.nts.cafe.app.model.db.TableModel;
import by.nts.cafe.app.network.TableClient;

/**
 * Created by Yahor_Fralou on 9/10/2018 4:43 PM.
 */
@RunWith(MockitoJUnitRunner.class)
public class TableListActivityTest {
    private static final String HALL_ID = "1";
    private static final int TABLE_ID_ONE = 1;
    private static final String TABLE_NAME_ONE = "Table number one";
    private static final int TABLE_ID_TWO = 2;
    private static final String TABLE_NAME_TWO = "The second table";

    @Mock
    private TableDao tableDao;

    @Mock
    private TableClient tableClient;

    @Rule
    public ActivityTestRule<TableListActivity> rule = new ActivityTestRule<>(TableListActivity.class);
    //private TableListActivity activity;

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.putExtra(TableListActivity.EXTRA_HALL_ID, HALL_ID);
        //activity = rule.launchActivity(intent);
    }

    @Test
    public void testUsersUpdate() {
        /*doReturn(Flowable.just(Collections.emptyList()))
                .when(tableDao).getAll(HALL_ID);
        doReturn(Flowable.just(prepareList()))
                .when(tableClient).getTables(HALL_ID);*/
        Mockito.when(ServiceLocator.Companion.getInstance().getTableClient()).thenReturn(tableClient);

        Espresso.onView(ViewMatchers.withId(R.id.rvUsers)).check((view, noViewExc) -> {
            RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
            Assert.assertNotNull(adapter);
            Assert.assertEquals(adapter.getItemCount(), 0);
        });
    }

    private List<TableModel> prepareList() {
        return Arrays.asList(
                new TableModel(TABLE_ID_ONE, TABLE_NAME_ONE, HALL_ID, TableModel.STATUS.VACANT),
                new TableModel(TABLE_ID_TWO, TABLE_NAME_TWO, HALL_ID, TableModel.STATUS.VACANT)
        );
    }
}