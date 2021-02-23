package com.group12.fitnics.business;

import android.util.Log;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.persistence.ExercisePersistence;

import java.util.ArrayList;
import java.util.List;

public class AccessExercises {
    private final ExercisePersistence exercisePersistence;
    private String searchPhrase;
    private List<String> filter;


    public AccessExercises(){
        exercisePersistence = Services.getExercisePersistence();
        filter = new ArrayList<>();
        searchPhrase = null;
    }

    public void setFilter(String newFilter){
        int index = filter.indexOf(newFilter);
        //check if the string filter already exists
        if(index != 0){
            filter.add(newFilter);
        }else{
            filter.remove(index);
        }
    }

    public List<String> getFilter(){
        return filter;
    }

    public void resetFilter(){
        filter = new ArrayList<>();
    }

    public void setSearchPhrase(String searchTerm){
        searchPhrase = searchTerm;
    }

    public String getSearchPhrase(){
        return this.searchPhrase;
    }

    public void resetSearch(){
        searchPhrase = null;
    }

    /*
    * Sorted Alphabetically by default
    * */
    public List<Exercise> getAllExercise(){

        return exercisePersistence.getExerciseList();
    }

    public void filter(String[] filterOptionsChose){
        for (String s : filterOptionsChose) {
            if (s != null) {
                setFilter(s);
            }
        }
    }

   public List<Exercise> getExerciseBySearch(List<Exercise> exerciseList){
        List<Exercise> searchResults = new ArrayList<>(exerciseList.size());

        if(searchPhrase != null){
            String search_Phrase = searchPhrase.toLowerCase();
            for (int i = 0; i <exerciseList.size() ; i++) {
                if(exerciseList.get(i).getTitle().toLowerCase().contains(search_Phrase)){
                    searchResults.add(exerciseList.get(i));
                }
            }
        }

        return searchResults;
   }

   public Exercise getExerciseById(int exerciseId){
      return exercisePersistence.getExerciseById(exerciseId);
   }

   public void deleteExercise(Exercise exercise){
       boolean deleted = false;
        if(exercise != null){
            deleted = exercisePersistence.deleteExercise(exercise);
        }
        if(deleted){
            Log.i("Deletion Status:","SUCCESSFULL");
        }else{
            Log.i("Deletion Status:", "FAILED TO DELETE");
        }
   }

    public void deleteExercise(int exerciseId){
        if(exerciseId >= 0){
            exercisePersistence.deleteExercise(exerciseId);
        }
    }
}
