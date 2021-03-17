package com.group12.fitnics.presentation.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.group12.fitnics.R;
import com.group12.fitnics.objects.Food;

import java.util.ArrayList;
import java.util.Locale;

public class FoodAdapter extends BaseAdapter implements Filterable {
    private Activity context;
    private ArrayList<Food> food; 
    private ArrayList<Food> oldList; 
    private SearchFilter filter;
    private LayoutInflater inflater;

    private final Object lock = new Object();

    public FoodAdapter(Activity context, ArrayList<Food> food) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.food = food;
    }

    public void resetData(ArrayList<Food> data) {
        food = data;
        if (oldList != null)
            oldList = food;
    }

    @Override
    public int getCount() {
        return food.size();
    }

    @Override
    public Object getItem(int position) {
        return food.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).
                    inflate(R.layout.list_item, parent, false);
        }

        Food currentFood = (Food) getItem(position);
        TextView foodName = (TextView) listItemView.findViewById(R.id.Food_name);
        foodName.setText(currentFood.toString());

        TextView foodIntake = (TextView) listItemView.findViewById(R.id.Food_intake);
        foodIntake.setText(String.valueOf(currentFood.getFoodID()));
        return listItemView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new SearchFilter();
        }
        return filter;
    }

    class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();

            
            if (oldList == null) {
                synchronized (lock) {
                    oldList = new ArrayList<Food>(food);
                }
            }

           
            if (TextUtils.isEmpty(constraint)) {
                synchronized (lock) {
                    filterResults.values = oldList;
                    filterResults.count = oldList.size();
                }
            } else {

                
                String filterString = constraint.toString().trim()
                        .toLowerCase(Locale.US);
                ArrayList<Food> newValues = new ArrayList<Food>();

                for (Food vo : oldList) {
                    if (vo.getName().toLowerCase(Locale.US)
                            .contains(filterString)) {
                        newValues.add(vo);
                    }
                    filterResults.values = newValues;
                    filterResults.count = newValues.size();
                }
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            food = (ArrayList<Food>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }


}
