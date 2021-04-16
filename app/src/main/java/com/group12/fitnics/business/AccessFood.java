package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.FoodNotFoundException;
import com.group12.fitnics.exceptions.InvalidFdNameException;
import com.group12.fitnics.exceptions.InvalidFoodException;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.persistence.IFoodPersistence;

import java.util.ArrayList;
import java.util.List;

public class AccessFood{

    private IFoodPersistence foodList;
    private String searchPhrase;

    public AccessFood(IFoodPersistence foodList) {
        this.foodList = foodList;
    }

    public AccessFood(){
        foodList = Services.getFoodPersistence();
        searchPhrase = null;
    }

    public Food searchByFoodName(String target) {
        return foodList.getFoodByFoodName(target);
    }
    
    public Food searchByFoodID(int foodID) {
        return foodList.getFoodByID(foodID);
    }

    public List<Food> getFoodList() {
        return foodList.getFoodSequential();
    }

    /*
     * This method takes in List of food.
     * This is because, the plan is for it to work with an already filtered list.
     * */
    public List<Food> getFoodBySearch(List<Food> foodList){
        List<Food> searchResults = new ArrayList<>(foodList.size());

        if(searchPhrase != null){
            String search_Phrase = searchPhrase; //.toLowerCase();
            for (int i = 0; i <foodList.size() ; i++) {
                if(foodList.get(i).getName().toLowerCase().contains(search_Phrase)){
                    searchResults.add(foodList.get(i));
                }
            }
        }
        return searchResults;
    }

    public void setSearchPhrase(String searchTerm){
        searchPhrase = searchTerm;
    }

    public String getSearchPhrase(){
        return this.searchPhrase;
    }
    
    public int addFood(Food newFood) throws InvalidFoodException {
        if (newFood == null)
            throw new InvalidFoodException("The food is not valid. ");

        // if there exists same food name already, do not allow it to be inserted
        if (foodList.getFoodByFoodName(newFood.getName()) != null)
            throw new InvalidFoodException("There exists duplicate food. ");

        if(newFood.getName().length() > 20)
            throw new InvalidFdNameException("The name should be no more than 20 characters.");

        int newId = foodList.insertFood(newFood);
        newFood.setFoodID(newId);

        return newId;
    }

    public void deleteFood(int foodID) throws FoodNotFoundException {
        if (foodList.getFoodByID(foodID) == null)
            throw new FoodNotFoundException("There's no food with the foodID. ");

        foodList.deleteFood(foodID);
    }

    public void updateFood(int foodID, Food newFood) throws InvalidFoodException, FoodNotFoundException {
        if (newFood == null)
            throw new InvalidFoodException("The food is not valid. ");

        if (foodList.getFoodByID(foodID) == null)
            throw new FoodNotFoundException("There's no food with the foodID. ");

        foodList.updateFood(foodID, newFood);
    }
}
