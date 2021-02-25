package com.group12.fitnics.tests.objects;

import org.junit.Before;
import org.junit.Test;

import com.group12.fitnics.objects.Food;

import static org.junit.Assert.*;

public class FoodTest {
    private Food food;

    @Before
    public void setUp() throws Exception {
        food = new Food(10, "Orange", 1.5);
    }

    @Test
    public void testConstructor() {
        assertNotNull(food);
    }

    @Test
    public void testGetFoodID() {
        assertEquals(10, food.getFoodID());
    }

    @Test
    public void testGetName() {
        assertEquals("Orange", food.getName());
    }
    @Test
    public void testGetCalories() {
        assertEquals(1.5, food.getCalories(),0.1);
    }
    @Test
    public void testSetFoodID() {
        food.setFoodID(123456789);
        assertEquals(123456789, food.getFoodID());
    }
    @Test
    public void testSetName() {
        food.setName("ABCDEFG");
        assertEquals("ABCDEFG", food.getName());
    }
    @Test
    public void testSetCalories() {
        food.setCalories(9999.99);
        assertEquals(9999.99, food.getCalories(),0.01);
    }


}