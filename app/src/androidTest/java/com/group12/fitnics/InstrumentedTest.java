package com.group12.fitnics;

import android.content.Context;
import android.os.SystemClock;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
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
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class InstrumentedTest {
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
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.group12.fitnics", appContext.getPackageName());
    }

    @Test
    public void signUpTest() {
        // click the 'Sign up' button
        onView(withId(R.id.btnSignUp)).perform(click());
        // goal - loose weight
        onView(withId(R.id.loseWeightbtn)).perform(click());
        // enter the user info
        onView(withId(R.id.enterUsername)).perform(typeText("eve"));
        onView(withId(R.id.editBirthday)).perform(typeText("15041998"));
        onView(withId(R.id.chooseGender)).perform(click()); // AppCompatSpinner
        onData(anything()).atPosition(0).perform(click()); // M? F? 0? 1?
        onView(withId(R.id.weightUnitSwitch)).perform(click()); // switch to kilogram
        onView(withId(R.id.editWeight)).perform(typeText("60"));
        onView(withId(R.id.editHeight)).perform(typeText("158"));
        onView(withId(R.id.btnContinue)).perform(click());
        // activity level
        onView(withId(R.id.NotActivebtn)).perform(click());
        // now log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // verify if the user can login with the new account
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, eve")));
        onView(withId(R.id.textRemaining)).check(matches(withText("1900"))); // TODO: correct number
        // TODO: delete the new account just added ??
        testUtils.deleteUser("eve");
    }

    @Test
    public void updateInfoTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // check the original daily caloric target
        onView(withId(R.id.textRemaining)).check(matches(withText("1900"))); // TODO: correct number
        // change weight
        onView(withId(R.id.btnUpdateUserInfo)).perform(click());
        onView(withId(R.id.editWeightLayout)).perform(typeText("52"));
        closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(click());
        // verify if the daily caloric target updates
        onView(withId(R.id.textRemaining)).check(matches(withText("1900"))); // TODO: correct number
        // TODO: undo the changes ??
        int[] units = {1, 0};
        User alice = new User("alice", 15, 4, 1998, 1, 55, 163, 'F', 0, units);
        testUtils.updateUser("alice", alice);
    }

    @Test
    public void findFoodTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // verify that the user logged in
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, alice")));
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
        onView(withId(R.id.add_food)).perform(click());
        pressBack();
        // TODO: need to verify something from here? or from foodLoggerTest??

    }

    @Test
    public void findExerciseTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // verify that the user logged in
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, alice")));
        // click 'Add exercise' button
        onView(withId(R.id.exercise)).perform(click());
        // search exercise item
        onView(withId(R.id.searchExercise)).perform(typeText("chin"));
        closeSoftKeyboard();
        // TODO: verify that the item appeared on the list

        // TODO: add the item

        // TODO: need to verify something from here ? or from exerciseLoggerTest??

    }

    @Test
    public void foodLoggerTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // open the food log screen
        onView(withId(R.id.btnFoodLog)).perform(click());
        // TODO: verify that the log item is there
        onData(anything()).inAdapterView(withId(R.id.food_log)).atPosition(0).
                onChildView(withId(R.id.list_item)).
                check(matches(withText("____"))); // check: the item added while findFoodTest

        // TODO: delete the item added (while findFoodTest)

    }

    @Test
    public void exerciseLoggerTest () {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // open the exercise log screen
        onView(withId(R.id.btnExerciseLog)).perform(click());
        // TODO: verify that the log item is there
        onData(anything()).inAdapterView(withId(R.id.exerciseLog)).atPosition(0).
                onChildView(withId(R.id.list_item)).
                check(matches(withText("____"))); // check: the item added while findExerciseTest

        // TODO: delete the item added (while findExerciseTest)

    }

    @Test
    public void caloriesTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // verify
        onView(withId(R.id.textTotalIntake)).check(matches(withText("100"))); // TODO: correct number
        onView(withId(R.id.textTotalBurned)).check(matches(withText("100"))); // TODO: correct number
        onView(withId(R.id.textRemaining)).check(matches(withText("1900"))); // TODO: correct number
    }

    @Test
    public void notificationTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // open the notification setting screen
//        onView(withId(R.id._____)).perform(click());
        // TODO: How to test push notification ???
        // https://stackoverflow.com/questions/34467310/espresso-test-for-notification-to-showing-up
        // https://stackoverflow.com/questions/33495294/testing-notifications-in-android

    }

    @Test
    public void foodsSpecificDateTest() {

    }

    @Test
    public void exercisesSpecificDateTest() {

    }

}