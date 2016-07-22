package com.example.eric.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    //testing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment MainFrag = new MainFragment();

        ArrayList<Category> foods = new ArrayList<>();
        Category Pasta = new Category("Pasta");
        Category Chinese = new Category("Chinese");
        Category Hamburgers = new Category("Hamburgers");
        Category Seafood = new Category("Seafood");
        Category Steak = new Category("Steak");

        Model.addKategoriArrayList(Pasta);
        Model.addKategoriArrayList(Chinese);
        Model.addKategoriArrayList(Hamburgers);
        Model.addKategoriArrayList(Seafood);
        Model.addKategoriArrayList(Steak);

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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_container,MainFrag);
        transaction.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();
    }

    private void shouldDisplayHomeUp() {
        boolean canback = getSupportFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
        
    }

    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
