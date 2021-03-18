package com.group12.fitnics.tests;

import com.group12.fitnics.tests.integration.AccessExerciseLogsIT;
import com.group12.fitnics.tests.integration.AccessExercisesIT;
import com.group12.fitnics.tests.integration.AccessFoodLogsIT;
import com.group12.fitnics.tests.integration.AccessFoodsIT;
import com.group12.fitnics.tests.integration.AccessUsersIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessUsersIT.class,
        AccessFoodsIT.class,
        AccessExercisesIT.class,
        AccessFoodLogsIT.class,
        AccessExerciseLogsIT.class
})

public class IntegrationTests {
}
