package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.time.LocalDate;

public class DietProgressActivity extends AppCompatActivity {

    private AccessUsers accessUsers;
    private AccessFoodLogs accessFoodLogs;

    private User selectedUser;

    private TextView userGoal;
    private TextView goalMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_progress);

        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
        userLoggedIn();

        userGoal = findViewById(R.id.userGoal);
        goalMessage = findViewById(R.id.progress_message);

        if (selectedUser.getGoal() == 0) {
            userGoal.setText("Lose Weight");
            goalMessage.setText("Consume up to " + (int) selectedUser.getDailyCaloricNeeds() + " kCal/day to lose minimum 1 lb/week");
        } else if (selectedUser.getGoal() == 1) {
            userGoal.setText("Maintain Weight");
            goalMessage.setText("Consume around " + (int) selectedUser.getDailyCaloricNeeds() + " kCal/day to maintain your current weight");
        } else if (selectedUser.getGoal() == 2) {
            userGoal.setText("Gain Weight");
            goalMessage.setText("Consume at least " + (int) selectedUser.getDailyCaloricNeeds() + " kCal/day to gain minimum 1 lb/week");
        }
    }

    private void userLoggedIn(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        selectedUser = (User) intent.getSerializableExtra("userLoggedIn");
    }
}