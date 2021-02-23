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
        dateToday = new MyDate(Calendar.getInstance());

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        selectedUser = accessUsers.getUserByName(username);

        int intake = calculateIntake();
        int burned = calculateBurned();
        paintGreeting();
        paintCalorieGoal();
        paintCalorieRemaining(intake, burned);
        paintCalorieFood(intake);
        paintCalorieExercise(burned);
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

    private void paintCalorieFood(int intake) {
        TextView textFoodCalories = findViewById(R.id.textFoodCalories);
        textFoodCalories.setText(intake);
    }

    private void paintCalorieExercise(int burned) {
        TextView textExerciseCalories = findViewById(R.id.textExerciseCalories);
        textExerciseCalories.setText(burned);

    }

    public void btnAddFoodOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, AddFoodActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

    public void btnAddExerciseOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, AddExerciseActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

    public void btnHistoryOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

    public void buttonProfileOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }
}