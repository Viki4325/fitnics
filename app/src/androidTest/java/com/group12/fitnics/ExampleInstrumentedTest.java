package com.group12.fitnics;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.group12.fitnics.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.group12.fitnics", appContext.getPackageName());
    }

    @Test
    public void findFood() {

        onView(withId(R.id.btnLogIn)).perform(click());

        // log in with username
        onView(withId(R.id.editUsername)).perform(typeText("alice"));
        closeSoftKeyboard();
        onView(withId(R.id.btnLogInToHome)).perform(click());

        // verify that the user logged in
        onView(withId(R.id.textGreeting)).check(matches(withText("Welcome, alice")));

        // click add breakfast
        onView(withId(R.id.breakfast)).perform(click());

        // search food item
        onView(withId(R.id.searchFood)).perform(typeText("bread"));

        // verify that the item appeared
        onView(withId(R.id.Search_food)).check(matches(withText("")));

        onData(anything()).inAdapterView(withId(R.id.Search_food)).atPosition(0).
                onChildView(withId(R.id.list_item)).
                check(matches(withText("bread")));
    }
}