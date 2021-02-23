package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import com.group12.fitnics.R;
import com.group12.fitnics.persistence.FoodPersistenceStub;
import com.group12.fitnics.objects.Food;

public class SearchFood extends AppCompatActivity {
    ListView listView;

    ArrayAdapter<Food> adapter;
    FoodPersistenceStub food = new FoodPersistenceStub();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        listView = (ListView) findViewById(R.id.Search_food);

        ArrayList<Food> list = new ArrayList<>();
        list = food.getFoodSequential();
        adapter = new ArrayAdapter<Food>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );

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
}