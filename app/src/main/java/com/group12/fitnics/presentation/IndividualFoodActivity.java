package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExerciseLogs;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.ExerciseLog;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.objects.User;

import java.util.Calendar;
import java.util.Objects;

public class IndividualFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private final static int UPDATE_CODE = 30;
    private final static int ADD_FOOD_CODE = 12;
    private AccessFoodLogs foodLogs;
    private Food foodSelected;
    private User userLoggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_log_detail);

        getFoodSelected();
        getUserLoggedIn();

        Toolbar toolbar = findViewById(R.id.LogDetailToolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        FloatingActionButton addFood = findViewById(R.id.add_food);
        addFood.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setEditText_grams();
        EditText foodGrams = (EditText) findViewById(R.id.foodGrams);
        setFoodInfo();

        foodGrams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                //if after editing, the editText has nothing then display 0
                String inputText = foodGrams.getText().toString();
                if(inputText.equals(""))
                {
                    displayCals(0);
                }else{
                    displayCals(Integer.parseInt(inputText));

                }
            }
        });


        foodLogs = new AccessFoodLogs();
        AccessFood accessFood = new AccessFood();
        setFoodInfo();
    }

    private void getFoodSelected(){
        if(whichIntent() == ADD_FOOD_CODE){
            foodSelected = (Food) getIntent().getSerializableExtra("foodSelected");
        }else if(whichIntent() == UPDATE_CODE){
            foodSelected = (Food) getIntent().getSerializableExtra("foodSelected");
        }
    }

    /*
    * Since 2 different activities come to the same page, with different purposes
    * We identify what the purpose is through codes
    * */
    private int whichIntent(){
        int code = getIntent().getExtras().getInt("PURPOSE_CODE");
        return code;
    }

    private void getUserLoggedIn(){
        userLoggedIn = (User) getIntent().getSerializableExtra("userLoggedIn");
    }


    /*
     * This method get's the Exercise object that was selected via the intent
     * It extracts the required details such as, Title, description, category, level
     * and sets them to the required views.
     * */
    private void setFoodInfo(){
        final CollapsingToolbarLayout layout = findViewById(R.id.LogDetailCollapseToolBar);
        layout.setTitle(foodSelected.getName());

        final TextView calories = findViewById(R.id.calories);
        calories.setText(String.valueOf(foodSelected.getCalories()));

    }

    private void setEditText_grams(){
        final TextView gramsEditText = findViewById(R.id.foodGrams);
        if(whichIntent() == UPDATE_CODE){
            //get the grams of food consumed from the foodLog via intent
            int gramsInLog = getIntent().getExtras().getInt("foodGrams");
            gramsEditText.setText(String.valueOf(gramsInLog));
        }else if(whichIntent() == ADD_FOOD_CODE){
            gramsEditText.setText("1");
        }
    }

    private void displayCals(int gram){
        final TextView calories = findViewById(R.id.calories);
        float calsPerGram = (float) ( gram * foodSelected.getCalories());
        calories.setText(String.valueOf(calsPerGram));
    }

    private int getGrams(){

        final TextView grams = findViewById(R.id.foodGrams);
        return Integer.parseInt(grams.getText().toString().trim());
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if(whichIntent() == ADD_FOOD_CODE){
            addFoodToLogs();
        }else if(whichIntent() == UPDATE_CODE){
            updateFoodLog();
        }

    }

    private void addFoodToLogs(){
        try {
            FoodLog foodLog = new FoodLog(userLoggedIn.getUserID(), foodSelected.getFoodID(), MyDate.getCurrentDate(),getGrams());
            foodLogs.insertFoodLog(foodLog);
            Toast.makeText(getApplicationContext(), "Food added to your Food logs!",Toast.LENGTH_SHORT).show();
            //once you add an exercise go back to the list to select more
            onBackPressed();
        }catch (Exception e){
            Messages.alert(this,e.getMessage());
        }
    }


    private void updateFoodLog(){
        try {
            //int userId, int foodId, MyDate date, int grams
            Calendar calendar = Calendar.getInstance();
            MyDate date = new MyDate(calendar);
            FoodLog updatedFoodLog = new FoodLog(userLoggedIn.getUserID(), foodSelected.getFoodID(), date,getGrams() );
            foodLogs.updateFoodLog(userLoggedIn.getUserID(), foodSelected.getFoodID(), date, updatedFoodLog);

            Toast.makeText(getApplicationContext(), "Your food log has been updated!!",Toast.LENGTH_SHORT).show();
            //once you update the foodLog o back to the list of foodLogs
            onBackPressed();
        }catch (Exception e){
            Messages.fatalError(this,e.getMessage());
        }

    }

}