package com.group12.fitnics;

import android.os.SystemClock;

import androidx.test.espresso.action.ViewActions;
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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AccountManageTest {
    private final int sleepTime = 300;
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
    public void updateInfoTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // check the original daily caloric target
        onView(withId(R.id.textRemaining)).check(matches(withText("3151")));
        // change weight, height
        SystemClock.sleep(sleepTime);
        onView(withId(R.id.btnUpdateUserInfo)).perform(click());
        onView(withId(R.id.editWeight)).perform(replaceText("52"));
        closeSoftKeyboard();
        onView(withId(R.id.editHeight)).perform(replaceText("5.35"));
        closeSoftKeyboard();
        onView(withId(R.id.saveButton)).perform(scrollTo()).perform(click());
        // verify if the daily caloric target updates
        SystemClock.sleep(sleepTime);
        onView(withId(R.id.textRemaining)).check(matches(withText("2400")));
        // undo the changes
        User alice = new User("alice", 15, 4, 1998, 1, 55, 163, 'F', 0, UNITS);
        testUtils.updateUser("alice", alice);
    }

    @Test
    public void signUpTest() {
        // click the 'Sign up' button
        onView(withId(R.id.btnSignUp)).perform(click());
        // select the goal - loose weight
        onView(withId(R.id.gainWeightbtn)).perform(click());
        // enter the user info
        onView(withId(R.id.enterUsername)).perform(typeText("eddie"));
        onView(withId(R.id.editBirthday)).perform(typeText("15041998"));
        closeSoftKeyboard();
        onView(withId(R.id.chooseGender)).perform(click()); // AppCompatSpinner
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.weightUnitSwitch)).perform(click()); // switch to kilogram (it shows LBS first)
        onView(withId(R.id.editWeight)).perform(scrollTo()).perform(typeText("66"));
        closeSoftKeyboard();
        onView(withId(R.id.editHeight)).perform(scrollTo()).perform(typeText("5.71"));
        closeSoftKeyboard();
        onView(withId(R.id.btnContinue)).perform(scrollTo()).perform(click());
        // activity level
        onView(withId(R.id.Activebtn)).perform(click());
        // click "Got it" on the alert message
        onView(withText("GOT IT"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        // now log in with username
        onView(withId(R.id.editUsername)).perform(typeText("eddie"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // verify if the user can login with the new account
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, eddie")));
        onView(withId(R.id.textRemaining)).check(matches(withText("2302")));
        // delete the new account just added
        testUtils.deleteUser("eddie");
    }



}
