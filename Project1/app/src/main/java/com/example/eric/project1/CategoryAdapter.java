package com.example.eric.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eric on 7/21/2016.
 */
public class CategoryAdapter extends BaseAdapter{

    //private int id;
    private ArrayList<Category> foods;
    private Context context;

    public CategoryAdapter(ArrayList<Category> foods, Context context)
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
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_layout, viewGroup, false);
        }
        TextView name = (TextView) view.findViewById(R.id.foodname);
        Category foods = (Category) getItem(position);
        name.setText(foods.getFoodName());


        return view;
    }
}
