package com.example.eric.project1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        Food2 food = bundle.getParcelable("Food");
        ((MainActivity)getActivity()).setActionBarTitle(food.getName());

        TextView name = (TextView) getView().findViewById(R.id.xname);
        TextView desc = (TextView) getView().findViewById(R.id.description);
        TextView carb = (TextView) getView().findViewById(R.id.carbo);
        TextView fat = (TextView) getView().findViewById(R.id.fat);
        TextView protein = (TextView) getView().findViewById(R.id.protein);

        name.setText("Name: "  + food.getName());
        desc.setText("Description: " + food.getDescription());
        carb.setText("Carbohydrate: " + String.valueOf(food.getCarbo()));
        fat.setText("Fat: " + String.valueOf(food.getFat()));
        protein.setText("Protein: " + String.valueOf(food.getProtein()));

    }
}
