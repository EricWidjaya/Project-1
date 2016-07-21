package com.example.eric.project1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private ListView listView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Food> foods = new ArrayList<>();
        Food Pasta = new Food(1,"Pasta");
        Food Chinese = new Food(2,"Chinese");
        Food Hamburgers = new Food(3,"Hamburgers");
        Food Seafood = new Food(4,"Seafood");
        Food Steak = new Food(5,"Steak");

        foods.add(Pasta);
        foods.add(Chinese);
        foods.add(Hamburgers);
        foods.add(Seafood);
        foods.add(Steak);

        final CategoryAdapter custom = new CategoryAdapter(foods, getContext());
        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(custom);
    }
}
