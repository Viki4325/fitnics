package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group12.fitnics.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void btnSetGoal(View v) {
        int goal = -1;
        if(v == findViewById(R.id.loseWeightbtn))
            goal = 0;
        else if(v == findViewById(R.id.maintianWeightbtn))
            goal = 1;
        else if(v == findViewById(R.id.gainWeightbtn))
            goal = 2;
        Intent SignUpInfo = new Intent(this, SignUpInfoActivity.class);
        SignUpInfo.putExtra("goal",goal);
        startActivity(SignUpInfo);
    }
}