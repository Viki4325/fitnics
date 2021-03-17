package com.group12.fitnics.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group12.fitnics.R;
import com.group12.fitnics.objects.Exercise;

import java.util.List;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    public ExerciseAdapter(Context context, int resource, List<Exercise> exerciseList) {
        super(context, resource, exerciseList);
    }

    @Override
    public View getView(int position  ,View convertView,  ViewGroup parent) {

        Exercise exercise = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.exercise_cell, parent, false);
        }

        TextView exerciseName = convertView.findViewById(R.id.exerciseName);
        ImageView imageView = convertView.findViewById(R.id.exerciseImage);

        exerciseName.setText(exercise.getTitle());
        imageView.setImageResource(R.drawable.ic_launcher_background);

        return convertView;
    }
}
