//package com.group12.fitnics.presentation;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.group12.fitnics.R;
//
//public class HomeActivity extends AppCompatActivity {
//
//    private TextView textGreeting;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        textGreeting = (TextView)findViewById(R.id.textGreeting);
//
//        Intent intent = getIntent();
//        String username = intent.getStringExtra("username");
//        String userID = intent.getStringExtra("userID");
//        textGreeting.setText("Welcome, " + username + " (" + userID + ")");
//    }
//}
package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.objects.User;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private AccessUsers accessUsers;
    private AccessFoodLogs accessFoodLogs;
    //private AccessExerciseLogs accessExerciseLogs;
    private AccessFood accessFood;
    private User selectedUser;
    private MyDate dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        accessUsers = new AccessUsers();
        accessFoodLogs = new AccessFoodLogs();
       // accessExerciseLogs = new AccessExerciseLogs();
        accessFood = new AccessFood();
        dateToday = new MyDate(Calendar.getInstance());

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        selectedUser = accessUsers.getUserByName(username);

        paintGreeting();
    }

    private void paintGreeting() {
        TextView textGreeting = (TextView)findViewById(R.id.textGreeting);
        textGreeting.setText("Welcome, " + selectedUser.getUsername());
    }

    public void btnFoodLog(View v){
        Intent intent = new Intent(HomeActivity.this, FoodLogActivity.class);
        intent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(intent);
    }


    public void btnSearchFoodOnClick(View v) {
        Intent foodFinder = new Intent(HomeActivity.this,SearchFoodActivity.class);
        startActivity(foodFinder);
    }
}
