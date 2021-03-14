package com.group12.fitnics.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.objects.User;

import java.util.GregorianCalendar;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView navBar;
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
        paintCaloriesInfo(intake, burned);
    }


    private int calculateIntake() {
        return accessFoodLogs.getUserTotalDailyIntake(selectedUser.getUserID(), dateToday);
    }

    private int calculateBurned() {
        return accessExerciseLogs.getUserTotalDailyBurned(selectedUser.getUserID(), dateToday);
    }

    @SuppressLint("SetTextI18n")
    private void paintGreeting() {
        TextView textGreeting = (TextView)findViewById(R.id.textGreeting);
        textGreeting.setText("Welcome, " + selectedUser.getUsername());
    }

    private void paintCalorieGoal() {
//        TextView textGoal = findViewById(R.id.textGoal);
//        textGoal.setText("Goal: " + selectedUser.getDailyCaloricNeeds());
    }

    @SuppressLint("SetTextI18n")
    private void paintCaloriesInfo(int intake, int burned) {
        TextView textRemaining = findViewById(R.id.textRemaining);
        TextView textTotalIntake = findViewById(R.id.textTotalIntake);
        TextView textTotalBurned = findViewById(R.id.textTotalBurned);

        int goal = selectedUser.getDailyCaloricNeeds();
        int remaining = goal - intake + burned;
        textRemaining.setText( ""+remaining );
        textTotalIntake.setText(intake +"");
        textTotalBurned.setText(burned + "");
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
        Intent exerciseFinder = new Intent(HomeActivity.this, SearchExerciseActivity.class);
        startActivity(exerciseFinder);
    }

    public void btnExerciseLogOnClick(View v) {
        Intent intent = new Intent(HomeActivity.this, ExerciseLogActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }

    public void btnUpdateUserInfoOnClick(View v){
        Intent intent = new Intent(HomeActivity.this, UpdateUserInfoActivity.class);
        intent.putExtra("userID",Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }


    //click listner for the bottom navigation bar

}
