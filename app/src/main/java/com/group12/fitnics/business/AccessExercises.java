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
        List<Exercise> exerciseList = exercisePersistence.getExerciseList();
        return exerciseList;
    }

    public void filter(String[] filterOptionsChose){
        for (String s : filterOptionsChose) {
            if (s != null) {
                setFilter(s);
            }
        }
    }

    /*
    * This method takes in List of exercises.
    * This is because, the plan is for it to work with an already filtered list.
    * */
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

   public List<Exercise> getExerciseByCategory(String category){
        if(category != null){
            return exercisePersistence.getExerciseByCategory(category);
        }
        return null;
   }

   public List<Exercise> getExerciseByLevel(String level){
        if(level != null){
            return exercisePersistence.getExerciseByLevel(level);
        }
        return null;
   }

   public Exercise getExerciseById(int exerciseId){
        if(exerciseId > 0){
            return exercisePersistence.getExerciseById(exerciseId);
        }
        return null;
   }

   public boolean insertExercise(Exercise exercise){
        if(exercise != null){
            return exercisePersistence.insertExercise(exercise);
        }
        return false;
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
