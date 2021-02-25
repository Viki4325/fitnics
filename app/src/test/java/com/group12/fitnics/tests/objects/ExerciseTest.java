package com.group12.fitnics.tests.objects;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.group12.fitnics.objects.Exercise;

public class ExerciseTest {


    @BeforeClass
    public static void setup(){
        System.out.println("\nStarting testing on the Exercise class...");
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("\nFinished testing on the Exercise class...");
    }

    @Test
    public void testExerciseNoParams(){
        System.out.println("\nStarting testExerciseNoParams");

        Exercise exercise_NoParams = new Exercise();

        assertNotNull("Exercise object created with null constructor should not be null", exercise_NoParams);

        //Since it is created with the null constructor, all the expected results are null
        assertEquals("Exercise object created with null constructor, id should be -1", exercise_NoParams.getExerciseID() , 0 );
        assertNull("Exercise object created with null constructor, title should be null", exercise_NoParams.getTitle());
        assertNull("Exercise object created with null constructor, description should be null", exercise_NoParams.getDescription());
        assertNull("Exercise object created with null constructor, category should be null", exercise_NoParams.getCategory());
        assertNull("Exercise object created with null constructor, level should be null", exercise_NoParams.getLevel());
        assertEquals("Exercise object created with null constructor, calories should be null", exercise_NoParams.getCaloriesBurn(), 0);

        System.out.println("Finished testExerciseNoParams");
    }

    @Test
    public void testExerciseParamNoId(){
        System.out.println("\nStarting testExerciseParamNoId");

        Exercise workout = new Exercise(
                "Axe Hold",
                "Grab dumbbells and extend arms to side and hold as long as you can",
                "Arms",
                "Intermediate",
                500
        );

        assertNotNull("Exercise object created with constructor with no ID should not be null", workout);


        assertEquals("The id should be equal to 0", workout.getExerciseID(), 0);
        assertEquals("The title should be equal to Axe Hold", workout.getTitle(), "Axe Hold");
        assertEquals("The description should be equal to 'Grab dumbbells and extend arms to side and hold as long as you can' ",
                        workout.getDescription(), "Grab dumbbells and extend arms to side and hold as long as you can");
        assertEquals("The category should be equal to 'Arms' ", workout.getCategory(), "Arms");
        assertEquals("The level should be equal to 'Intermediate' ", workout.getLevel(), "Intermediate");
        assertEquals("The calories should be equal to 500", workout.getCaloriesBurn(), 500);

        System.out.println("Finished testExerciseParamNoId");

    }

    @Test
    public void testExerciseParamCustomId(){
        System.out.println("\nStarting testExerciseParamCustomId");

        Exercise workout = new Exercise(
                5,
                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

        assertEquals("The id should be equal to 0", workout.getExerciseID(), 5);
        assertEquals("The title should be equal to Chin-Ups", workout.getTitle(), "Chin-Ups");
        assertEquals("The description should be equal to 'Like pull-ups but with a reverse grip' ",
                workout.getDescription(), "Like pull-ups but with a reverse grip\n");
        assertEquals("The category should be equal to 'Back' ", workout.getCategory(), "Back");
        assertEquals("The level should be equal to 'Beginner' ", workout.getLevel(), "Beginner");
        assertEquals("The calories should be equal to 1", workout.getCaloriesBurn(), 1);

        System.out.println("Finished testExerciseParamCustomId");
    }

    @Test
    public void testSetExerciseTitle(){
        System.out.println("\nStarting testSetExerciseTitle");

        Exercise workout = new Exercise(
                5,
                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );


        System.out.println("Finished testSetExerciseTitle");
    }

    @Test
    public void testSetExerciseDescription(){
        System.out.println("\nStarting testSetExerciseDescription");

        String expectedDescription = "Description has been changed!!";

        Exercise workout = new Exercise(
                5,
                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

        assertNotNull("Exercise object created should not be null", workout);

        String originalDescription = workout.getDescription();
        workout.setDescription(expectedDescription);

        assertNotEquals("Exercise object's description has been changed, it should not be equal to the original description", originalDescription, expectedDescription);
        assertEquals("Exercise object's description has been changed, it should be equal to the new description", workout.getDescription(), expectedDescription);

        System.out.println("Finished testSetExerciseDescription");
    }

    @Test
    public void testSetExerciseCategory(){
        System.out.println("\nStarting testSetExerciseCategory");

        String expectedCategory = "Category has been changed!!";

        Exercise workout = new Exercise(
                5,
                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

        assertNotNull("Exercise object created should not be null", workout);

        String originalCategory = workout.getCategory();
        workout.setCategory(expectedCategory);

        assertNotEquals("Exercise object's category has been changed, it should not be equal to the original category", originalCategory, expectedCategory);
        assertEquals("Exercise object's category has been changed, it should be equal to the new category", workout.getCategory(), expectedCategory);

        System.out.println("Finished testSetExerciseCategory");
    }

    @Test
    public void testSetExerciseLevel(){
        System.out.println("\nStarting testSetExerciseLevel");

        String expectedLevel = "Level has been changed!!";

        Exercise workout = new Exercise(
                5,
                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

        assertNotNull("Exercise object created should not be null", workout);

        String originalLevel = workout.getLevel();
        workout.setLevel(expectedLevel);

        assertNotEquals("Exercise object's Level has been changed, it should not be equal to the original Level", originalLevel, expectedLevel);
        assertEquals("Exercise object's Level has been changed, it should be equal to the new Level", workout.getLevel(), expectedLevel);
        System.out.println("Finished testSetExerciseLevel");
    }

    @Test
    public void testSetExerciseCalories(){
        System.out.println("\nStarting testSetExerciseCalories");

        int expectedCalories = 1000;

        Exercise workout = new Exercise(
                5,
                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

        assertNotNull("Exercise object created should not be null", workout);

        int originalCalories = workout.getCaloriesBurn();
        workout.setCaloriesBurn(expectedCalories);

        assertNotEquals("Exercise object's calories has been changed, it should not be equal to the original calories", originalCalories, expectedCalories);
        assertEquals("Exercise object's calories has been changed, it should be equal to the new calories", workout.getCaloriesBurn(), expectedCalories);

        System.out.println("Finished testSetExerciseCalories");
    }
}
