package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessFood;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.objects.Food;
import com.group12.fitnics.presentation.adapters.FoodAdapter;

public class SearchFoodActivity extends AppCompatActivity {
    private final static int ADD_FOOD_CODE = 12;
    private ListView listView;
    private User selectedUser;
    private AccessFood food;
    List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        food = new AccessFood();
        selectedUser = (User)getIntent().getSerializableExtra("userLoggedIn");
        listView = (ListView) findViewById(R.id.Search_food);
        foodList = food.getFoodList();
        FoodAdapter adapter = new FoodAdapter(getApplicationContext(), 0, foodList);
        listView.setAdapter(adapter);
        setUpOnClickListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SearchView searchView = (SearchView) findViewById(R.id.searchFood);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                setSearchString(s);
                FoodAdapter adapter = new FoodAdapter(getApplicationContext(), 0,getSearchResult() );
                listView.setAdapter(adapter);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void setUpOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //To get the food we have selected
                Food selectFood = (Food) (listView.getItemAtPosition(position));

                //create intent to go next activity
                //We pass the whole objects through intent
                Intent showDetail = new Intent(getApplicationContext(), IndividualFoodActivity.class);
                showDetail.putExtra("PURPOSE_CODE",ADD_FOOD_CODE);
                showDetail.putExtra("userLoggedIn", selectedUser);
                showDetail.putExtra("foodSelected",  selectFood);
                startActivity(showDetail);

            }
        });
    }

    private void setSearchString(String searchQuery){
        food.setSearchPhrase("");
        food.setSearchPhrase(searchQuery);
    }

    private List<Food> getSearchResult(){
        return food.getFoodBySearch(foodList);
    }

}

