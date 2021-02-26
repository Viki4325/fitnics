package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.objects.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeActivity extends AppCompatActivity {

    private AccessUsers accessUsers;
    private AccessFoodLogs accessFoodLogs;
    private AccessExerciseLogs accessExerciseLogs;
    private User selectedUser;
    private MyDate dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
        accessExerciseLogs = new AccessExerciseLogs();
//        dateToday = new MyDate(Calendar.getInstance());
        dateToday = new MyDate(new GregorianCalendar(2021, 0, 1));
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        selectedUser = accessUsers.getUserByName(username);

        int intake = calculateIntake();
        int burned = calculateBurned();
        paintGreeting();
        paintCalorieGoal();
        paintCalorieRemaining(intake, burned);
    }

    private int calculateIntake() {
        return accessFoodLogs.getUserTotalDailyIntake(selectedUser.getUserID(), dateToday);
    }

    private int calculateBurned() {
        return accessExerciseLogs.getUserTotalDailyBurned(selectedUser.getUserID(), dateToday);
    }

    private void paintGreeting() {
        TextView textGreeting = (TextView)findViewById(R.id.textGreeting);
        textGreeting.setText("Welcome, " + selectedUser.getUsername());
    }

    private void paintCalorieGoal() {
        TextView textGoal = findViewById(R.id.textGoal);
        textGoal.setText("Goal: " + selectedUser.getDailyCaloricNeeds());
    }

    private void paintCalorieRemaining(int intake, int burned) {
        TextView textRemaining = findViewById(R.id.textRemaining);
        int goal = selectedUser.getDailyCaloricNeeds();
        int remaining = goal - intake + burned;
        textRemaining.setText("Remaining: " + remaining);
    }


    public void btnSearchFoodOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, SearchFoodActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

    public void btnFoodLogOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, FoodLogActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

    public void btnSearchExerciseOnClick(View v) {
        Intent exerciseFinder = new Intent(HomeActivity.this, searchExerciseActivity.class);
        startActivity(exerciseFinder);
    }

    public void btnExerciseLogOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, AddExerciseActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

}
