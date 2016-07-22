package com.example.eric.project1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private FoodAdapter foodAdapter;
    private ListView listView;
    private Category cat;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Bundle bundle = getArguments();
        cat = bundle.getParcelable("Category");
        foodAdapter = new FoodAdapter(cat.getFoodArrayList(),getContext());
        listView = (ListView) getActivity().findViewById(R.id.listView2);
        listView.setAdapter(foodAdapter);

    }
}
