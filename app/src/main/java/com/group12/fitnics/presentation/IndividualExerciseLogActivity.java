package com.group12.fitnics.presentation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.User;

import java.util.Objects;

public class IndividualExerciseLogActivity extends AppCompatActivity {

    EditText minutes_editText;
    TextView calories_textView;

    private User userLoggedIn;
    private ExerciseLog exerciseLog_sel;
    private AccessExerciseLogs accessExerciseLogs;
    private AccessExercises accessExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_log_details);
        Toolbar toolbar = findViewById(R.id.logDetailToolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        FloatingActionButton addExercise = findViewById(R.id.update_Min);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get the user logged in and the exercise log chosen
        userLoggedIn = (User) getIntent().getSerializableExtra("userLoggedIn");
        exerciseLog_sel = (ExerciseLog) getIntent().getSerializableExtra("exerciseLogSelected");
        accessExercises = new AccessExercises();
        accessExerciseLogs = new AccessExerciseLogs();

        minutes_editText = (EditText) findViewById(R.id.exerciseMinutes);
        calories_textView = (TextView) findViewById(R.id.calories);
        displayMins();
        displayLog();
        displayCals(exerciseLog_sel.getMinutes());

        minutes_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                //if after editing, the editText has nothing then display 0
                String inputText = minutes_editText.getText().toString();
                if(inputText.equals(""))
                {
                    displayCals(0);
                }else{
                    displayCals(Integer.parseInt(inputText));

                }
            }
        });
    }

    public void updateMin(View view) {
        updateExerciseLog();
        Toast.makeText(this,"Time updated.",Toast.LENGTH_SHORT).show();
        //Go back to exercise log page
        onBackPressed();
    }

    /*
    * Updating exercise log affects -> the minutes spent on an exercise
    *                               -> the calories the user has burnt (Invoke a method to receives the updates)
    * */
    private void updateExerciseLog(){
        try {
            int min = getExerciseMins();
            int user_id = userLoggedIn.getUserID();
            ExerciseLog updatedExerciseLog = new ExerciseLog(exerciseLog_sel.getUserID(), exerciseLog_sel.getExerciseID(), exerciseLog_sel.getDate(), min);
            accessExerciseLogs.updateExerciseLog(user_id, exerciseLog_sel.getExerciseID(), exerciseLog_sel.getDate(), updatedExerciseLog);
        }catch (Exception e){
            Messages.fatalError(this, e.getMessage());
        }

    }

    /*
    * Definitely need refactoring as there is logic here
    * */
    private void displayCals(int min){
        int calsPerMin = accessExercises.getExerciseById( exerciseLog_sel.getExerciseID()).getCaloriesBurn();
        int total = calsPerMin * min;
        calories_textView.setText(String.valueOf(total));
    }

    private void displayLog(){
        final CollapsingToolbarLayout layout = findViewById(R.id.logDetailCollapseToolBar);
        layout.setTitle(accessExercises.getExerciseById(exerciseLog_sel.getExerciseID()).getTitle());
    }

    private void displayMins(){
        minutes_editText.setText(String.valueOf(exerciseLog_sel.getMinutes()));
    }

    private int getExerciseMins(){
        return Integer.parseInt(String.valueOf(minutes_editText.getText()));
    }



    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}