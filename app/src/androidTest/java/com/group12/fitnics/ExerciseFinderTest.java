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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExerciseFinderTest {
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
        onView(withId(R.id.searchExercise)).perform(typeText("axe"));
        closeSoftKeyboard();
        // TODO: verify that the item appeared on the list
        onData(anything()).inAdapterView(withId(R.id.exerciseList)).atPosition(0).
                onChildView(withId(R.id.list_item)). // list_item ???
                check(matches(withText("Axe Hold")));
        // click the item
        onData(anything()).inAdapterView(withId(R.id.exerciseList)).atPosition(0).perform(click());
        // verify the title of the detailed-info screen
        onView(withId(R.id.exerciseTitle)).check(matches(withText("Axe Hold")));

    }

}
