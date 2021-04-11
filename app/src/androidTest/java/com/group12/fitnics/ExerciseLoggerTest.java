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
public class ExerciseLoggerTest {
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
    public void exerciseLoggerTest () {
        signTestUserIn();
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("testUser"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // click 'Add exercise' button
        onView(withId(R.id.exercise)).perform(click());
        // search exercise item
        onView(withId(R.id.searchExercise)).perform(typeText("axe"));
        closeSoftKeyboard();
        // TODO: verify that the item appeared on the list
        onData(anything()).inAdapterView(withId(R.id.exerciseList)).atPosition(0).
                onChildView(withId(R.id.list_item)). // list_item ???
                check(matches(withText("Axe Hold")));
        // click the item
        onData(anything()).inAdapterView(withId(R.id.exerciseList)).atPosition(0).perform(click());
        // click the add (+) button
        onView(withId(R.id.add_button)).perform(click());
        // TODO: should go to the screen to type minutes...
//        onView(withId(R.id.exerciseMinutes)).perform(typeText("5"));
//        onView(withId(R.id.update_Min)).perform(click()); // should go to the log screen
//        pressBack();
        // open the exercise log screen
        onView(withId(R.id.btnExerciseLog)).perform(click());
        // TODO: verify that the log item is there
        onData(anything()).inAdapterView(withId(R.id.exerciseLog)).atPosition(0).
                onChildView(withId(R.id.list_item)).
                check(matches(withText("____"))); // check if the item added...
        // TODO: click the item, and change minutes
        onData(anything()).inAdapterView(withId(R.id.exerciseLog)).atPosition(0).perform(click());
        onView(withId(R.id.exerciseMinutes)).perform(typeText("10"));
        onView(withId(R.id.update_Min)).perform(click()); // should go to the log screen
        pressBack();
        // TODO: verify the change is made on the log screen

        // undo the change
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
