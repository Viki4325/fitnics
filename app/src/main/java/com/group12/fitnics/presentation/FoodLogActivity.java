package com.group12.fitnics.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.group12.fitnics.R;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.persistence.FoodLogPersistenceStub;

import java.util.ArrayList;

public class FoodLogActivity extends AppCompatActivity {
    User selectedUser;
    ListView listView;
    FoodLog foodLog;
    ArrayAdapter<FoodLog> adapter;
    FoodLogPersistenceStub log = new FoodLogPersistenceStub();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_log);


        Intent intent = getIntent();
        String id = intent.getStringExtra("userID");
        int userID = Integer.parseInt(id);

        listView = (ListView) findViewById(R.id.food_log);
        ArrayList<FoodLog> list = new ArrayList<FoodLog>(log.getFoodLogByUser(userID));

        adapter = new ArrayAdapter<FoodLog>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        listView.setAdapter(adapter);
    }
}
