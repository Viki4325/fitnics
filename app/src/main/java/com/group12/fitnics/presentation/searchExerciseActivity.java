package com.group12.fitnics.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.group12.fitnics.R;
import com.group12.fitnics.business.AccessExercises;
import com.group12.fitnics.objects.Exercise;
import com.group12.fitnics.presentation.adapters.ExerciseAdapter;

import java.util.ArrayList;
import java.util.List;

public class searchExerciseActivity extends AppCompatActivity {

    AccessExercises accessExercises = new AccessExercises();
    private ListView exerciseListView;
    List<Exercise> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_finder);

        exercises =  accessExercises.getAllExercise();

        setupList();
        initSearchWidgets();
        setUpOnClickListener();

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
                Intent showDetail = new Intent(getApplicationContext(), IndividualExercise.class);
                showDetail.putExtra("exerciseTitle",selectExercise.getTitle());
                showDetail.putExtra("exerciseDescription",selectExercise.getDescription());
                showDetail.putExtra("exerciseCategory", selectExercise.getCategory());
                showDetail.putExtra("exerciseLevel",selectExercise.getLevel());
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
}