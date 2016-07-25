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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Foods");
        super.onActivityCreated(savedInstanceState);

        Log.d("MainFragment", "OnActivityCreated is called!");



        final CategoryAdapter custom = new CategoryAdapter(Model.getKategoriArrayList(), getContext());
        listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(custom);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Category food = (Category) custom.getItem(position);
                Fragment secondFrag = new SecondFragment();
               // Bundle bundle = new Bundle();
               // bundle.putParcelable("Category",food);
                ((MainActivity)getActivity()).setCurrentCat(food);
               // secondFrag.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,secondFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
    }
}
