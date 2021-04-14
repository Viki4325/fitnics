package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group12.fitnics.R;
import com.group12.fitnics.enums.Goal;

public class SignUpActivity extends AppCompatActivity {

    private Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void btnLoseWeight(View v) {
        goal = Goal.GOAL_LOSE;
        goSignUpInfoScreen();
    }

    public void btnMaintainWeight(View v) {
        goal = Goal.GOAL_MAINTAIN;
        goSignUpInfoScreen();
    }

    public void btnGainWeight(View v) {
        goal = Goal.GOAL_GAIN;
        goSignUpInfoScreen();
    }

    public void goSignUpInfoScreen() {
        Intent intent = new Intent(this, SignUpInfoActivity.class);
        intent.putExtra("goal", goal.getValue());
        startActivity(intent);
    }
}