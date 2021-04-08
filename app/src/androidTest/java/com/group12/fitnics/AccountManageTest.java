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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AccountManageTest {
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


}
