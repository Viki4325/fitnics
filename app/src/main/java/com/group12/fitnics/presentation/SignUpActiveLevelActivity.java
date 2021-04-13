package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.enums.ActivityLevel;
import com.group12.fitnics.objects.User;

public class SignUpActiveLevelActivity extends AppCompatActivity {

    private User newUser;
    private AccessUsers accessUsers;
    private ActivityLevel activityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_active_level);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        accessUsers = new AccessUsers();

        newUser = accessUsers.getUserByName(username);

    }

    //Probably make separate code or method for this.
    //Looks like code smell -> Coz of the if statements

    public void btnNotActive(View v){
        activityLevel = ActivityLevel.NOT_ACTIVE;
        setUserActivityLevel(activityLevel);
    }
    public void btnActive(View v){
        activityLevel = ActivityLevel.ACTIVE;
        setUserActivityLevel(activityLevel);
    }
    public void btnSomeActive(View v){
        activityLevel = ActivityLevel.SOMEWHAT_ACTIVE;
        setUserActivityLevel(activityLevel);
    }
    public void veryActive(View v){
        activityLevel = ActivityLevel.VERY_ACTIVE;
        setUserActivityLevel(activityLevel);
    }

    private void setUserActivityLevel(ActivityLevel userActivityLevel){
        newUser.setActivityLevel(userActivityLevel);
        accessUsers.updateUser(newUser.getUserID(), newUser);
        //After finishing the setting up and updating
        goToLogInScreen();
    }

    /*
    * Sends the user to the log in page after finishing the last step of signing up
    * */
    public void goToLogInScreen(){
        Intent logInToHomeIntent = new Intent(this, LogInActivity.class);
        AlertDialog welcome = Messages.welcome(this,"You are signed up. You will be redirected to the login page where you can login using your username. Thank you for signing up. \nYour goals are now our priority.","Welcome to Fitnics");
        welcome.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                logInToHomeIntent.putExtra("username", newUser.getUsername());
                logInToHomeIntent.putExtra("userID", Integer.toString(newUser.getUserID()));

                startActivity(logInToHomeIntent);
            }
        });
    }

}
