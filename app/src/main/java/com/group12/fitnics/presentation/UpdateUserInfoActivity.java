 package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.enums.ActivityLevel;
import com.group12.fitnics.enums.Gender;
import com.group12.fitnics.enums.Goal;
import com.group12.fitnics.objects.User;

import static com.group12.fitnics.business.UnitConverter.convertUnitToString;

public class UpdateUserInfoActivity extends AppCompatActivity {
    private AccessUsers accessUsers;
    private User selectedUser;
    ToggleButton weightSwitch;
    ToggleButton heightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        accessUsers = new AccessUsers();
        weightSwitch = (ToggleButton) findViewById(R.id.weightUnitSwitch);
        heightSwitch = (ToggleButton) findViewById(R.id.heightUnitSwitch);


        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");
        selectedUser = accessUsers.getUserById(selectedUser.getUserID());


        setUnitsInSwitch();
        setupUnitSwitch();
        setUpSpinner_gender();
        setUpSpinner_activity();
        setUpSpinner_goal();

        showUserName(selectedUser.getUsername());
        showUserGoal();
        showActivityLevel();
        showInitHeight(selectedUser.getHeight());
        showInitWeight(selectedUser.getWeight());
        showToggleStates();

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
        goal.setText(goal_array[selectedUser.getGoal().getValue()]);
    }

    private void showActivityLevel(){
        String[] level_array = getResources().getStringArray(R.array.activityLevels);
        TextView activity = findViewById(R.id.activityLevel);
        activity.setText("");
        activity.setText(level_array[selectedUser.getActivityLevel().getValue()]);
    }

    private void showInitWeight(double weight_){
        EditText weight = findViewById(R.id.editWeight);
        weight.setText(String.valueOf(weight_).trim());
    }

    private void showInitHeight(double height_){
        EditText height = findViewById(R.id.editHeight);
        height.setText(String.valueOf(height_).trim());
    }

    private void showToggleStates(){
        weightSwitch.setChecked(loadToggleWeight(selectedUser.getUserID()));
        heightSwitch.setChecked(loadToggleHeight(selectedUser.getUserID()));

    }


    private void callDialogMessages(String message){
        Messages.fatalError(this,message);

    }

    /*
    * This methods sets the ON and OFF string values to be set for the switches
    * This is tricky, because the "purpose" of the switch keeps changing,
    * therefore there is no fixed string to be set.
    * Hence, this is controlled here
    * */
    private void setUnitsInSwitch(){
        weightSwitch.setTextOff("lbs"); //selectedUser.getWeightUnit()
        heightSwitch.setTextOff("ft"); //selectedUser.getHeightUnit()

        weightSwitch.setTextOn("kgs"); //negateWeightUnit()
        heightSwitch.setTextOn("cm"); //negateHeightUnit()
    }



    private void setupUnitSwitch(){
        weightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean checkedState) {
                try {
                    updateUnits();
                    convertWeight(checkedState);
                    //save state
                    saveToggle("weight_toggle"+selectedUser.getUserID(),checkedState);

                }catch (Exception e){
                    callDialogMessages(e.getMessage());
                }
            }
        }) ;

        heightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkedState) {
                try {
                    updateUnits();
                    convertHeight(checkedState);
                    //save the state
                    saveToggle("height_toggle"+selectedUser.getUserID(),checkedState);

                }catch (Exception e){
                    callDialogMessages(e.getMessage());
                }
            }
        });
    }

    private void saveToggle(String key,boolean isToggled) {
        final SharedPreferences sharedPreferences = getSharedPreferences(key, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isToggled).apply();
    }

    private boolean loadToggleWeight(int id){
        final SharedPreferences sharedPreferences = getSharedPreferences("weight_toggle"+id, Context.MODE_PRIVATE);
        boolean defaultVal = false;
        if(selectedUser.getUnits()[0] == 1)// does not match default
        {
            defaultVal = true;
        }
        return sharedPreferences.getBoolean("weight_toggle"+id, defaultVal);
    }

    private boolean loadToggleHeight(int id){
        final SharedPreferences sharedPreferences = getSharedPreferences("height_toggle"+id, Context.MODE_PRIVATE);
        boolean defaultVal = false;
        if(selectedUser.getUnits()[1] == 0)// does not match default
        {
            defaultVal = true;
        }
        return sharedPreferences.getBoolean("height_toggle"+id, defaultVal);
    }

    private void convertWeight(boolean checkedState) {
        EditText weightData = (EditText) findViewById(R.id.editWeight);
        double read = 0.0;
        if(weightData.getText().toString().trim().equals("")){
            read = 0.0;
        }else{
            read = Double.parseDouble(weightData.getText().toString().trim());
        }

        if(checkedState){
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
    private void convertHeight(boolean checkedState) {
        //by default user types in lbs, ft
        EditText heightData = (EditText) findViewById(R.id.editHeight);
        double read = 0.0;
        if(heightData.getText().toString().trim().equals("")){
            read = 0.0;
        }else{
            read = Double.parseDouble(heightData.getText().toString().trim());
        }
        if(checkedState){
            double toCm = UnitConverter.FTToCM(read);
            String toCm_String = convertUnitToString(toCm,1);
            heightData.setText(toCm_String);

        }else{
            double toFt = UnitConverter.CMToFT(read);
            String toFt_String = convertUnitToString(toFt,1);
            heightData.setText(toFt_String);
        }

    }

    private void updateUnits(){
        int[] choiceUnits = new int[2];
        if(weightSwitch.isChecked()){
            choiceUnits[0] = 1; //if kgs
        }else{
            choiceUnits[0] = 0;  //if lbs
        }

        if(heightSwitch.isChecked()){
            choiceUnits[1] = 0; //if cm
        }else{
            choiceUnits[1] = 1;  //if ft
        }
        selectedUser.setUnits(choiceUnits);
    }

    private void updateGender(){
        Spinner choice = (Spinner) findViewById(R.id.genderSpinner);
        selectedUser.setGender(Gender.valueOf(choice.getSelectedItem().toString().charAt(0)));
    }
    private void updateGoal(){
        Spinner choice = (Spinner) findViewById(R.id.goalSpinner);
        selectedUser.setGoal(Goal.valueOf(choice.getSelectedItemPosition()));
    }
    private void updateActivity(){
        Spinner choice = (Spinner) findViewById(R.id.activityLevelSpinner);
        selectedUser.setActivityLevel(ActivityLevel.valueOf(choice.getSelectedItemPosition()));
    }

    private void updateWeight(){
        EditText weightData = (EditText) findViewById(R.id.editWeight);
        if(weightSwitch.isChecked())
        {//If kgs : then convert to default unit -> lbs
            selectedUser.setWeight(UnitConverter.KGToLB(Double.parseDouble(weightData.getText().toString().trim())));
        }else
        {
            selectedUser.setWeight(Double.parseDouble(weightData.getText().toString().trim()));
        }
    }

    private void updateHeight(){
        EditText heightData = (EditText) findViewById(R.id.editHeight);
        if(heightSwitch.isChecked())
        {
            selectedUser.setHeight(UnitConverter.CMToFT(Double.parseDouble(heightData.getText().toString().trim())));
        }else
        {//If cms : then convert to default unit -> ft
            selectedUser.setHeight(Double.parseDouble(heightData.getText().toString().trim()));
        }
    }


    private void setUpSpinner_goal(){
        Spinner goals = (Spinner) findViewById(R.id.goalSpinner);
        ArrayAdapter<String> goalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.goals));
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goals.setAdapter(goalAdapter);
        goals.setSelection(selectedUser.getGoal().getValue());

    }

    private void setUpSpinner_activity(){
        Spinner activityLevel = (Spinner) findViewById(R.id.activityLevelSpinner);
        ArrayAdapter<String> activityLevelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activityLevels));
        activityLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevel.setAdapter(activityLevelAdapter);
        activityLevel.setSelection(selectedUser.getActivityLevel().getValue());
    }


    private void setUpSpinner_gender(){
        Spinner Gender = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Genders));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(genderAdapter);
        Gender.setSelection(selectedUser.getGender().getPos());
    }


    public void btnSaveUpdatesOnClick(View v) {

        try {
            updateActivity();
            updateGender();
            updateGoal();
            updateHeight();
            updateWeight();
            updateUnits();


            accessUsers.updateUser(selectedUser.getUserID(),selectedUser);
            Intent logInToHomeIntent = new Intent(this, HomeActivity.class);
            logInToHomeIntent.putExtra("userLoggedIn",selectedUser);
            startActivity(logInToHomeIntent);
            finish();
        }catch (Exception e){
            Messages.fatalError(this,e.getMessage());
        }

    }
}