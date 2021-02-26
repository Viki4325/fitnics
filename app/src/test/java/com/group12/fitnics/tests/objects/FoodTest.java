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
        System.out.println("\nStarting testConstructor");
        assertNotNull(food);
        System.out.println("Finished testConstructor");
    }

    @Test
    public void testGetFoodID() {
        System.out.println("\nStarting testGetFoodID");
        assertEquals(10, food.getFoodID());
        System.out.println("Finished testGetFoodID");
    }

    @Test
    public void testGetName() {
        System.out.println("\nStarting testGetName");
        assertEquals("Orange", food.getName());
        System.out.println("Finished testGetName");
    }
    @Test
    public void testGetCalories() {
        System.out.println("\nStarting testGetCalories");
        assertEquals(1.5, food.getCalories(),0.1);
        System.out.println("Finished testGetCalories");
    }
    @Test
    public void testSetFoodID() {
        System.out.println("\nStarting testSetFoodID");
        food.setFoodID(123456789);
        assertEquals(123456789, food.getFoodID());
        System.out.println("Finished testSetFoodID");
    }
    @Test
    public void testSetName() {
        System.out.println("\nStarting testSetName");
        food.setName("ABCDEFG");
        assertEquals("ABCDEFG", food.getName());
        System.out.println("Finished testSetName");
    }
    @Test
    public void testSetCalories() {
        System.out.println("\nStarting testSetCalories");
        food.setCalories(9999.99);
        assertEquals(9999.99, food.getCalories(),0.01);
        System.out.println("Finished testSetCalories");
    }
}
