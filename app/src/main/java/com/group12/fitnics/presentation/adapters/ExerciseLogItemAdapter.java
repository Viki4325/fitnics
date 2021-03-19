package com.group12.fitnics.presentation.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.ExerciseLog;

import java.util.List;

public class ExerciseLogItemAdapter extends ArrayAdapter<ExerciseLog> {
   //Is this fine???
    AccessExercises accessExercises = new AccessExercises();

    public ExerciseLogItemAdapter(Context context, int resource, List<ExerciseLog> exerciseLog) {
        super(context, resource, exerciseLog);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position ,View convertView,  ViewGroup parent) {

        ExerciseLog exerciseLog = getItem(position);
        //Should we make AccessExercises object or is there another way???
        Exercise exerciseObj = accessExercises.getExerciseById(exerciseLog.getExerciseID());

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.exercise_log_item, parent, false);
        }

        TextView exerciseName = convertView.findViewById(R.id.exerciseName);
        TextView exerciseCalories = convertView.findViewById(R.id.exerciseCalories);
        TextView exerciseTime = convertView.findViewById(R.id.exerciseTime);


        //set the calories to if 30min -> xxx cals what about Y min?
        exerciseCalories.setText(String.format("%s calories", exerciseObj.getCaloriesBurn()));
        exerciseName.setText(exerciseObj.getTitle());
        exerciseTime.setText(String.format("%d minutes", exerciseLog.getMinutes()));

        return convertView;
    }
}