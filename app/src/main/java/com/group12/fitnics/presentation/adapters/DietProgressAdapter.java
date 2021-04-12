package com.group12.fitnics.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group12.fitnics.objects.ProgressObject;

import java.util.ArrayList;
import java.util.List;

public class DietProgressAdapter extends ArrayAdapter<ProgressObject> {

    private Context context;
    private int resource;
    private ArrayList<ProgressObject> objects;

    public DietProgressAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ProgressObject> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent,false);

        return convertView;
    }
}
