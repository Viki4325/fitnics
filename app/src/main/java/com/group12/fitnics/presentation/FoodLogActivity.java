package com.group12.fitnics.presentation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.User;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FoodLogActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private final static int UPDATE_CODE = 30;
    private User selectedUser;
    ListView listView;
    ArrayAdapter<FoodLog> adapter;
    private AccessFood foods;
    private AccessFoodLogs log;
    private TextView pickedDateText;
    private TextView pickedDayText;
    private LocalDate datePicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_log);

        datePicked = LocalDate.now();

        foods = new AccessFood();
        getUserLoggedIn();
        log =  new AccessFoodLogs();

        pickedDateText = findViewById(R.id.date_picked);
        pickedDateText.setText(datePicked.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));

        pickedDayText = findViewById(R.id.day_picked);
        pickedDayText.setText(datePicked.format(DateTimeFormatter.ofPattern("EEEE")));

        findViewById(R.id.date_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        setupList();
        setUpOnClickListener();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        // Hard coded the date account created. Need to store that date in the database
        datePickerDialog.getDatePicker().setMinDate(new GregorianCalendar(2021, 3, 1).getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

    private void setupList(){
        listView = (ListView) findViewById(R.id.food_log);
        ArrayList<FoodLog> list = new ArrayList<>(log.getFoodLogByUserDate(selectedUser.getUserID(), datePicked));

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

        listView.setAdapter(adapter);
    }

    private void setUpOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //To get the food we have selected
                FoodLog selectFoodLog = (FoodLog) (listView.getItemAtPosition(position));
                Food foodFromLog = (Food) foods.searchByFoodID(selectFoodLog.getFoodID());
                //create intent to go next activity
                //We pass the whole objects through intent
                Intent showDetail = new Intent(getApplicationContext(), IndividualFoodActivity.class);
                showDetail.putExtra("PURPOSE_CODE",UPDATE_CODE);
                showDetail.putExtra("userLoggedIn", selectedUser);
                showDetail.putExtra("foodGrams",selectFoodLog.getGrams());
                showDetail.putExtra("foodSelected",  foodFromLog);
                startActivity(showDetail);

            }
        });
    }


    private void getUserLoggedIn(){
        selectedUser = (User) getIntent().getSerializableExtra("userLoggedIn");
    }

    /*
     * Updates info on this page
     * */
    @Override
    protected void onResume() {
        super.onResume();
        setupList();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datePicked = LocalDate.of(year, month+1, dayOfMonth);
        pickedDateText.setText(datePicked.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        pickedDayText.setText(datePicked.format(DateTimeFormatter.ofPattern("EEEE")));
        setupList();
    }
}
