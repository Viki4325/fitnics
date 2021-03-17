package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.objects.User;

import java.util.Calendar;

public class UpdateUserInfoActivity extends AppCompatActivity {
    private AccessUsers accessUsers;
    private User selectedUser;
    private EditText date;
    private EditText name;
    private EditText weight;
    private EditText height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        accessUsers = new AccessUsers();
        Intent intent = getIntent();
        String id = intent.getStringExtra("userID");

        selectedUser = accessUsers.getUserById(Integer.parseInt(id));
        int[] units = selectedUser.getUnits();

        Spinner Gender = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Genders));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(genderAdapter);
        char gender = selectedUser.getGender();
        int selection = -1;
        if(gender == 'M')
            selection = 0;
        else if(gender == 'F')
            selection = 1;
        else if(gender == 'O')
            selection = 2;
        Gender.setSelection(selection);

        Spinner weightUnits = (Spinner) findViewById(R.id.weightUnitsSpinner);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.weightUnits));
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightUnits.setAdapter(weightAdapter);
        weightUnits.setSelection(units[0]);

        Spinner heightUnits = (Spinner) findViewById(R.id.heightUnitsSpinner);
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.heightUnits));
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightUnits.setAdapter(heightAdapter);
        heightUnits.setSelection(units[1]);

        Spinner goals = (Spinner) findViewById(R.id.goalSpinner);
        ArrayAdapter<String> goalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.goals));
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goals.setAdapter(goalAdapter);
        goals.setSelection(selectedUser.getGoal());

        Spinner activityLevel = (Spinner) findViewById(R.id.activityLevelSpinner);
        ArrayAdapter<String> activityLevelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activityLevels));
        activityLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevel.setAdapter(activityLevelAdapter);
        activityLevel.setSelection(selectedUser.getActivityLevel());

        name = (EditText)findViewById(R.id.usernameBox);
        name.setText(selectedUser.getUsername());

        height = (EditText)findViewById(R.id.heightBox);
        height.setText(""+selectedUser.getHeight());

        weight = (EditText)findViewById(R.id.weightBox);
        weight.setText(""+selectedUser.getWeight());

        date = (EditText)findViewById(R.id.birthdayBox);
        if(selectedUser.getBirthDay() < 10)
            date.setText("0"+selectedUser.getBirthDay()+"/"+selectedUser.getBirthMonth()+"/"+selectedUser.getBirthYear());
        else if(selectedUser.getBirthMonth() < 10)
            date.setText(selectedUser.getBirthDay()+"/0"+selectedUser.getBirthMonth()+"/"+selectedUser.getBirthYear());
        else
            date.setText(selectedUser.getBirthDay()+"/"+selectedUser.getBirthMonth()+"/"+selectedUser.getBirthYear());
        date.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar calendar = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {

                if(!seq.toString().equals(current)){
                    String clean = seq.toString().replaceAll("[^\\d.]","");
                    String cleanC = current.replaceAll("[^\\d.]","");

                    int cl = clean.length();
                    int sel = cl;
                    for(int i = 2;i<= cl && i < 6; i+=2){
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if(clean.equals(cleanC)) sel--;

                    if(clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else {
                        //this part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));

                        if (mon > 12) mon = 12;
                        calendar.set(Calendar.MONTH, mon - 1);

                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        calendar.set(Calendar.YEAR, year);
                        //^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > calendar.getActualMaximum(Calendar.DATE)) ?
                                calendar.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d",day,mon,year);
                    }

                    clean = String.format("%s/%s/%s",clean.substring(0,2),
                            clean.substring(2,4),
                            clean.substring(4,8));

                    sel = sel < 0?0:sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel:current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void btnSaveUpdatesOnClick(View v) {
        String username;
        int[] units = new int[2];
        //Add username to new user
        EditText data = (EditText) findViewById(R.id.usernameBox);
        username = data.getText().toString().trim();
        selectedUser.setUsername(username);
        //Add birthday to new user
        data = (EditText) findViewById(R.id.birthdayBox);
        String birthday = data.getText().toString().trim();
        selectedUser.setBirthDay(Integer.parseInt(birthday.substring(0, 2)));
        selectedUser.setBirthMonth(Integer.parseInt(birthday.substring(3, 5)));
        selectedUser.setBirthYear(Integer.parseInt(birthday.substring(6, 10)));
        //Add gender to new user
        Spinner choice = (Spinner) findViewById(R.id.genderSpinner);
        selectedUser.setGender(choice.getSelectedItem().toString().charAt(0));
        //Add weight to new user
        data = (EditText) findViewById(R.id.weightBox);
        choice = (Spinner) findViewById(R.id.weightUnitsSpinner);

        double value = Double.parseDouble(data.getText().toString().trim());
        //set max and min weight
        if(value > 1400 ){
            value = 1400;
        }else if(value < 12){
            value = 12;
        }

        if(choice.getSelectedItem().toString() == "kg") {
            selectedUser.setWeight(UnitConverter.KGToLB(value));
        }
        else {
            selectedUser.setWeight(value);
        }
        units[0] = choice.getSelectedItemPosition();
        //Add height to new user
        data = (EditText) findViewById(R.id.heightBox);
        choice = (Spinner) findViewById(R.id.heightUnitsSpinner);

        value = Double.parseDouble(data.getText().toString().trim());
        //set max and min height
        if(value > 300){
            value = 300;
        }else if(value < 50){
            value = 50;
        }

        if(choice.getSelectedItem().toString() == "ft") {
            selectedUser.setHeight(UnitConverter.FTToCM(value));
        }
        else {
            selectedUser.setHeight(value);
        }
        units[1] = choice.getSelectedItemPosition();
        selectedUser.setUnits(units);

        choice = (Spinner) findViewById(R.id.goalSpinner);
        selectedUser.setGoal(choice.getSelectedItemPosition());

        choice = (Spinner) findViewById(R.id.activityLevelSpinner);
        selectedUser.setActivityLevel(choice.getSelectedItemPosition());

        accessUsers.updateUser(selectedUser.getUserID(),selectedUser);
        Intent logInToHomeIntent = new Intent(this, HomeActivity.class);
        logInToHomeIntent.putExtra("username", username);
        startActivity(logInToHomeIntent);
    }
}