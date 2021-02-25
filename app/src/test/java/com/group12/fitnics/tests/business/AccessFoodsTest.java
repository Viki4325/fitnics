package com.group12.fitnics.tests.business;

import org.junit.Before;
import org.junit.Test;

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
    public void TestSearchByFoodName(){
        food = accessFood.searchByFoodName("Bread");
        assertEquals(food.getFoodID(),1);
        assertEquals(food.getCalories(), 2.33,0.01);
        assertSame(food.getName(), "Bread");
    }
    @Test
        // test search by food id
    public void TestSearchByFoodID(){
        food = accessFood.searchByFoodID(2);
        assertEquals(food.getFoodID(),2);
        assertEquals(food.getCalories(), 0.53,0.01);
        assertSame(food.getName(), "Nonfat Greek yogurt");
    }

    @Test
    public void TestAddFood(){
        accessFood.addFood(food);
        Food test = accessFood.searchByFoodID(10);
        assertEquals(test.getFoodID(),10);
        assertEquals(test.getCalories(), 1.5,0.01);
        assertSame(test.getName(), "Orange");
    }

    @Test
    // test delete food
    public void TestDeleteFood(){
        accessFood.addFood(food);
        assertTrue(accessFood.deleteFood(food));
        assertNull(accessFood.searchByFoodID(10));
    }
    @Test
    public void TestUpdateFood(){
        food = new Food(5, "Steamed rice", 1.93);
        food.setCalories(10);
        accessFood.updateFood(food);
        assertEquals(accessFood.searchByFoodID(5).getCalories(), 10,0.01);
    }
}
