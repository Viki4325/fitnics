package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.objects.User;
import com.group12.fitnics.presentation.adapters.ExerciseAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchExerciseActivity extends AppCompatActivity {

    AccessExercises accessExercises = new AccessExercises();
    private ListView exerciseListView;
    List<Exercise> exercises = new ArrayList<>();
    private User userLoggedIn;
    Bundle info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_finder);

        exercises =  accessExercises.getAllExercise();


        getUserLoggedIn();
        setupList();
        initSearchWidgets();
        setUpOnClickListener();

    }

    private void getUserLoggedIn(){
        userLoggedIn =(User) getIntent().getSerializableExtra("userLoggedIn");
    }


    private void initSearchWidgets(){
        SearchView searchView = findViewById(R.id.searchExercise);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //This method is called every time a user enter a character into the searchView
            @Override
            public boolean onQueryTextChange(String newText) {
                setSearchString(newText);
                ExerciseAdapter adapter = new ExerciseAdapter(getApplicationContext(), 0, getSearchResult());
                exerciseListView.setAdapter(adapter);
                return false;
            }
        });

    }

    private void setUpOnClickListener(){
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //To get the exercise we have selected
                Exercise selectExercise = (Exercise) (exerciseListView.getItemAtPosition(position));

                //create intent to go next activity
                //We pass the whole objects through intent
                Intent showDetail = new Intent(getApplicationContext(), IndividualExercise.class);
                showDetail.putExtra("userLoggedIn", userLoggedIn);
                showDetail.putExtra("exerciseSelected",  selectExercise);
                startActivity(showDetail);

            }
        });
    }

    private void setupList(){
        exerciseListView = (ListView) findViewById(R.id.exerciseList);

        ExerciseAdapter adapter = (ExerciseAdapter) new ExerciseAdapter(getApplicationContext(),0, exercises);

        exerciseListView.setAdapter(adapter);
    }

    private void setSearchString(String searchQuery){
        accessExercises.resetSearch();
        accessExercises.setSearchPhrase(searchQuery);
    }

    private List<Exercise> getSearchResult(){
        return accessExercises.getExerciseBySearch(exercises);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}