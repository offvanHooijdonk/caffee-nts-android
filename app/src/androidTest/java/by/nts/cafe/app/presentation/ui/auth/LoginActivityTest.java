package by.nts.cafe.app.presentation.ui.auth;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void onUserClicked() {
        int itemsCount = rule.getActivity().getRvUsers().getAdapter().getItemCount();
        Assert.assertTrue("Expected users > 0, found: " + itemsCount, itemsCount > 0);
    }
}