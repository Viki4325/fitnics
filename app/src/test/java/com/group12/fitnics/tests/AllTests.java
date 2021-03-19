package com.group12.fitnics.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.group12.fitnics.tests.business.AccessExerciseLogsTest;
import com.group12.fitnics.tests.business.AccessExercisesTest;
import com.group12.fitnics.tests.business.AccessFoodLogsTest;
import com.group12.fitnics.tests.business.AccessFoodsTest;
import com.group12.fitnics.tests.business.AccessUsersTest;
import com.group12.fitnics.tests.business.NotificationHelperTest;
import com.group12.fitnics.tests.objects.ExerciseLogTest;
import com.group12.fitnics.tests.objects.ExerciseTest;
import com.group12.fitnics.tests.objects.FoodLogTest;
import com.group12.fitnics.tests.objects.FoodTest;
import com.group12.fitnics.tests.objects.UserTest;

/**
 * Before running any unit tests, please be sure to set
 * the value of 'forProduction' to false in application/Services class.
 * **/

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        FoodTest.class,
        FoodLogTest.class,
        ExerciseTest.class,
        ExerciseLogTest.class,
        AccessUsersTest.class,
        AccessFoodsTest.class,
        AccessFoodLogsTest.class,
        AccessExercisesTest.class,
        AccessExerciseLogsTest.class,
        NotificationHelperTest.class
})


public class AllTests {

}