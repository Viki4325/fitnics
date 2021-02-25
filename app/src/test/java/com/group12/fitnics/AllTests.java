package com.group12.fitnics;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.group12.fitnics.tests.business.AccessExercisesTest;
import com.group12.fitnics.tests.business.AccessFoodLogsTest;
import com.group12.fitnics.tests.business.AccessFoodsTest;
import com.group12.fitnics.tests.business.AccessUsersTest;
import com.group12.fitnics.tests.business.CaloriesHelperTest;
import com.group12.fitnics.tests.objects.ExerciseLogTest;
import com.group12.fitnics.tests.objects.ExerciseTest;
import com.group12.fitnics.tests.objects.FoodLogTest;
import com.group12.fitnics.tests.objects.FoodTest;
import com.group12.fitnics.tests.objects.MyDateTest;
import com.group12.fitnics.tests.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        MyDateTest.class,
        FoodTest.class,
        FoodLogTest.class,
        ExerciseTest.class,
        ExerciseLogTest.class,
        AccessUsersTest.class,
        AccessFoodsTest.class,
        AccessFoodLogsTest.class,
        AccessExercisesTest.class,
        AccessExercisesTest.class,
        CaloriesHelperTest.class
})

public class AllTests {

}