package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.group12.fitnics.R;

public class HomeActivity extends AppCompatActivity {

    private TextView textGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textGreeting = (TextView)findViewById(R.id.textGreeting);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userID = intent.getStringExtra("userID");
        textGreeting.setText("Welcome, " + username + " (" + userID + ")");
    }
}