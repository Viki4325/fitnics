package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User;

public class LogInActivity extends AppCompatActivity {

    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        accessUsers = new AccessUsers();
    }

    public void btnLogInToHomeOnClick(View v) {
        EditText editUsername = (EditText)findViewById(R.id.editUsername);
        User selectedUser = accessUsers.getRandom(editUsername.getText().toString());

        Intent logInToHomeIntent = new Intent(LogInActivity.this, HomeActivity.class);
        logInToHomeIntent.putExtra("username", selectedUser.getUsername());
        logInToHomeIntent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
        startActivity(logInToHomeIntent);
    }
}