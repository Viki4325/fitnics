package com.group12.fitnics.tests.business;

import org.junit.Before;
import org.junit.Test;

import com.group12.fitnics.exceptions.FoodNotFoundException;
import com.group12.fitnics.exceptions.InvalidFdNameException;
import com.group12.fitnics.exceptions.InvalidFoodException;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.persistence.IExercisePersistence;
import com.group12.fitnics.persistence.stub.FoodPersistenceStub;

import java.util.List;

import static org.junit.Assert.*;

public class AccessFoodsTest {
    private Food food;
    AccessFood accessFood;


    @Before
    public void setUp() {
        accessFood = new AccessFood(new FoodPersistenceStub());
        food = new Food(10, "Orange", 1.5);
    }

    @Test
    // test search by food name
    public void testSearchByFoodName(){
        System.out.println("\nStarting testSearchByFoodName");
        food = accessFood.searchByFoodName("Bread");
        assertEquals(food.getFoodID(),1);
        assertEquals(food.getCalories(), 2.33,0.01);
        assertEquals(food.getName(), "Bread");
        System.out.println("Finished testSearchByFoodName");
    }

    @Test
    public void testSetSearch(){
        System.out.println("\nStarting testSetSearch...");

        String[] testSearchStrings = {
                "bread",
                "non",
                "greek",
                "yoghurt"
        };

        assertNotNull(testSearchStrings);

        for (String testSearchPhrase : testSearchStrings) {
            accessFood.setSearchPhrase(testSearchPhrase);
            assertEquals("The search phrase has been set, therefore should be equal", accessFood.getSearchPhrase(), testSearchPhrase);
        }

        //Does not break if set to null
        accessFood.setSearchPhrase(null);
        assertNull("Search phrase was set to null. Should get a search phrase equal to null",accessFood.getSearchPhrase());

        System.out.println("\nFinished testSetSearch...");
    }

    @Test
    public void testGetSearch(){
        System.out.println("\nStarting testGetSearch");

        String search_Phrase_1 = "yoghurt";
        String search_phrase_2 = "EGGS";
        String search_phrase_3 = "greek";

        assertNull("Should be equal to null, since no search phrase has been set", accessFood.getSearchPhrase());
        //Get and compare taking into consideration the case
        accessFood.setSearchPhrase(search_Phrase_1);
        assertEquals("Search phrase has been set. Should not be equal to null",accessFood.getSearchPhrase(),search_Phrase_1);
        assertNotEquals("Search phrase has been set and compared with the opposite case(UPPERCASE/lowercase) of what was used to set",accessFood.getSearchPhrase(),search_Phrase_1.toUpperCase());

        accessFood.setSearchPhrase(search_phrase_2);
        assertEquals("Search phrase has been set. Should not be equal to null",accessFood.getSearchPhrase(),search_phrase_2);
        assertNotEquals("Search phrase has been set and compared with the opposite case(UPPERCASE/lowercase) of what was used to set",accessFood.getSearchPhrase(),search_phrase_2.toLowerCase());

        accessFood.setSearchPhrase(search_phrase_3);
        assertEquals("Search phrase has been set. Should not be equal to null",accessFood.getSearchPhrase(),search_phrase_3);
        assertNotEquals("Search phrase has been set and compared with the opposite case(UPPERCASE/lowercase) of what was used to set",accessFood.getSearchPhrase(),search_phrase_3.toUpperCase());

        //Null is returned if set a null phrase
        accessFood.setSearchPhrase(null);
        assertNull("Search phrase was set to null. Should get a search phrase equal to null",accessFood.getSearchPhrase());

        System.out.println("Finished testGetSearch");
    }

    @Test
    public void testgetFoodBySearch(){
        System.out.println("\nStarting testgetFoodBySearch...");

        List<Food> foodList =  accessFood.getFoodList();

        String[] searchPhrase = {
                "bre",
                "yog",
        };

        List<Food> searchResult;
        //brute-force testing
        accessFood.setSearchPhrase(searchPhrase[0]);
        searchResult = accessFood.getFoodBySearch(foodList);
        assertEquals("Set searchPhrase that should only return ONE food object, should contain 'bre' ", searchResult.size(), 2);
        assertEquals("Set searchPhrase that should only return ONE food object, should contain 'bread' ", searchResult.get(0).getName(), "Bread");
        assertEquals("Bread", searchResult.get(0).getName());
        assertEquals("Grilled chicken breast", searchResult.get(1).getName());

        accessFood.setSearchPhrase(searchPhrase[1]);
        searchResult = accessFood.getFoodBySearch(foodList);
        assertEquals("Set searchPhrase that should only return ONE food object, should contain 'yogh' ", searchResult.size(), 1);
        assertEquals("Set searchPhrase that should only return ONE food object, should contain 'yoghurt' ", searchResult.get(0).getName(), "Nonfat Greek yogurt");
        assertEquals("Nonfat Greek yogurt", searchResult.get(0).getName());

        System.out.println("Finished testFoodBySearch");
    }


    @Test
    // test search by food id
    public void testSearchByFoodID(){
        System.out.println("\nStarting testSearchByFoodID");
        food = accessFood.searchByFoodID(2);
        assertEquals(food.getFoodID(),2);
        assertEquals(food.getCalories(), 0.53,0.01);
        assertEquals(food.getName(), "Nonfat Greek yogurt");
        System.out.println("Finished testSearchByFoodID");
    }

    @Test
    public void testAddFood(){
        System.out.println("\nStarting testAddFood");
        int id = accessFood.addFood(food);
        Food test = accessFood.searchByFoodID(id);
        assertEquals(test.getFoodID(),id);
        assertEquals(test.getCalories(), 1.5,0.01);
        assertEquals(test.getName(), "Orange");

        accessFood.deleteFood(id);
        assertNull(accessFood.searchByFoodID(id));
        System.out.println("Finished testAddFood");
    }

    @Test(expected = InvalidFoodException.class)
    public void testAddFoodNull() {
        System.out.println("\nStarting testAddFoodNull");
        accessFood.addFood(null);
        System.out.println("Finished testAddFoodNull");
    }

    @Test(expected = InvalidFoodException.class)
    public void testAddDuplicateFood() {
        System.out.println("\nStarting testAddDuplicateFood");
        accessFood.addFood(new Food(11, "Bread",  3));
        System.out.println("Finished testAddDuplicateFood");
    }

    @Test(expected = InvalidFdNameException.class)
    public void testAddFoodLongName() {
        System.out.println("\nStarting testAddFoodLongName");
        accessFood.addFood(new Food(11, "12345678901234567890a",  3));
        System.out.println("Finished testAddFoodLongName");
    }

    @Test
    public void testDeleteFood(){
        System.out.println("\nStarting testDeleteFood");
        int id = accessFood.addFood(food);
        accessFood.deleteFood(id);
        assertNull(accessFood.searchByFoodID(id));
        System.out.println("Finished testDeleteFood");
    }

    @Test(expected = FoodNotFoundException.class)
    public void testDeleteNotFoundFood() {
        System.out.println("\nStarting testDeleteNotFoundFood");
        accessFood.deleteFood(88);
        System.out.println("Finished testDeleteNotFoundFood");
    }

    @Test
    public void testUpdateFood(){
        System.out.println("\nStarting testUpdateFood");
        food = new Food(5, "Steamed rice", 1.93);
        food.setCalories(10);
        accessFood.updateFood(5, food);
        assertEquals(accessFood.searchByFoodID(5).getCalories(), 10,0.01);
        food.setCalories(1.93);
        accessFood.updateFood(5, food);
        System.out.println("Finished testUpdateFood");
    }

    @Test(expected = InvalidFoodException.class)
    public void testUpdateInvalidFood() {
        System.out.println("\nStarting testUpdateInvalidFood");
        accessFood.updateFood(0, null);
        System.out.println("Finished testUpdateInvalidFood");
    }

    @Test(expected = FoodNotFoundException.class)
    public void testUpdateNotFoundFood() {
        System.out.println("\nStarting testUpdateNotFoundFood");
        accessFood.updateFood(88, new Food(88, "Roast beef", 5));
        System.out.println("Finished testUpdateNotFoundFood");
    }

}


