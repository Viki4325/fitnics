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
import com.group12.fitnics.objects.User;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.time.LocalDate;

public class HomeActivity extends AppCompatActivity {

    private AccessUsers accessUsers;
    private AccessFoodLogs accessFoodLogs;
    private AccessExerciseLogs accessExerciseLogs;
    private ColorfulRingProgressView crpv;
    private int rem;
    private User selectedUser;
    private LocalDate dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
        accessExerciseLogs = new AccessExerciseLogs();

        dateToday = LocalDate.of(2021, 1, 1);
        userLoggedIn();
        rem = (int)selectedUser.getDailyCaloricNeeds();

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
        rem += burned;
        textTotalBurned.setText(String.valueOf(burned));
    }

    private void paintCaloriesConsumed(){
        TextView textTotalIntake = findViewById(R.id.textTotalIntake);
        int intake = calculateIntake();
        rem -= intake;
        textTotalIntake.setText(String.valueOf(intake ));
    }

    private void paintRemaining(){
        TextView textRemaining = findViewById(R.id.textRemaining);
        double goal = selectedUser.getDailyCaloricNeeds();
        int burned = calculateBurned();
        int intake = calculateIntake();
        int remaining = (int) (goal - intake + burned);
        textRemaining.setText( String.valueOf(remaining) );
    }

    /*
    * The method seems to have some logic
    * The logic is to convert the calories remaining to %, that all.
    * Therefore left this method in this class, instead of the logic layer
    * It is also used here only.
    * */
    private void printRingGraph(){
        int remaining_per = (int)(- calculateIntake() + calculateBurned());
        crpv.setPercent (remaining_per); //WE NEED A WAY TO CONVERT THE DIGITS TO % . An active listener that always listens for these changes

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

    public void btnSetNotificationsOnClick(View v){
        Intent intent = new Intent(HomeActivity.this, CreateNotificationActivity.class);
        intent.putExtra("userID",Integer.toString(selectedUser.getUserID()));
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
