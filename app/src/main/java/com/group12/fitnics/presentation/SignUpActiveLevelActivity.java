package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User;

public class SignUpActiveLevelActivity extends AppCompatActivity {

    private User newUser;
    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_active_level);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        accessUsers = new AccessUsers();
        newUser = accessUsers.getUserByName(username);

    }

    public void btnComplete(View v)
    {
        int activityLevel = -1;
        if(v == findViewById(R.id.NotActivebtn))
            activityLevel = 0;
        else if(v == findViewById(R.id.SomewhatActivebtn))
            activityLevel = 1;
        else if(v == findViewById(R.id.Activebtn))
            activityLevel = 2;
        else if(v == findViewById(R.id.VeryActivebtn))
            activityLevel = 3;

        newUser.setActivityLevel(activityLevel);

        Intent logInToHomeIntent = new Intent(this, HomeActivity.class);
        logInToHomeIntent.putExtra("username", newUser.getUsername());
        logInToHomeIntent.putExtra("userID", Integer.toString(newUser.getUserID()));
        startActivity(logInToHomeIntent);
    }
}
