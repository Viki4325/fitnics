package com.group12.fitnics.business;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.group12.fitnics.application.Services;
import com.group12.fitnics.exceptions.ExerciseNotFoundException;
import com.group12.fitnics.exceptions.InvalidExerciseException;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.persistence.IExercisePersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccessExercises {
    private final IExercisePersistence exercisePersistence;
    private String searchPhrase;
    private List<String> filter;

    public AccessExercises(IExercisePersistence exercisePersistence){
        this.exercisePersistence = exercisePersistence;
        filter = new ArrayList<>();
        searchPhrase = null;
    }

    public AccessExercises(){
        exercisePersistence = Services.getExercisePersistence();
        filter = new ArrayList<>();
        searchPhrase = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFilter(String newFilter){
        filter.add(newFilter);
        filter = filter.stream().distinct().collect(Collectors.toList());
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

    @RequiresApi(api = Build.VERSION_CODES.N)
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

   public Exercise getExerciseByName(String exerciseTitle){
        return exercisePersistence.getExerciseByName(exerciseTitle);
   }

    public void insertExercise(Exercise exercise) throws InvalidExerciseException {
        exercisePersistence.insertExercise(exercise);
    }

    /*
    * This method is an addition to the 2 basic ways of deleting exercise objects
    * */
   public void deleteExerciseByName(String exerciseName) throws InvalidExerciseException, ExerciseNotFoundException {
       if(exerciseName.equals(""))
           throw new InvalidExerciseException("The exercise name is not valid. ");

       Exercise result = exercisePersistence.getExerciseByName(exerciseName);
       if(result == null)
           throw new ExerciseNotFoundException("There's no exercise with the name. ");

       exercisePersistence.deleteExercise(result);
   }

    public void deleteExerciseById(int exerciseId) throws InvalidExerciseException, ExerciseNotFoundException {
        if(exerciseId < 0)
            throw new InvalidExerciseException("The exercise id is not valid. ");

        exercisePersistence.deleteExercise(exerciseId);
    }

}
