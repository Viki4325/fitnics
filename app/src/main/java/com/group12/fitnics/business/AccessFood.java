package com.group12.fitnics.business;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.FoodNotFoundException;
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
        return foodList.insertFood(newFood);
    }

    public void deleteFood(int foodID) throws InvalidFoodException, FoodNotFoundException {
        foodList.deleteFood(foodID);
    }

    public void updateFood(int foodID, Food newFood) throws FoodNotFoundException {
        foodList.updateFood(foodID, newFood);
    }
}
