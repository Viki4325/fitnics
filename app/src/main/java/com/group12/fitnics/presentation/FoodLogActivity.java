package com.group12.fitnics.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.User;

import java.util.ArrayList;

public class FoodLogActivity extends AppCompatActivity {
    private User selectedUser;
    ListView listView;
    private FoodLog foodLog;
    ArrayAdapter<FoodLog> adapter;
    private AccessFoodLogs log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_log);

        getUserLoggedIn();
        log =  new AccessFoodLogs();;
//        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");

        listView = (ListView) findViewById(R.id.food_log);
        ArrayList<FoodLog> list = new ArrayList<FoodLog>(log.getFoodLogByUser(selectedUser.getUserID()));

        adapter = new ArrayAdapter<FoodLog>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        listView.setAdapter(adapter);
    }

    private void getUserLoggedIn(){
        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");
    }
}
