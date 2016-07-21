package com.example.eric.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 7/21/2016.
 */
public class FoodAdapter extends BaseAdapter {

    private ArrayList<Food2> foods;
    private Context context;

    public FoodAdapter(ArrayList<Food2> foods, Context context)
    {
        this.foods=foods;
        this.context=context;

    }


    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return foods.get(position).getId();
    }

    public ArrayList<Food2> getFoodArray()
    {
        return foods;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout, viewGroup, false);
        }
        TextView name = (TextView) view.findViewById(R.id.foodname);
        Food2 foods = (Food2) getItem(position);
        name.setText(foods.getName());


        return view;
    }
}
