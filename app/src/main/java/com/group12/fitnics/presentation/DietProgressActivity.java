package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.ProgressObject;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.presentation.adapters.DietProgressAdapter;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class DietProgressActivity extends AppCompatActivity {

    private AccessUsers accessUsers;
    private AccessFoodLogs accessFoodLogs;

    private User selectedUser;

    private TextView userGoal;
    private TextView goalMessage;

    private ListView listView;

    private ArrayList<ProgressObject> dietProgressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_progress);

        //initialize
        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
        userLoggedIn();
        userGoal = findViewById(R.id.userGoal);
        goalMessage = findViewById(R.id.progress_message);
        listView = (ListView) findViewById(R.id.diet_progress_list);

        //set the message of diet progress
        setProgressMessage(selectedUser.getGoal());

        //Create the ArrayList of Progress Objects
        dietProgressList = setUpProgressObjectList();

        DietProgressAdapter adapter = new DietProgressAdapter(this, R.layout.progress_list_layout, dietProgressList);
        listView.setAdapter(adapter);

    }

    private void userLoggedIn(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        selectedUser = (User) intent.getSerializableExtra("userLoggedIn");
    }

    private void setProgressMessage(int goal) {
        if (goal == 0) {
            userGoal.setText("Lose Weight");
            goalMessage.setText("Consume up to " + (int) selectedUser.getDailyCaloricNeeds() + " kCal/day to lose minimum 1 lb/week");
        } else if (goal == 1) {
            userGoal.setText("Maintain Weight");
            goalMessage.setText("Consume around " + (int) selectedUser.getDailyCaloricNeeds() + " kCal/day to maintain your current weight");
        } else if (goal == 2) {
            userGoal.setText("Gain Weight");
            goalMessage.setText("Consume at least " + (int) selectedUser.getDailyCaloricNeeds() + " kCal/day to gain minimum 1 lb/week");
        }
    }

    private ArrayList<ProgressObject> setUpProgressObjectList() {
        ArrayList<ProgressObject> list = new ArrayList<ProgressObject>();
        LocalDate newDate = LocalDate.of(2021, 4, 1);
        while(!newDate.equals(LocalDate.now().plusDays(1))) {
            list.add(0, new ProgressObject(newDate, 500, 90, (int) selectedUser.getDailyCaloricNeeds()));
            newDate = newDate.plusDays(1);
        }

        return list;
    }
}