package com.group12.fitnics.tests.business;

import org.junit.Before;
import org.junit.Test;

import com.group12.fitnics.exceptions.FoodNotFoundException;
import com.group12.fitnics.exceptions.InvalidFdNameException;
import com.group12.fitnics.exceptions.InvalidFoodException;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.business.AccessFood;
import static org.junit.Assert.*;

public class AccessFoodsTest {
    private Food food;
    AccessFood accessFood;

    @Before
    public void setUp() throws Exception {
        accessFood = new AccessFood();
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


