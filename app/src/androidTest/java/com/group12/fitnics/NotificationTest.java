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
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NotificationTest {
    private final int sleepTime = 500;
    private TestUtils testUtils;
    private int[] UNITS = {1, 0};

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        testUtils = new TestUtils();
    }

    @Test
    public void notificationTest() {
        signTestUserIn();
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("testUser"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        SystemClock.sleep(sleepTime);
        // verify that the user logged in with the new account
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, testUser")));
        // open the notification setting screen
        onView(withId(R.id.Notificationbtn)).perform(click());
        // click (+) button to add a notification
        onView(withId(R.id.addNotificationBtn)).perform(click());
        // click "Got it" on the alert message
        onView(withText("OK"))
                .check(matches(isDisplayed()))
                .perform(click());
        // verify that the result displayed on the list
        onData(anything())
                .inAdapterView(withId(R.id.NotificationList)).atPosition(0)
                .check(matches(isDisplayed()));
        // select the generated one
        onData(anything()).inAdapterView(withId(R.id.NotificationList)).atPosition(0).perform(click());
        // edit the title
        onView(withId(R.id.TitleBox)).perform(replaceText("Workout"));
        closeSoftKeyboard();
        onView(withId(R.id.savebtn)).perform(click());
        // select the edited one
        onData(anything()).inAdapterView(withId(R.id.NotificationList)).atPosition(0).perform(click());
        // verify that the title changed
        onView(withId(R.id.TitleBox)).check(matches(withText("Workout")));
        // delete this notification
        onView(withId(R.id.deletebtn)).perform(click());
        pressBack();

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
        closeSoftKeyboard();
        onView(withId(R.id.chooseGender)).perform(click()); // AppCompatSpinner
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.weightUnitSwitch)).perform(click()); // switch to kilogram (it shows LBS first)
        onView(withId(R.id.editWeight)).perform(typeText("60"));
        closeSoftKeyboard();
        onView(withId(R.id.editHeight)).perform(typeText("5.18"));
        closeSoftKeyboard();
        onView(withId(R.id.btnContinue)).perform(scrollTo()).perform(click());
        // activity level
        onView(withId(R.id.SomewhatActivebtn)).perform(click());
        // click "Got it" on the alert message
        onView(withText("GOT IT"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
    }

}
