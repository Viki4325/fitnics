package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.FoodLog;
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
    private AccessFood accessFood;

    private User selectedUser;

    private TextView userGoal;
    private TextView goalMessage;

    private ListView listView;
    private DietProgressAdapter adapter;

    private ArrayList<ProgressObject> dietProgressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_progress);

        //initialize
        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
        accessFood = new AccessFood();
        userLoggedIn();
        userGoal = findViewById(R.id.userGoal);
        goalMessage = findViewById(R.id.progress_message);
        listView = (ListView) findViewById(R.id.diet_progress_list);

        //set the message of diet progress
        setProgressMessage(selectedUser.getGoal());

        //Create the ArrayList of Progress Objects
        dietProgressList = setUpProgressObjectList();

        adapter = new DietProgressAdapter(this, R.layout.progress_list_layout, dietProgressList);
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
        LocalDate newDate = LocalDate.of(2021, 4, 10);
        while(!newDate.equals(LocalDate.now().plusDays(1))) {
            int caloriesConsumed = calculateConsumption(newDate);
            System.out.println("-----------------------------------------------------------------Calories consumed:" + caloriesConsumed);
            list.add(0, new ProgressObject(newDate, caloriesConsumed, 0, (int) selectedUser.getDailyCaloricNeeds()));
            newDate = newDate.plusDays(1);
        }

        return list;
    }

    private int calculateConsumption(LocalDate date) {
        int totalCalories = 0;
        int grams = 0;
        double caloriesPerGram = 0;

        ArrayList<FoodLog> list = new ArrayList<>(accessFoodLogs.getFoodLogByUserDate(selectedUser.getUserID(), date));

        for(int i=0; i<list.size(); i++) {
            grams = list.get(i).getGrams();
            caloriesPerGram = accessFood.searchByFoodID(list.get(i).getFoodID()).getCalories();
            totalCalories += grams * caloriesPerGram;
        }

        return totalCalories;
    }
}