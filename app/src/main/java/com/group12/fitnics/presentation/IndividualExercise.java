package com.group12.fitnics.presentation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.group12.fitnics.R;

import java.util.Objects;

public class IndividualExercise extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton addExercise;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_detail);
//        Toolbar toolbar = findViewById(R.id.exerciseDetailToolBar);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        addExercise = findViewById(R.id.add_button);
//        addExercise.setOnClickListener(this);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle info = getIntent().getExtras();

//        final CollapsingToolbarLayout layout = findViewById(R.id.exerciseDetailCollapseToolBar);
//        layout.setTitle(info.getString("exerciseTitle"));

        final TextView title = findViewById(R.id.exerciseTitle);
        title.setText(info.getString("exerciseTitle"));

        final TextView description = findViewById(R.id.exerciseDescription);
        description.setText(info.getString("exerciseDescription"));

        final TextView category = findViewById(R.id.exerciseCategory);
        category.setText(info.getString("exerciseCategory"));

        final TextView level = findViewById(R.id.exerciseLevel);
        level.setText(info.getString("exerciseLevel"));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
//        addExercise();
    }

//    private void addExercise(){
//        Toast.makeText(getApplicationContext(), "Still working on it",Toast.LENGTH_SHORT).show();
//        //once you add an exercise go back to the list to select more
//        onBackPressed();
//    }
}
