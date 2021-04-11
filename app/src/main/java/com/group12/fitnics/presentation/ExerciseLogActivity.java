package com.group12.fitnics.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.User.User;
import com.group12.fitnics.presentation.adapters.ExerciseLogItemAdapter;

import java.time.LocalDate;


public class ExerciseLogActivity extends AppCompatActivity {
    ListView exerciseLogListView;
    private AccessExerciseLogs log;
    private User userLoggedIn;
    private LocalDate date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_log);

        log  = new AccessExerciseLogs();
        date = LocalDate.now();

        getUserLoggedIn();
        setupList();
        setUpOnClickListener();
    }


    private void setupList(){
        exerciseLogListView = (ListView) findViewById(R.id.exerciseLog);
        ExerciseLogItemAdapter adapter = (ExerciseLogItemAdapter) new ExerciseLogItemAdapter(getApplicationContext(),0, log.getExerciseLogByUserDate(userLoggedIn.getUserID(), date));
        exerciseLogListView.setAdapter(adapter);

    }



    private void setUpOnClickListener(){
        exerciseLogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //To get the exercise we have selected
                ExerciseLog selectExerciseLog = (ExerciseLog) (exerciseLogListView.getItemAtPosition(position));
                

                //create intent to go next activity
                //We pass the whole objects through intent
                Intent showDetail = new Intent(getApplicationContext(), IndividualExerciseLogActivity.class);
                showDetail.putExtra("userLoggedIn", userLoggedIn);
                showDetail.putExtra("exerciseLogSelected",  selectExerciseLog);
                startActivity(showDetail);
            }
        });
    }

    /*
    * This method gets the user through the intent passed after logging in
    * and returns its id
    * */
    private void getUserLoggedIn(){
        userLoggedIn = (User) getIntent().getSerializableExtra("userLoggedIn");
    }

    /*
    * Updates info on this page
    * */
    @Override
    protected void onResume() {
        super.onResume();
        setupList();
    }


}