package com.example.eric.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 7/21/2016.
 */
public class FoodAdapter extends BaseAdapter {

    private ArrayList<Food2> foods;
    private ArrayList<Food2> foods1;

    private Context context;

    public FoodAdapter(ArrayList<Food2> foods, Context context)
    {
        this.foods=foods;
        this.foods1=foods;
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

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                foods = (ArrayList<Food2>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Food2> FilteredArrList = new ArrayList<>();

                if (foods1 == null) {
                    foods1 = new ArrayList<>(foods); // saves the original data in mOriginalValues
                }
                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = foods1.size();
                    results.values = foods1;
                }

                else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < foods1.size(); i++) {
                        String data = foods1.get(i).getName();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(new Food2(foods1.get(i).getCatid(),foods1.get(i).getName(),
                             foods1.get(i).getDescription(),foods1.get(i).getCarbo(),foods1.get(i).getFat(),
                              foods1.get(i).getProtein(),foods1.get(i).getImages()));
                        }
                    }

                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }

                //Toast.makeText(context, "No Result Found!", Toast.LENGTH_SHORT).show();

                return results;
            }
        };
        return filter;
    }
}

