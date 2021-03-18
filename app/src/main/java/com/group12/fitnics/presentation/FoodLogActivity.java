package com.group12.fitnics.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.User;

import java.util.ArrayList;

public class FoodLogActivity extends AppCompatActivity {
    private final static int UPDATE_CODE = 30;
    private User selectedUser;
    ListView listView;
    private AccessFood foods;
    ArrayAdapter<FoodLog> adapter;
    private AccessFoodLogs log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_log);

        foods = new AccessFood();
        getUserLoggedIn();
        log =  new AccessFoodLogs();;

        setupList();
        setUpOnClickListener();
    }

    private void setupList(){
        listView = (ListView) findViewById(R.id.food_log);
        ArrayList<FoodLog> list = new ArrayList<FoodLog>(log.getFoodLogByUser(selectedUser.getUserID()));

        adapter = new ArrayAdapter<FoodLog>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        listView.setAdapter(adapter);
    }

    private void setUpOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //To get the food we have selected
                FoodLog selectFoodLog = (FoodLog) (listView.getItemAtPosition(position));
                Food foodFromLog = (Food) foods.getFoodList().getFoodByID(selectFoodLog.getFoodID());
                //create intent to go next activity
                //We pass the whole objects through intent
                Intent showDetail = new Intent(getApplicationContext(), IndividualFoodActivity.class);
                showDetail.putExtra("PURPOSE_CODE",UPDATE_CODE);
                showDetail.putExtra("userLoggedIn", selectedUser);
                showDetail.putExtra("foodGrams",selectFoodLog.getGrams());
                showDetail.putExtra("foodSelected",  foodFromLog);
                startActivity(showDetail);

            }
        });
    }


    private void getUserLoggedIn(){
        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");
    }

    /*
     * Updates info on this page
     * */
    @Override
    protected void onResume() {
        super.onResume();
        setupList();
    }
}
