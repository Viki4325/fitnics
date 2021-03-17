package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.business.AccessFoodLogs;
import com.group12.fitnics.objects.FoodLog;
import com.group12.fitnics.objects.MyDate;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.presentation.adapters.FoodAdapter;

public class SearchFoodActivity extends AppCompatActivity {
    ListView listView;
    int foodConsume;
    FoodAdapter adapter;
    //ArrayAdapter<Food> adapter;
    AccessFood food = new AccessFood();
    AccessFoodLogs foodLogs = new AccessFoodLogs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        listView = (ListView) findViewById(R.id.Search_food);

        ArrayList<Food> list = new ArrayList<>();
        list = food.getFoodList().getFoodSequential();
        adapter = new FoodAdapter(this, list);



        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.Search_food);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public void btnAdd(View view) {
        LinearLayout parentRow = (LinearLayout) view.getParent();

        EditText editNumber = (EditText) parentRow.findViewById(R.id.Food_intake);
        TextView foodDetail = (TextView) parentRow.findViewById(R.id.Food_name);
        String detail = foodDetail.getText().toString();
        String foodIntake = editNumber.getText().toString();
        foodConsume = Integer.parseInt(foodIntake);
        editNumber.setText(String.valueOf(foodConsume));

        String[] foodName = detail.split("\\.");
        Food a = (food.searchByFoodName(foodName[1]));
        double calories = a.getCalories();
        int foodID = a.getFoodID();
        int totalCalories = (int) (calories * foodConsume);
        Calendar calendar = Calendar.getInstance();
        MyDate date = new MyDate(calendar);
        Intent intent = getIntent();
        int selectedUser = Integer.parseInt(intent.getStringExtra("userID"));
        FoodLog foodLog = new FoodLog(selectedUser, foodID, date, totalCalories);
        foodLogs.insertFoodLog(foodLog);
    }
}


