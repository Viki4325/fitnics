package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.objects.User;

import static com.group12.fitnics.business.UnitConverter.convertUnitToString;

public class UpdateUserInfoActivity extends AppCompatActivity {
    private AccessUsers accessUsers;
    private User selectedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        accessUsers = new AccessUsers();
        Intent intent = getIntent();
        String id = intent.getStringExtra("userID");

        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");
        selectedUser = accessUsers.getUserById(selectedUser.getUserID());

        setupUnitSwitch();
        setUpSpinner_gender();
        setUpSpinner_activity();
        setUpSpinner_goal();

        showUserName(selectedUser.getUsername());
        showUserGoal();
        showActivityLevel();
        showInitHeight(selectedUser.getHeight());
        showInitWeight(selectedUser.getWeight());

    }

    private void showUserName(String user_Name){
        TextView userName = findViewById(R.id.userName);
        userName.setText("");
        userName.setText(user_Name.toUpperCase());
    }

    private void showUserGoal(){
        String[] goal_array = getResources().getStringArray(R.array.goals);
        TextView goal = findViewById(R.id.goal);
        goal.setText("");
        goal.setText(goal_array[selectedUser.getGoal()]);
    }

    private void showActivityLevel(){
        String[] level_array = getResources().getStringArray(R.array.activityLevels);
        TextView activity = findViewById(R.id.activityLevel);
        activity.setText("");
        activity.setText(level_array[selectedUser.getActivityLevel()]);
    }

    private void showInitWeight(double weight_){
        EditText weight = findViewById(R.id.editWeight);
        weight.setText(String.valueOf(weight_).trim());
    }
    private void showInitHeight(double height_){
        EditText height = findViewById(R.id.editHeight);
        height.setText(String.valueOf(height_).trim());
    }

    //CAN GO TO USERS
    private String getWeightUnits(){
        int[] units = selectedUser.getUnits();
        String weightUnit = "";
        if(units[0] == 1){
            weightUnit =  "kgs";
        }else if(units[0] == 0){
            weightUnit = "lbs";
        }
        return weightUnit;
    }

    //cAN GO TO USERS
    private String getHeightUnits(){
        int[] units = selectedUser.getUnits();
        String heightUnit = "";
        if(units[0] == 1){
            heightUnit =  "fts";
        }else if(units[0] == 0){
            heightUnit = "cm";
        }
        return heightUnit;
    }

    private void callDialogMessages(String message){
        Messages.fatalError(this,message);

    }

    private void setupUnitSwitch(){
        ToggleButton weightSwitch = (ToggleButton) findViewById(R.id.weightUnitSwitch);
        weightSwitch.setText(getWeightUnits());
        weightSwitch.setTextOff("lbs");
        weightSwitch.setTextOn("kgs");

        weightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean convertToKgs) {
                try {
                    updateWeight(convertToKgs);
                }catch (Exception e){
                    callDialogMessages(e.getMessage());
                }

            }
        }) ;

        ToggleButton heightSwitch = (ToggleButton) findViewById(R.id.heightUnitSwitch);
        heightSwitch.setText(getHeightUnits());
        heightSwitch.setTextOff("cm");
        heightSwitch.setTextOn("ft");
        heightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean convertToFts) {
                try {
                    updateHeight(convertToFts);
                }catch (Exception e){
                    callDialogMessages(e.getMessage());
                }
            }
        });
    }


    private void updateWeight(boolean convertToKgs) {
        EditText weightData = (EditText) findViewById(R.id.editWeight);
        double read = 0.0;
        if(weightData.getText().toString().trim().equals("")){
            read = 0.0;
        }else{
            read = Double.parseDouble(weightData.getText().toString().trim());
        }
        if(convertToKgs){
            double toKg = UnitConverter.LBToKg(read);
            String toKgString = convertUnitToString(toKg,1);
            weightData.setText(toKgString);
        }else {
            double toLbs = UnitConverter.KGToLB(read);
            String toLbsString = convertUnitToString(toLbs, 1);
            weightData.setText(toLbsString);
        }

    }

    /*
     * IF heightSwitch is checked -> current unit is FT (Need to be converted to cm)
     * IF heightSwitch is unchecked -> current unit is cm (Need to be converted to FT)
     * */
    private void updateHeight(boolean convertToFts) {
        //by default user types in lbs, cm
        EditText heightData = (EditText) findViewById(R.id.editHeight);
        double read = 0.0;
        if(heightData.getText().toString().trim().equals("")){
            read = 0.0;
        }else{
            read = Double.parseDouble(heightData.getText().toString().trim());
        }
        if(convertToFts){
            double toFt = UnitConverter.CMToFT(read);
            String toFt_String = convertUnitToString(toFt,1);
            heightData.setText(toFt_String);

        }else{
            double toCm = UnitConverter.FTToCM(read);
            String toCm_String = convertUnitToString(toCm,1);
            heightData.setText(toCm_String);
        }

    }

    private void updateGender(){
        Spinner choice = (Spinner) findViewById(R.id.genderSpinner);
        selectedUser.setGender(choice.getSelectedItem().toString().charAt(0));
    }
    private void updateGoal(){
        Spinner choice = (Spinner) findViewById(R.id.goalSpinner);
        selectedUser.setGoal(choice.getSelectedItemPosition());
    }
    private void updateActivity(){
        Spinner choice = (Spinner) findViewById(R.id.activityLevelSpinner);
        selectedUser.setActivityLevel(choice.getSelectedItemPosition());
    }
    private void updateWeight(){
        EditText weightData = (EditText) findViewById(R.id.editWeight);
        selectedUser.setWeight(Double.parseDouble(weightData.getText().toString().trim()));
    }
    private void updateHeight(){
        EditText heightData = (EditText) findViewById(R.id.editHeight);
        selectedUser.setHeight(Double.parseDouble(heightData.getText().toString().trim()));
    }

    private void setUpSpinner_goal(){
        Spinner goals = (Spinner) findViewById(R.id.goalSpinner);
        ArrayAdapter<String> goalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.goals));
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goals.setAdapter(goalAdapter);
        goals.setSelection(selectedUser.getGoal());

    }

    private void setUpSpinner_activity(){
        Spinner activityLevel = (Spinner) findViewById(R.id.activityLevelSpinner);
        ArrayAdapter<String> activityLevelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activityLevels));
        activityLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevel.setAdapter(activityLevelAdapter);
        activityLevel.setSelection(selectedUser.getActivityLevel());
    }


    private void setUpSpinner_gender(){
        Spinner Gender = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Genders));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(genderAdapter);
        Gender.setSelection(getGender());
    }

    //CAN GO TO USERS
    private int getGender(){
        int selection = -1;
        if(selectedUser.getGender() == 'M'){
            selection = 0;
        }else if(selectedUser.getGender() == 'F'){
            selection = 1;
        }else if(selectedUser.getGender() == 'O'){
            selection = 2;
        }
        return selection;
    }


    public void btnSaveUpdatesOnClick(View v) {

        try {
            updateActivity();
            updateGender();
            updateGoal();
            updateHeight();
            updateWeight();

            accessUsers.updateUser(selectedUser.getUserID(),selectedUser);
            Intent logInToHomeIntent = new Intent(this, HomeActivity.class);
            logInToHomeIntent.putExtra("userLoggedIn",selectedUser);
            startActivity(logInToHomeIntent);
        }catch (Exception e){
            Messages.fatalError(this,e.getMessage());
        }

    }
}