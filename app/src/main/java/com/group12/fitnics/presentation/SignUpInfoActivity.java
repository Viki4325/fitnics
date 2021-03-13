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
import com.group12.fitnics.objects.User;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;

public class SignUpInfoActivity extends AppCompatActivity {

    private User newUser;
    private EditText date;
    private AccessUsers accessUsers;
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
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar calendar = Calendar.getInstance();

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
            float weight = Float.parseFloat(data.getText().toString().trim());
            newUser.setWeight(weight);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void updateHeightInfo(){

        try {
            EditText data = (EditText) findViewById(R.id.editHeight);
            float height = Float.parseFloat(data.getText().toString().trim());
            newUser.setHeight(height);
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
                //Someone please make a method out of this and put it in the calculation class.
                double result = currentValue / 2.2046226218;
                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);
                String toKgs = df.format(result);
                setTextToEditText(weightData,toKgs);

            }else{
                //Someone please make a method out of this and put it in the calculation class.
                double result = currentValue * 2.2046226218;
                DecimalFormat df = new DecimalFormat("#");
                df.setRoundingMode(RoundingMode.FLOOR);
                String toLbs = df.format(result);
                setTextToEditText(weightData,toLbs);

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
            double currentValue = Double.parseDouble(heightData.getText().toString());

            if(convertToFts){
                //Someone please make a method out of this and put it in the calculation class.
                double result = currentValue/30.48;
                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);
                String ftInch = df.format(result);
                setTextToEditText(heightData,ftInch);

            }else{
                //Someone please make a method out of this and put it in the calculation class.
                double result = currentValue * 30.48;
                DecimalFormat df = new DecimalFormat("#");
                df.setRoundingMode(RoundingMode.FLOOR);
                String cm = df.format(result);
                setTextToEditText(heightData,cm);

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
        int[] choiceUnits = new int[2];
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