package com.group12.fitnics.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.objects.User;

import java.util.Objects;

public class IndividualExercise extends AppCompatActivity implements View.OnClickListener{

    private AccessExerciseLogs exerciseLogs;
    private Exercise exerciseSelected;
    private User userLoggedIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_detail);
        Toolbar toolbar = findViewById(R.id.LogDetailToolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        FloatingActionButton addExercise = findViewById(R.id.add_button);
        addExercise.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        exerciseLogs = new AccessExerciseLogs();
        getExerciseSelected();
        getUserLoggedIn();

        setExerciseInfo();

    }

    private void getExerciseSelected(){
        exerciseSelected = (Exercise) getIntent().getSerializableExtra("exerciseSelected");
    }


    private void getUserLoggedIn(){
        userLoggedIn = (User) getIntent().getSerializableExtra("userLoggedIn");
    }

    /*
    * This method get's the Exercise object that was selected via the intent
    * It extracts the required details such as, Title, description, category, level
    * and sets them to the required views.
    * */
    private void setExerciseInfo(){
        final CollapsingToolbarLayout layout = findViewById(R.id.LogDetailCollapseToolBar);
        layout.setTitle(exerciseSelected.getTitle());

        final TextView title = findViewById(R.id.exerciseTitle);
        title.setText(exerciseSelected.getTitle());

        final TextView description = findViewById(R.id.exerciseDescription);
        description.setText(exerciseSelected.getDescription());

        final TextView category = findViewById(R.id.exerciseCategory);
        category.setText(exerciseSelected.getCategory());

        final TextView level = findViewById(R.id.exerciseLevel);
        level.setText(exerciseSelected.getLevel());
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {

        addExerciseToLogs();
    }

    private void addExerciseToLogs(){
        try {
            ExerciseLog exerciseLog = new ExerciseLog(userLoggedIn.getUserID(), exerciseSelected.getExerciseID(),MyDate.getCurrentDate(),30);
            exerciseLogs.insertExerciseLog(exerciseLog);
            Toast.makeText(getApplicationContext(), "Exercise added to your logs!",Toast.LENGTH_SHORT).show();
            //once you add an exercise go back to the list to select more
            onBackPressed();
        }catch (Exception e){
            Messages.alert(this,e.getMessage());
        }

    }
}
