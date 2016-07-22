package com.example.eric.project1;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        ((MainActivity)getActivity()).setActionBarTitle(cat.getFoodName());
        foodAdapter = new FoodAdapter(cat.getFoodArrayList(),getContext());
        listView = (ListView) getActivity().findViewById(R.id.listView2);
        listView.setAdapter(foodAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Food2 food = (Food2) foodAdapter.getItem(position);
                Fragment thirdFrag = new ThirdFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Food",food);
                thirdFrag.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container,thirdFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_button,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add :
                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View viewD = inflater.inflate(R.layout.dialog_box,null);
                inputAlert.setView(viewD);
                inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText addName = (EditText) viewD.findViewById(R.id.addName);
                    EditText addDesc = (EditText) viewD.findViewById(R.id.addDesc);
                    EditText addCarb = (EditText) viewD.findViewById(R.id.addCarb);
                    EditText addFat = (EditText) viewD.findViewById(R.id.addFat);
                    EditText addProt = (EditText) viewD.findViewById(R.id.addProtein);
                    String name = addName.getText().toString();
                    String desc = addDesc.getText().toString();
                    double carb = Double.parseDouble(addCarb.getText().toString());
                    double fat = Double.parseDouble(addFat.getText().toString());
                    double prot = Double.parseDouble(addProt.getText().toString());
                    Food2 food2 = new Food2(name, desc, carb, fat, prot, cat);
                    foodAdapter.getFoodArray().add(food2);
                    foodAdapter.notifyDataSetChanged();
                }
                });
                inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();
                break;
        } //end of switch
        return true;
    }

}
