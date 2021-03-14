package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessUsers;
import com.group12.fitnics.business.UnitConverter;
import com.group12.fitnics.objects.User;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;
import static com.group12.fitnics.business.UnitConverter.convertUnitToString;

public class SignUpInfoActivity extends AppCompatActivity {

    private User newUser;
    private EditText date;
    private AccessUsers accessUsers;
    int[] choiceUnits = new int[2];
    ToggleButton weightSwitch;
    ToggleButton heightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_info);

        newUser = new User();
        accessUsers = new AccessUsers();
        Intent intent = getIntent();
        newUser.setGoal(intent.getIntExtra("goal",-1));

        Spinner Gender = (Spinner) findViewById(R.id.chooseGender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Genders));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(genderAdapter);

        setupUnitSwitch();
        date = (EditText)findViewById(R.id.editBirthday);

        date.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private final String ddmmyyyy = "DDMMYYYY";
            private final Calendar calendar = Calendar.getInstance();

            @SuppressLint("DefaultLocale")
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

                        year = (year < 1900) ? 1900 : Math.min(year, 2100);
                        calendar.set(Calendar.YEAR, year);
                        //^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = Math.min(day, calendar.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d",day,mon,year);
                    }

                    clean = String.format("%s/%s/%s",clean.substring(0,2),
                            clean.substring(2,4),
                            clean.substring(4,8));

                    sel = Math.max(sel, 0);
                    current = clean;
                    date.setText(current);
                    date.setSelection(Math.min(sel, current.length()));
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

    public void btnNextPage(View v) {
        newUser.setUsername(getUserName());
        newUser.setBirthDay(Integer.parseInt(getBirthDay().substring(0, 2)));
        newUser.setBirthMonth(Integer.parseInt(getBirthDay().substring(3, 5)));
        newUser.setBirthYear(Integer.parseInt(getBirthDay().substring(6, 10)));
        newUser.setGender(getGender());

        updateWeightInfo();
        updateHeightInfo();
        setUnits();
        accessUsers.insertUser(newUser);
        setupIntent();
    }



    private String getUserName(){
        EditText data = (EditText) findViewById(R.id.enterUsername);
        return data.getText().toString().trim();
    }

    private String getBirthDay(){
        EditText data = (EditText) findViewById(R.id.editBirthday);
        return data.getText().toString().trim();
    }

    private char getGender(){
        Spinner choice = (Spinner) findViewById(R.id.chooseGender);
        return choice.getSelectedItem().toString().charAt(0);
    }

    private void updateWeightInfo(){
        try {
            EditText data = (EditText) findViewById(R.id.editWeight);
            if(choiceUnits[0] == 1)
            {//If kgs : then convert to default unit -> lbs
                newUser.setWeight(UnitConverter.KGToLB(Double.parseDouble(data.getText().toString().trim())));
            }else
            {
                newUser.setWeight(Double.parseDouble(data.getText().toString().trim()));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /*
    * User's height information stored as cm's by default
    * */
    private void updateHeightInfo(){
        try {
            EditText data = (EditText) findViewById(R.id.editHeight);
            if(choiceUnits[1] == 1)
            {//If fts : then convert to default unit -> kgs
                newUser.setHeight(UnitConverter.FTToCM(Double.parseDouble(data.getText().toString().trim())));
            }else
            {
                newUser.setHeight(Double.parseDouble(data.getText().toString().trim()));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void setupIntent(){
        Intent ActivityPage = new Intent(this, SignUpActiveLevelActivity.class);
        ActivityPage.putExtra("username",getUserName());
        startActivity(ActivityPage);
    }


    private void setupUnitSwitch(){
        weightSwitch = (ToggleButton) findViewById(R.id.weightUnitSwitch);
        weightSwitch.setText("lbs");
        weightSwitch.setTextOff("lbs");
        weightSwitch.setTextOn("kgs");
        weightSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean convertToKgs) {
                updateWeight(convertToKgs);
                //call setUnits to update what units the user has
                setUnits();
            }
        }) ;

        heightSwitch = (ToggleButton) findViewById(R.id.heightUnitSwitch);
        heightSwitch.setText("cm");
        heightSwitch.setTextOff("cm");
        heightSwitch.setTextOn("ft");
        heightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean convertToFts) {
                updateHeight(convertToFts);
                //call setUnits to update what units the user has
                setUnits();
            }
        });

    }

    /*
    * IF weightSwitch is checked -> current unit is LBS (Need to be converted to Kgs)
    * IF weightSwitch is unchecked -> current unit is KGS (Need to be converted to LBS)
    * */
    private void updateWeight(boolean convertToKgs) {
        try {
            EditText weightData = (EditText) findViewById(R.id.editWeight);
            double currentValue = Double.parseDouble(weightData.getText().toString());
            if(convertToKgs){
                double toKg = UnitConverter.LBToKg(Double.parseDouble(weightData.getText().toString().trim()));
                String toKgString = convertUnitToString(toKg,1);
                setTextToEditText(weightData,toKgString);
            }else{
                double toLbs = UnitConverter.KGToLB(Double.parseDouble(weightData.getText().toString().trim()));
                String toLbsString = convertUnitToString(toLbs,1);
                setTextToEditText(weightData,toLbsString);
            }

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), LENGTH_SHORT).show();
        }
    }

    /*
     * IF heightSwitch is checked -> current unit is FT (Need to be converted to cm)
     * IF heightSwitch is unchecked -> current unit is cm (Need to be converted to FT)
     * */
    private void updateHeight(boolean convertToFts) {
        try {
            //by default user types in lbs, cm
            EditText heightData = (EditText) findViewById(R.id.editHeight);

            if(convertToFts){
                double toFt = UnitConverter.CMToFT(Double.parseDouble(heightData.getText().toString().trim()));
                String toFt_String = convertUnitToString(toFt,1);
                setTextToEditText(heightData,toFt_String);

            }else{
                double toCm = UnitConverter.FTToCM(Double.parseDouble(heightData.getText().toString().trim()));
                String toCm_String = convertUnitToString(toCm,1);
                setTextToEditText(heightData,toCm_String);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getLocalizedMessage(), LENGTH_SHORT).show();
        }
    }



    private void setTextToEditText(EditText editTextWidget, String textToDisplay){
        if(textToDisplay != null && editTextWidget != null){
            editTextWidget.setText(textToDisplay);
        }
    }


    private void setUnits(){
        if(weightSwitch.isChecked()){
            choiceUnits[0] = 1; //if kgs
        }else{
            choiceUnits[0] = 0;  //if lbs
        }

        if(heightSwitch.isChecked()){
            choiceUnits[1] = 1; //if ft
        }else{
            choiceUnits[1] = 0;  //if cm
        }
        newUser.setUnits(choiceUnits);
    }


    public void closePage(View view){
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
