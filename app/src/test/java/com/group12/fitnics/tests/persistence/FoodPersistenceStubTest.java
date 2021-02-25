package com.group12.fitnics.tests.persistence;

import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.FoodPersistence;
import com.group12.fitnics.persistence.FoodPersistenceStub;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class FoodPersistenceStubTest {
    private Food food;
    FoodPersistenceStub foodPersistenceStub;
    @Before
    public void setUp() throws Exception {
        food = new Food(10, "Orange", 1.5);
        foodPersistenceStub = new FoodPersistenceStub();
    }

    @Test
    public void testGetFoodSequential(){
        ArrayList<Food> list = foodPersistenceStub.getFoodSequential();
        assertNotNull(list);
    }
    @Test
    public void testGetFoodByID(){
        food = foodPersistenceStub.getFoodByID(1);
        assertEquals(food.getFoodID(), 1);
        assertSame(food.getName(), "Bread");
        assertEquals(food.getCalories(), 2.33,0.01);
    }
    @Test
    public void testGetFoodByFoodName(){
        food = foodPersistenceStub.getFoodByFoodName("Bread");

        assertEquals(food.getFoodID(), 1);
        assertSame(food.getName(), "Bread");
        assertEquals(food.getCalories(), 2.33,0.01);
    }
    @Test
    public void testInsertFood(){
        foodPersistenceStub.insertFood(food);
        Food test = foodPersistenceStub.getFoodByFoodName("Orange");
        assertEquals(7, foodPersistenceStub.getFoodSequential().size());
        assertSame("Orange", test.getName());
        assertEquals(10, test.getFoodID());
        assertEquals(1.5, test.getCalories(),0.1);
    }
    @Test
    public void testUpdateFood(){
        Food food = new Food(10, "Orange", 5.5);
        foodPersistenceStub.updateFood(6,food);
        Food test = foodPersistenceStub.getFoodByFoodName("Orange");
        assertEquals("Orange", test.getName());
        assertEquals(6, test.getFoodID());
        assertEquals(5.5, test.getCalories(),0.1);
    }
    @Test
    public void testDeleteFood(){
        assertTrue(foodPersistenceStub.deleteFood(6));
        assertNull(foodPersistenceStub.getFoodByID(6));
    }
}
