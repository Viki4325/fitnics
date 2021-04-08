package com.group12.fitnics;

import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@LargeTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountManageTest.class,
        FoodFinderTest.class,
        ExerciseFinderTest.class,
        FoodLoggerTest.class,
        ExerciseLoggerTest.class,
        DailyCaloriesTest.class,
        NotificationTest.class,
        WorkoutProgressTest.class,
        DietProgressTest.class
})


public class AllAcceptanceTests {
}
