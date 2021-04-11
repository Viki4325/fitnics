package com.group12.fitnics;

import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

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
public class DailyCaloriesTest {
    private final int sleepTime = 500;
    private TestUtils testUtils;
    private LocalDate dateToday;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        testUtils = new TestUtils();
        dateToday = LocalDate.now();
    }

    @Test
    public void caloriesTest() {
        signTestUserIn();
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("testUser"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // TODO: verify the remaining calories for today

        // add food log
        onView(withId(R.id.breakfast)).perform(click());
        onView(withId(R.id.searchFood)).perform(typeText("bread"));
        closeSoftKeyboard();
        onData(anything()).inAdapterView(withId(R.id.Search_food)).atPosition(0).perform(click());
        onView(withId(R.id.foodGrams)).perform(typeText("100"));
        onView(withId(R.id.add_food)).perform(click()); // should go to the log screen
        pressBack();
        // add exercise log
        onView(withId(R.id.exercise)).perform(click());
        onView(withId(R.id.searchExercise)).perform(typeText("axe"));
        closeSoftKeyboard();
        onData(anything()).inAdapterView(withId(R.id.exerciseList)).atPosition(0).perform(click());
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.exerciseMinutes)).perform(typeText("10"));
        onView(withId(R.id.update_Min)).perform(click()); // should go to the log screen
        pressBack();
        // verify
        onView(withId(R.id.textTotalIntake)).check(matches(withText("100"))); // TODO: correct number
        onView(withId(R.id.textTotalBurned)).check(matches(withText("100"))); // TODO: correct number
        onView(withId(R.id.textRemaining)).check(matches(withText("1900"))); // TODO: correct number

        // undo the change
        testUtils.deleteFoodLog("testUser", "bread", dateToday);
        testUtils.deleteExerciseLog("testUser", "axe hold", dateToday);
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
