package com.group12.fitnics.tests.business;

import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.objects.Exercise;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AccessExercisesTest {

    private AccessExercises exerciseHandler;
    private Exercise axe_Hold;
    private Exercise bench_press;
    private Exercise shrugs;
    private Exercise braced_squat;
    private Exercise flutter_kicks;
    private Exercise chin_ups;
    private Exercise calf_raises;


    @BeforeClass
    public static void initialSetup(){
        System.out.println("\nStarted testing AccessExercise from the Logic layer....");

    }

    @AfterClass
    public static void tearDown(){
        System.out.println("\nFinished testing AccessExercise.");
    }

    @Before
    public void setup(){
        exerciseHandler = new AccessExercises();

        //id 0
         axe_Hold = new Exercise(
                "Axe Hold",
                "Grab dumbbells and extend arms to side and hold as long as you can",
                "Arms",
                "Intermediate",
                500
        );

         //id 1
          braced_squat = new Exercise(
                "Brace Squat",
                "Stand with feet slightly wider than shoulder-width apart, while standing as tall as you can. \n" +
                        "Grab a weight plate and hold it out in front of your body with arms straight out. Keep your core tight and stand with a natural arch in your back. \n" +
                        "Now, push hips back and bend knees down into a squat as far as you can. Hold for a few moments and bring yourself back up to the starting position.\n",
                "Legs",
                "Intermediate",
                222
          );

          //id 2
         flutter_kicks = new Exercise(
                "Flutter Kicks",
                "Laying on the back, lift your straightened legs from the ground at a 45 degree angle.\n"+
                        "As your Left foot travels downward and nearly touches the floor, your Right foot should seek to reach a 90 degree angle, or as close to one as possible.\n"+
                        "Bring your R foot down until it nearly touches the floor, and bring your L foot upwards.  Maintain leg rigidity throughout the exercise.  Your head should stay off the ground, supported by tightened upper abdominals.\n" +
                        "(L up R down, L down R up, x2)  ^v, v^, ^v, v^ = 1 rep\n" ,
                "Abs",
                "Beginner",
                120
        );

         //id 3
         bench_press = new Exercise(

                "Bench Press",
                "Lay down on a bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. " +
                        "Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. " +
                        "Push the bar up. \n" +
                        "If you train with a high weight it is advisable to have a spotter that can help you up if you can't lift the weight on your own.\n"+
                        "With the width of the grip you can also control which part of the chest is trained more:\n" +
                        "\n" +
                        "\t\twide grip: outer chest muscles\n" +
                        "\t\tnarrow grip: inner chest muscles and triceps",
                "Chest",
                "Beginner",
                532
        );

         //id 4
         chin_ups = new Exercise(

                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

         //id 5
         shrugs = new Exercise(

                "Shrugs,Dumbbells",
                "Stand with straight body, the hands are hanging freely on the side and hold each a dumbbell. Lift from this position the shoulders as high as you can, but don't bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.\n" +
                        "When training with a higher weight, make sure that you still do the whole movement!\n" ,
                "Shoulders",
                "Expert",
                100
        );

         //id 6
         calf_raises = new Exercise(

                "Calf Raises",
                "Calf raises are a method of exercising the gastrocnemius, tibialis posterior and soleus muscles of the lower leg. The movement performed is plantar flexion, a.k.a. ankle extension.\n",
                "Calves",
                "Expert",
                85
        );
    }

    @Test
    public void testgetExerciseList(){
        System.out.println("\nStarting testgetExerciseList...");

        List<Exercise> expectedExerciseList = exerciseHandler.getAllExercise();

        assertNotNull("The list of exercises(from fake DB) should not be null",expectedExerciseList);
        //Total number of objects should be 7
        assertEquals("The list of exercise(From fake DB) should have 7 objects",exerciseHandler.getAllExercise().size(),7);

        System.out.println("\nFinished testgetExerciseList");

    }

    @Test
    public void testListIsSortedAlphabetically(){
        System.out.println("\nTesting if the list of exercise is sorted alphabetically");

        List<Exercise> isListSorted = exerciseHandler.getAllExercise();

        Iterator<Exercise> iter = isListSorted.iterator();
        Exercise current ,previous = iter.next();
        while (iter.hasNext()) {
            System.out.println(previous.getTitle()+"P\n");
            current = iter.next();
            assertTrue(previous.getTitle().compareToIgnoreCase(current.getTitle()) <= 0);
            previous = current;
        }

        System.out.println("Testing if the list of exercise is sorted finished.");
    }

    @Test
    public void testSetSearch(){
        System.out.println("\nStarting testSetSearch...");

        String[] testSearchStrings = {
                "exercise",
                "Ax",
                "GO",
                "keepFit"
        };

        assertNotNull(testSearchStrings);

        for (String testSearchPhrase : testSearchStrings) {
            exerciseHandler.setSearchPhrase(testSearchPhrase);
            assertEquals("The search phrase has been set, therefore should be equal", exerciseHandler.getSearchPhrase(), testSearchPhrase);
        }

        //Does not break if set to null
        exerciseHandler.setSearchPhrase(null);
        assertNull("Search phrase was set to null. Should get a search phrase equal to null",exerciseHandler.getSearchPhrase());

        System.out.println("\nFinished testSetSearch...");
    }

    @Test
    public void testGetSearch(){
        System.out.println("\nStarting testGetSearch");

        String search_Phrase_1 = "dumbbells";
        String search_phrase_2 = "WORKOUT";
        String search_phrase_3 = "ben";

        assertNull("Should be equal to null, since no search phrase has been set", exerciseHandler.getSearchPhrase());
        //Get and compare taking into consideration the case
        exerciseHandler.setSearchPhrase(search_Phrase_1);
        assertEquals("Search phrase has been set. Should not be equal to null",exerciseHandler.getSearchPhrase(),search_Phrase_1);
        assertNotEquals("Search phrase has been set and compared with the opposite case(UPPERCASE/lowercase) of what was used to set",exerciseHandler.getSearchPhrase(),search_Phrase_1.toUpperCase());

        exerciseHandler.setSearchPhrase(search_phrase_2);
        assertEquals("Search phrase has been set. Should not be equal to null",exerciseHandler.getSearchPhrase(),search_phrase_2);
        assertNotEquals("Search phrase has been set and compared with the opposite case(UPPERCASE/lowercase) of what was used to set",exerciseHandler.getSearchPhrase(),search_phrase_2.toLowerCase());

        exerciseHandler.setSearchPhrase(search_phrase_3);
        assertEquals("Search phrase has been set. Should not be equal to null",exerciseHandler.getSearchPhrase(),search_phrase_3);
        assertNotEquals("Search phrase has been set and compared with the opposite case(UPPERCASE/lowercase) of what was used to set",exerciseHandler.getSearchPhrase(),search_phrase_3.toUpperCase());

        //Null is returned if set a null phrase
        exerciseHandler.setSearchPhrase(null);
        assertNull("Search phrase was set to null. Should get a search phrase equal to null",exerciseHandler.getSearchPhrase());

        System.out.println("Finished testGetSearch");
    }

    @Test
    public void testResetSearch(){
        System.out.println("\nStarting testResetSearch...");
        exerciseHandler.resetSearch();
        assertNull("search was reset. It search phrase should be null",exerciseHandler.getSearchPhrase());

        exerciseHandler.setSearchPhrase("I_will_reset_");
        exerciseHandler.resetSearch();
        assertNull("search was set then reset. It search phrase should be null",exerciseHandler.getSearchPhrase());

        System.out.println("\nFinishing testResetSearch...");

    }


    @Test
    public void testgetExerciseBySearch(){
        System.out.println("\nStarting testgetExerciseBySearch...");

        List<Exercise> exerciseList = exerciseHandler.getAllExercise();



        String[] searchPhrase = {
                "hold",
                "re",
                "be",
                "rugs",
                "ups",
                "uat"
        };

        List<Exercise> searchResult;
        //brute-force testing
        exerciseHandler.setSearchPhrase(searchPhrase[0]);
        searchResult = exerciseHandler.getExerciseBySearch(exerciseList);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 'HOLD' ", searchResult.size(), 1);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 'HOLD' ", searchResult.get(0).getTitle(), "Axe Hold");
        assertEquals(searchResult.get(0),axe_Hold);

        exerciseHandler.setSearchPhrase(searchPhrase[1]);
        searchResult = exerciseHandler.getExerciseBySearch(exerciseList);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 're' ", searchResult.size(), 1);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 're' ", searchResult.get(0).getTitle(), "Bench Press");
        assertEquals(searchResult.get(0),bench_press);

        exerciseHandler.setSearchPhrase(searchPhrase[2]);
        searchResult = exerciseHandler.getExerciseBySearch(exerciseList);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 'be' ", searchResult.size(), 2);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 'be' ", searchResult.get(0).getTitle(), "Bench Press");
        assertEquals(searchResult.get(0),bench_press);
        assertEquals("Set searchPhrase that should only return ONE exercise object, should contain 'be' ", searchResult.get(1).getTitle(), "Shrugs,Dumbbells");
        assertEquals(searchResult.get(1),shrugs);

        System.out.println("Finished testExerciseBYSearch");

    }

    @Test
    public void testSetFilter(){
        System.out.println("\nStarting testSetFilter...");
        List<String> filterStrings = Arrays.asList("Beginner",
                "Arms");

        for (String filterString : filterStrings) {
            exerciseHandler.setFilter(filterString);
        }

        assertNotNull("Filter strings should not be null",exerciseHandler.getFilter());
        assertEquals("Filter strings should be equal to the expected List of strings",exerciseHandler.getFilter(),filterStrings);

        //Trying to add a filter that was already added
        String repeatFilter = "Arms";
        exerciseHandler.setFilter(repeatFilter);
        assertNotNull("Filter strings should not be null after adding a repeated filter string",exerciseHandler.getFilter());
        assertEquals("Adding a filter string that already existed. Should only have one of those. NO DUPLICATES",exerciseHandler.getFilter().size(),2);


        System.out.println("Finished testSetFilter");

    }

    @Test
    public void testGetFilter(){
        System.out.println("\nStarting testGetFilter...");

        exerciseHandler.resetFilter();
        assertTrue("No filter strings set. Strings of filter should be empty", exerciseHandler.getFilter().isEmpty());

        List<String> filterStrings = Arrays.asList("Beginner",
                "Arms");

        for (String filterString : filterStrings) {
            exerciseHandler.setFilter(filterString);
        }

        assertNotNull("Filter strings set. Strings of filter should not be null",exerciseHandler.getFilter());
        assertEquals("Filter strings should be equal to the expected List of strings",exerciseHandler.getFilter(),filterStrings);

        System.out.println("Finished testGetFilter");
    }

    @Test
    public void testResetFilter(){
        System.out.println("\nStarting testRestFilter...");

        //Since filters were set in the methods above, it already exists
        assertNotNull("Filters strings are set. Should not be equal to null. Filters not string",exerciseHandler.getFilter());

        exerciseHandler.resetFilter();
        assertTrue("Filters strings reset. Should be empty",exerciseHandler.getFilter().isEmpty());
    }

    /*
    * Tests the basic what is done.
    * More testing will be done once the feature is completely done
    * */
    @Test
    public void testFilter(){
        System.out.println("\nStarting testFilter...");

    }

    @Test
    public void testExerciseById(){
        System.out.println("\nStarting testExerciseById...");

        int lastId = 6; //Because there are 7 objects in total
        assertEquals("Exercise object obtained using id should be equal to the matching expected exercise object",exerciseHandler.getExerciseById(0),axe_Hold);
        assertNotEquals("Exercise object obtained using id should be equal to the matching expected exercise object",exerciseHandler.getExerciseById(3),axe_Hold);
        assertEquals("Exercise object obtained using id should be equal to the matching expected exercise object",exerciseHandler.getExerciseById(3),bench_press);
        assertEquals("Exercise object obtained using id should be equal to the matching expected exercise object",exerciseHandler.getExerciseById(lastId),calf_raises);
        System.out.println("Finish testExerciseById");
    }

    @Test
    public void testExerciseByName(){
        System.out.println("\nStarting testExerciseByName...");

        assertEquals("Exercise object obtained using name should be equal to the matching expected exercise object",exerciseHandler.getExerciseByName("axe Hold"),axe_Hold);
        assertNotEquals("Exercise object obtained using name should be equal to the matching expected exercise object",exerciseHandler.getExerciseByName("bench press"),axe_Hold);
        assertEquals("Exercise object obtained using name should be equal to the matching expected exercise object",exerciseHandler.getExerciseByName("bench press"),bench_press);
        assertEquals("Exercise object obtained using name should be equal to the matching expected exercise object",exerciseHandler.getExerciseByName("chin-ups"),exerciseHandler.getAllExercise().get(4) );

        System.out.println("Finished testExerciseByName");
    }

    @Test
    public void testInsertExercise(){
        System.out.println("\nStarting testInsertExercise...");

        //No new exercise objects created
        assertEquals("No new exercise objects inserted. There should be 7 objects",exerciseHandler.getAllExercise().size(),7);

        int original_size = 7;
        int lastId = 7;

        //Id should be 7
        Exercise new_1 = new Exercise(
                "I am new",
                "This is description\n" ,
                "Category",
                "Level",
                100
        );

        //ID should be 8
        Exercise new_2 = new Exercise(
                "I am new_2",
                "This is description\n" ,
                "Category",
                "Level",
                100
        );

        assertTrue("Inserted new object", exerciseHandler.insertExercise(new_1));
        assertEquals("Inserted new object. Id should be set to the next incremented id",exerciseHandler.getAllExercise().size()-1, new_1.getExerciseID());
        original_size++;
        assertEquals("Inserted one new exercise object. Size should increased by 1",exerciseHandler.getAllExercise().size(), original_size);

        assertTrue("Inserted new object", exerciseHandler.insertExercise(new_2));
        assertEquals("Inserted new object. Id should be set to the next incremented id",exerciseHandler.getAllExercise().size()-1, new_2.getExerciseID());
        original_size++;
        assertEquals("Inserted one new exercise object. Size should increased by 1",exerciseHandler.getAllExercise().size(), original_size);

        System.out.println("Finished testInsertExercise");
    }

    @Test
    public void testDeleteExerciseById(){
        System.out.println("\nStarting testDeleteExerciseById...");
        final int original_size = 7;
        final List<Exercise> original_list = exerciseHandler.getAllExercise();

        int size = 7;
        int lastId = 6;
        assertEquals("No exercise objects deleted. Should have the right count",exerciseHandler.getAllExercise().size(), size);

        //should be id 7
        Exercise new_1 = new Exercise(
                "I am new",
                "This is description\n" ,
                "Category",
                "Level",
                100
        );

        //should be id 8
        Exercise new_2 = new Exercise(
                "I am new_2",
                "This is description\n" ,
                "Category",
                "Level",
                100
        );

        assertTrue("Inserted exercise object",exerciseHandler.insertExercise(new_1));
        size++;
        assertTrue("Exercise object deleted using id.",exerciseHandler.deleteExerciseById(new_1.getExerciseID()));
        size--;
        assertEquals("Exercise object deleted using id. Size should decrease respectively",exerciseHandler.getAllExercise().size(),size);

        assertTrue("Inserted exercise object",exerciseHandler.insertExercise(new_2));
        size++;
        System.out.println(exerciseHandler.getExerciseById(8));
        System.out.println(new_2.getExerciseID());
        System.out.println(exerciseHandler.getAllExercise().toString());
        assertTrue("Exercise object deleted using id.",exerciseHandler.deleteExerciseById(new_1.getExerciseID()));
        size--;
        assertEquals("Exercise object deleted using id. Size should decrease respectively",exerciseHandler.getAllExercise().size(),size);

        assertEquals("Objects inserted and deleted respectively. Size should be equal to the original list's size",exerciseHandler.getAllExercise().size(),original_size);
        assertEquals("List of exercise should be the same, after inserting objects and deleting the respective objects",exerciseHandler.getAllExercise(), original_list);

        System.out.println("Finished testDeleteExerciseById");


    }

    @Test
    public void testDeleteExerciseByName(){
        System.out.println("\nStarting testDeleteExerciseByName...");
        final int original_size = 7;
        final List<Exercise> original_list = exerciseHandler.getAllExercise();

        int size = 7;
        int lastId = 6;

        //should be id 7
        Exercise new_1 = new Exercise(
                "I am new",
                "This is description\n" ,
                "Category",
                "Level",
                100
        );

        //should be id 8
        Exercise new_2 = new Exercise(
                "I am new_2",
                "This is description\n" ,
                "Category",
                "Level",
                100
        );

        assertTrue("Inserted exercise object",exerciseHandler.insertExercise(new_1));
        size++;
        assertTrue("Exercise object deleted using id.",exerciseHandler.deleteExerciseByName("I am new"));
        size--;
        assertEquals("Exercise object deleted using id. Size should decrease respectively",exerciseHandler.getAllExercise().size(),size);


        assertTrue("Inserted exercise object",exerciseHandler.insertExercise(new_2));
        size++;
        assertTrue("Exercise object deleted using title.",exerciseHandler.deleteExerciseByName("I am new_2"));
        size--;
        assertEquals("Exercise object deleted using title. Size should decrease respectively",exerciseHandler.getAllExercise().size(),size);

        assertEquals("Objects inserted and deleted respectively. Size should be equal to the original list's size",exerciseHandler.getAllExercise().size(),original_size);
        assertEquals("List of exercise should be the same, after inserting objects and deleting the respective objects",exerciseHandler.getAllExercise(), original_list);


        System.out.println("Finished testDeleteExerciseByName");
    }


}
