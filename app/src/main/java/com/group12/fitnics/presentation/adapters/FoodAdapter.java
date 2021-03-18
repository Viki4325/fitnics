package com.group12.fitnics.presentation.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.objects.Food;


import java.util.List;


public class FoodAdapter extends ArrayAdapter<Food> implements Filterable {

    public FoodAdapter(Context context, int resource,List<Food> food) {
        super(context,resource,food);
    }

    @Override
    public View getView(int position  ,View convertView,  ViewGroup parent) {

        Food foodItem = (Food) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.list_item, parent, false);
        }

        TextView foodName = convertView.findViewById(R.id.Food_name);
        foodName.setText(String.valueOf(foodItem.toString()));


        foodName.setText(foodItem.toString());


        return convertView;
    }

}
