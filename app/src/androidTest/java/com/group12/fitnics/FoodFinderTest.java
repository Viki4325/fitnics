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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FoodFinderTest {
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
    public void findFoodTest() {
        // click the 'Log in' button
        onView(withId(R.id.btnLogIn)).perform(click());
        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());
        // verify that the user logged in
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, alice")));
        // click 'Add breakfast' button
        onView(withId(R.id.breakfast)).perform(click());
        // search food item
        onView(withId(R.id.searchFood)).perform(typeText("bread"));
        closeSoftKeyboard();
        // verify that the result displayed on the list
        onData(anything())
                .inAdapterView(withId(R.id.Search_food))
                .atPosition(0)
                .check(matches(isDisplayed()));
    }
}
