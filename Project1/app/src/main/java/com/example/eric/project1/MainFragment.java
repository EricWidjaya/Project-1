package com.example.eric.project1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Food");
        super.onActivityCreated(savedInstanceState);

        ArrayList<Category> foods = new ArrayList<>();
        Category Pasta = new Category("Pasta");
        Category Chinese = new Category("Chinese");
        Category Hamburgers = new Category("Hamburgers");
        Category Seafood = new Category("Seafood");
        Category Steak = new Category("Steak");

        foods.add(Pasta);
        foods.add(Chinese);
        foods.add(Hamburgers);
        foods.add(Seafood);
        foods.add(Steak);

        Food2 Fettucine = new Food2("Fettucine","Fettucine",4.5,4.5,4.5,Pasta);
        Pasta.getFoodArrayList().add(Fettucine);
        Food2 Spaghetti = new Food2("Spaghetti","Spaghetti",4.5,4.5,4.5,Pasta);
        Pasta.getFoodArrayList().add(Spaghetti);
        Food2 Japjae = new Food2("Japjae","Japjae",4.5,4.5,4.5,Chinese);
        Chinese.getFoodArrayList().add(Japjae);
        Food2 Capcay = new Food2("Capcay","Capcay",4.5,4.5,4.5,Chinese);
        Chinese.getFoodArrayList().add(Capcay);
        Food2 Cheeseburger = new Food2("Cheeseburger","Cheeseburger",4.5,4.5,4.5,Hamburgers);
        Hamburgers.getFoodArrayList().add(Cheeseburger);
        Food2 Beefburger = new Food2("Beefburger","Beefburger",4.5,4.5,4.5,Hamburgers);
        Hamburgers.getFoodArrayList().add(Beefburger);
        Food2 Sushi = new Food2("Sushi","Sushi",4.5,4.5,4.5, Seafood);
        Seafood.getFoodArrayList().add(Sushi);
        Food2 MarinatedCrab = new Food2("Marinated Crab","Marinated Crab",4.5,4.5,4.5,Seafood);
        Seafood.getFoodArrayList().add(MarinatedCrab);
        Food2 Hamburg = new Food2("Hamburg","Hamburg",4.5,4.5,4.5,Steak);
        Steak.getFoodArrayList().add(Hamburg);
        Food2 Sirloin = new Food2("Sirloin","Sirloin",4.5,4.5,4.5,Steak);
        Steak.getFoodArrayList().add(Sirloin);

        final CategoryAdapter custom = new CategoryAdapter(foods, getContext());
        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(custom);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Category food = (Category) custom.getItem(position);
                Fragment secondFrag = new SecondFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Category",food);

                secondFrag.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,secondFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
    }
}
