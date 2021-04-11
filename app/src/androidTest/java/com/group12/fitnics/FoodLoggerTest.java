package com.group12.fitnics;

import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.group12.fitnics.objects.User;
import com.group12.fitnics.presentation.MainActivity;
import com.group12.fitnics.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FoodLoggerTest {
    private final int sleepTime = 500;
    private TestUtils testUtils;
    private User testUser;
    private LocalDate dateToday;
    private int[] UNITS = {1, 0};

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        testUtils = new TestUtils();
        dateToday = LocalDate.now();
        testUser = new User("testUser", 15, 4, 1998, 1, 55, 163, 'F', 0, UNITS);
    }

    @Test
    public void foodLoggerTest() {
        signTestUserIn();
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("testUser"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // click 'Add breakfast' button
        onView(withId(R.id.breakfast)).perform(click());
        // search food item
        onView(withId(R.id.searchFood)).perform(typeText("bread"));
        closeSoftKeyboard();
        // TODO: verify that the item appeared on the list
        onData(anything()).inAdapterView(withId(R.id.Search_food)).atPosition(0).
                onChildView(withId(R.id.list_item)).
                check(matches(withText("bread")));
        // add the item
        onData(anything()).inAdapterView(withId(R.id.Search_food)).atPosition(0).perform(click());
        onView(withId(R.id.foodGrams)).perform(typeText("100"));
        onView(withId(R.id.add_food)).perform(click()); // should go to the log screen
        pressBack();
        // open the food log screen
        onView(withId(R.id.btnFoodLog)).perform(click());
        // TODO: verify that the added log item is there
        onData(anything()).inAdapterView(withId(R.id.food_log)).atPosition(0).
                onChildView(withId(R.id.list_item)).
                check(matches(withText("bread"))); // is it right?
        // TODO: click the item, and change grams
        onData(anything()).inAdapterView(withId(R.id.food_log)).atPosition(0).perform(click());
        onView(withId(R.id.foodGrams)).perform(typeText("200"));
        onView(withId(R.id.add_food)).perform(click()); // should go to the log screen
        pressBack();
        // TODO: verify the change is made on the log screen

        // undo the change
        testUtils.deleteFoodLog("testUser", "bread", dateToday);
        testUtils.deleteUser("testUser");
    }

    private void signTestUserIn() {
        // click the 'Sign up' button
        onView(withId(R.id.btnSignUp)).perform(click());
        // select the goal - loose weight
        onView(withId(R.id.loseWeightbtn)).perform(click());
        // enter the user info
        onView(withId(R.id.enterUsername)).perform(typeText("testUser"));
        onView(withId(R.id.editBirthday)).perform(typeText("15041998"));
        onView(withId(R.id.chooseGender)).perform(click()); // AppCompatSpinner
        onData(anything()).atPosition(1).perform(click()); // M? F? 0? 1?
        onView(withId(R.id.weightUnitSwitch)).perform(click()); // switch to kilogram (it shows LBS first)
        onView(withId(R.id.editWeight)).perform(typeText("60"));
        onView(withId(R.id.editHeight)).perform(typeText("158"));
        onView(withId(R.id.btnContinue)).perform(click());
        // activity level
        onView(withId(R.id.SomewhatActivebtn)).perform(click());
    }


}
