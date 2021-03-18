package com.group12.fitnics.persistence;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.group12.fitnics.exceptions.ExerciseNotFoundException;
import com.group12.fitnics.exceptions.InvalidExNameException;
import com.group12.fitnics.exceptions.InvalidExerciseException;
import com.group12.fitnics.objects.Exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExercisePersistenceStub implements IExercisePersistence {
    private List<Exercise> exerciseList;

    public ExercisePersistenceStub(){
        this.exerciseList = new ArrayList<>();

        Exercise axe_Hold = new Exercise(
                "Axe Hold",
                "Grab dumbbells and extend arms to side and hold as long as you can",
                "Arms",
                "Intermediate",
                 50
        );

        Exercise  braced_squat = new Exercise(
                "Brace Squat",
                "Stand with feet slightly wider than shoulder-width apart, while standing as tall as you can. \n" +
                "Grab a weight plate and hold it out in front of your body with arms straight out. Keep your core tight and stand with a natural arch in your back. \n" +
                "Now, push hips back and bend knees down into a squat as far as you can. Hold for a few moments and bring yourself back up to the starting position.\n",
                "Legs",
                "Intermediate",
                22
        );

        Exercise flutter_kicks = new Exercise(
                "Flutter Kicks",
                "Laying on the back, lift your straightened legs from the ground at a 45 degree angle.\n"+
                "As your Left foot travels downward and nearly touches the floor, your Right foot should seek to reach a 90 degree angle, or as close to one as possible.\n"+
                "Bring your R foot down until it nearly touches the floor, and bring your L foot upwards.  Maintain leg rigidity throughout the exercise.  Your head should stay off the ground, supported by tightened upper abdominals.\n" +
                "(L up R down, L down R up, x2)  ^v, v^, ^v, v^ = 1 rep\n" ,
                "Abs",
                "Beginner",
                120
        );

        Exercise bench_press = new Exercise(

                "Bench Press",
                "Lay down on a bench, the bar should be directly above your eyes, the knees are somewhat angled and the feet are firmly on the floor. " +
                        "Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. " +
                        "Push the bar up. \n" +
                "If you train with a high weight it is advisable to have a spotter that can help you up if you can't lift the weight on your own.\n"+
                "With the width of the grip you can also control which part of the chest is trained more:\n" +
                        "\n" +
                        "\t\twide grip: outer chest muscles\n" +
                        "\t\tnarrow grip: inner chest muscles and triceps",
                "Chest",
                "Beginner",
                532
        );

        Exercise chin_ups = new Exercise(

                "Chin-Ups",
                "Like pull-ups but with a reverse grip\n" ,
                "Back",
                "Beginner",
                1
        );

        Exercise shrugs = new Exercise(

                "Shrugs,Dumbbells",
                "Stand with straight body, the hands are hanging freely on the side and hold each a dumbbell. Lift from this position the shoulders as high as you can, but don't bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.\n" +
                "When training with a higher weight, make sure that you still do the whole movement!\n" ,
                "Shoulders",
                "Expert",
                100
        );

        Exercise calf_raises = new Exercise(

                "Calf Raises",
                 "Calf raises are a method of exercising the gastrocnemius, tibialis posterior and soleus muscles of the lower leg. The movement performed is plantar flexion, a.k.a. ankle extension.\n",
                "Calves",
                "Expert",
                85
        );

        axe_Hold.setExerciseID(0);
        braced_squat.setExerciseID(1);
        flutter_kicks.setExerciseID(2);
        bench_press.setExerciseID(3);
        chin_ups.setExerciseID(4);
        shrugs.setExerciseID(5);
        calf_raises.setExerciseID(6);

        exerciseList.add(axe_Hold);
        exerciseList.add( braced_squat);
        exerciseList.add(flutter_kicks);
        exerciseList.add(bench_press);
        exerciseList.add(chin_ups);
        exerciseList.add (shrugs);
        exerciseList.add(calf_raises);


    }

    /*
     * Returns list sorted alphabetically by default
     * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Exercise> getExerciseList() {

        List<Exercise> exerciseSortedAlpha = new ArrayList<>(exerciseList.size());

        exerciseSortedAlpha.addAll(exerciseList);

        Collections.sort(exerciseSortedAlpha, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise exercise1, Exercise exercise2) {
                return (exercise1.getTitle()).compareToIgnoreCase(exercise2.getTitle());
            }
        });

        return exerciseSortedAlpha;
    }

    @Override
    public Exercise getExerciseById(int exerciseID) {
        for (int i = 0; i < exerciseList.size() ; i++) {
            if(exerciseList.get(i).getExerciseID() == exerciseID){
                return exerciseList.get(i);
            }
        }
        return null;
    }

    @Override
    public Exercise getExerciseByName(String exerciseName) {
        Exercise result = null;
        for (int i = 0; i < exerciseList.size() ; i++) {
            if(exerciseList.get(i).getTitle().equalsIgnoreCase(exerciseName)){
                result = exerciseList.get(i);
            }
        }
        return result;
    }

    @Override
    public List<Exercise> getExerciseByLevel(String exerciseLevel) {
        List<Exercise> exerciseListByLevel = new ArrayList<>();

        for (int i = 0; i < exerciseList.size(); i++) {
            if(exerciseList.get(i).getLevel().equalsIgnoreCase(exerciseLevel)){
                exerciseListByLevel.add(exerciseList.get(i));
            }
        }
        return exerciseListByLevel;
    }

    @Override
    public List<Exercise> getExerciseByCategory(String exerciseCategory) {
        List<Exercise> exerciseListByCategory = new ArrayList<>();

        for (int i = 0; i < exerciseList.size(); i++) {
            if(exerciseList.get(i).getCategory().equalsIgnoreCase(exerciseCategory)){
                exerciseListByCategory.add(exerciseList.get(i));
            }
        }
        return exerciseListByCategory;
    }

    @Override
    public int insertExercise(Exercise newExercise) throws InvalidExerciseException {
        if(newExercise == null)
            throw new InvalidExerciseException("The exercise is not valid. ");
        if(newExercise.getTitle().length() > 20)
            throw new InvalidExNameException("The name should be no more than 20 characters.");
        if(newExercise.getDescription().length() > 1024)
            throw new InvalidExNameException("The description should be no more than 1024 characters.");
        if(newExercise.getLevel().length() > 20)
            throw new InvalidExNameException("The level should be no more than 20 characters.");
        if(newExercise.getCategory().length() > 20)
            throw new InvalidExNameException("The category should be no more than 20 characters.");

        newExercise.setExerciseID();
        exerciseList.add(newExercise);

        return newExercise.getExerciseID();
    }

    /*
    * Only if an exercise Item exists, then it can be updated
    * */
    @Override
    public void updateExercise(int exerciseID, Exercise currentExercise) throws InvalidExerciseException, ExerciseNotFoundException {
        if(currentExercise == null)
            throw new InvalidExerciseException("The exercise is not valid. ");

        boolean found = false;
        for(int i = 0; i < exerciseList.size() && !found; i++) {
            if(exerciseList.get(i).getExerciseID() == exerciseID) {
                Exercise prevExercise = exerciseList.get(i);
                currentExercise.setExerciseID(currentExercise.getExerciseID());
                exerciseList.set(i, currentExercise);
                found = true;
            }
        }
        if(!found)
            throw new ExerciseNotFoundException("There's no exercise with the exerciseID. ");
    }

    @Override
    public void deleteExercise(int exerciseID) throws ExerciseNotFoundException {
        boolean found = false;
        for (int i = 0; i < exerciseList.size() && !found; i++) {
            if(exerciseList.get(i).getExerciseID() == exerciseID){
                exerciseList.get(i).updateId();
                exerciseList.remove(i);
                found = true;
            }
        }
        if(!found)
            throw new ExerciseNotFoundException("There's no exercise with the exerciseID. ");
    }


    @Override
    public void deleteExercise(Exercise currentExercise) throws ExerciseNotFoundException {
        boolean found = false;
        if(exerciseList.contains(currentExercise)){
            currentExercise.updateId();
            exerciseList.remove(currentExercise);
            found = true;
        }
        if(!found)
            throw new ExerciseNotFoundException("There's no exercise to delete. ");
    }
}
