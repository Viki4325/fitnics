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

public class SignUpInfoActivity extends AppCompatActivity {

    private User newUser;
    private EditText date;
    private AccessUsers accessUsers;

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

        Spinner weightUnits = (Spinner) findViewById(R.id.weightUnitSpinner);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.weightUnits));
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightUnits.setAdapter(weightAdapter);

        Spinner heightUnits = (Spinner) findViewById(R.id.heightUnitSpinner);
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.heightUnits));
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightUnits.setAdapter(heightAdapter);

        date = (EditText)findViewById(R.id.editBirthday);
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

    public void btnNextPage(View v) {
        String username;
        int[] units = new int[2];
        //Add username to new user
        EditText data = (EditText) findViewById(R.id.enterUsername);
        username = data.getText().toString().trim();
        newUser.setUsername(username);
        //Add birthday to new user
        data = (EditText) findViewById(R.id.editBirthday);
        String birthday = data.getText().toString().trim();
        newUser.setBirthDay(Integer.parseInt(birthday.substring(0, 2)));
        newUser.setBirthMonth(Integer.parseInt(birthday.substring(3, 5)));
        newUser.setBirthYear(Integer.parseInt(birthday.substring(6, 10)));
        //Add gender to new user
        Spinner choice = (Spinner) findViewById(R.id.chooseGender);
        newUser.setGender(choice.getSelectedItem().toString().charAt(0));
        //Add weight to new user
        data = (EditText) findViewById(R.id.editWeight);
        choice = (Spinner) findViewById(R.id.weightUnitSpinner);

        if(choice.getSelectedItem().toString() == "kg")
            newUser.setWeight(UnitConverter.KGToLB(Double.parseDouble(data.getText().toString().trim())));
        else
            newUser.setWeight(Double.parseDouble(data.getText().toString().trim()));
        units[0] = choice.getSelectedItemPosition();
        //Add height to new user
        data = (EditText) findViewById(R.id.editHeight);
        choice = (Spinner) findViewById(R.id.heightUnitSpinner);

        if(choice.getSelectedItem().toString() == "ft")
            newUser.setHeight(UnitConverter.FTToCM(Double.parseDouble(data.getText().toString().trim())));
        else
            newUser.setHeight(Double.parseDouble(data.getText().toString().trim()));
        units[1] = choice.getSelectedItemPosition();
        newUser.setUnits(units);
        //go to next panel to get activity level
        accessUsers.insertUser(newUser);
        Intent ActivityPage = new Intent(this, SignUpActiveLevelActivity.class);
        ActivityPage.putExtra("username",username);
        startActivity(ActivityPage);
    }
}