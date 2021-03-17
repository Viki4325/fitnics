package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.objects.User;

import java.io.Serializable;

public class LogInActivity extends AppCompatActivity {

    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        accessUsers = new AccessUsers();
    }

    public void btnLogInToHomeOnClick(View v) throws Exception {
        try {
            EditText editUsername = (EditText) findViewById(R.id.editUsername);
            User selectedUser = accessUsers.getUserByName(editUsername.getText().toString());
            if (selectedUser == null) {
                throw new Exception("Invalid User Name");
            }
            Intent logInToHomeIntent = new Intent(LogInActivity.this, HomeActivity.class);
            logInToHomeIntent.putExtra("username", selectedUser.getUsername());
            logInToHomeIntent.putExtra("userID", Integer.toString(selectedUser.getUserID()));
            logInToHomeIntent.putExtra("userLoggedIn",  selectedUser);
            startActivity(logInToHomeIntent);
        }
        catch (Exception e){
            Messages.fatalError(this,e.getMessage()+"\n No such user found.");

        }
    }
}