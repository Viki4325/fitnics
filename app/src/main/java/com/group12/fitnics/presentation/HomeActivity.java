package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.GregorianCalendar;

public class HomeActivity extends AppCompatActivity {

    private AccessUsers accessUsers;
    private AccessFoodLogs accessFoodLogs;
    private AccessExerciseLogs accessExerciseLogs;
    private ColorfulRingProgressView crpv;
    private MyDate dateToday;
    private User selectedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
        accessExerciseLogs = new AccessExerciseLogs();
//        dateToday = new MyDate(Calendar.getInstance());
        dateToday = new MyDate(new GregorianCalendar(2021, 0, 1));
        userLoggedIn();

        paintGreeting();
        paintCaloriesConsumed();
        paintCaloriesBurned();
        paintRemaining();
        printRingGraph();
    }

    private void userLoggedIn(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        selectedUser = (User) intent.getSerializableExtra("userLoggedIn");
//        selectedUser = accessUsers.getUserByName(username);
    }

    /*
    * Calories of food consumed
    * */
    private int calculateIntake() {
        return accessFoodLogs.getUserTotalDailyIntake(selectedUser.getUserID(), dateToday);
    }

    /*
    * Calories burned by performing exercises
    * */
    private int calculateBurned() {
        return accessExerciseLogs.getUserTotalDailyBurned(selectedUser.getUserID(), dateToday);
    }

    @SuppressLint("SetTextI18n")
    private void paintGreeting() {
        TextView textGreeting = (TextView)findViewById(R.id.textGreeting);
        textGreeting.setText("Welcome, " + selectedUser.getUsername());
    }

    /*
    * These methods were separated and put as individual, because they are need in
    * onResume, which is critical in updating the info
    * */
    private void paintCaloriesBurned(){
        TextView textTotalBurned = findViewById(R.id.textTotalBurned);
        int burned = calculateBurned();
        textTotalBurned.setText(String.valueOf(burned));
    }

    private void paintCaloriesConsumed(){
        TextView textTotalIntake = findViewById(R.id.textTotalIntake);
        int intake = calculateIntake();
        textTotalIntake.setText(String.valueOf(intake ));
    }

    private void paintRemaining(){
        TextView textRemaining = findViewById(R.id.textRemaining);
        int goal = selectedUser.getDailyCaloricNeeds();
        int burned = calculateBurned();
        int intake = calculateIntake();
        int remaining = goal - intake + burned;
        textRemaining.setText( String.valueOf(remaining) );

    }

    /*
    * The method seems to have some logic
    * The logic is to convert the calories remaining to %, that all.
    * Therefore left this method in this class, instead of the logic layer
    * It is also used here only.
    * */
    private void printRingGraph(){
        int goal = selectedUser.getDailyCaloricNeeds(); //2500
        int burned = calculateBurned();
        int intake = calculateIntake();
        int remaining = goal - intake + burned;
//        int remaining_percent = ((goal - remaining)/goal)*100;
//        crpv.setPercent (remaining_percent); //WE NEED A WAY TO CONVERT THE DIGITS TO % . An active listener that always listens for these changes

    }

    public void btnSearchFoodOnClick(View v) {
        generateIntent(SearchFoodActivity.class);
    }

    public void btnFoodLogOnClick(View v) {
        generateIntent(FoodLogActivity.class);
    }

    public void btnSearchExerciseOnClick(View v) {
        generateIntent(SearchExerciseActivity.class);
    }

    public void btnExerciseLogOnClick(View v) {
        generateIntent(ExerciseLogActivity.class);
    }

    public void btnUpdateUserInfoOnClick(View v){
        generateIntent(UpdateUserInfoActivity.class);
    }

    /*
    * This method generates intent,and passes the current logged in user, since we need that info everywhere
    * */
    private void generateIntent ( Class to){

        Intent intent = new Intent(HomeActivity.this, to);
        intent.putExtra("userLoggedIn", selectedUser);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        paintCaloriesBurned();
        paintRemaining();
        paintCaloriesConsumed();
        printRingGraph();
    }
}
