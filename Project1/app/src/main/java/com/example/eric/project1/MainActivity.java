package com.example.eric.project1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    HashMap<Category,ArrayList<Food2>> hash1 = new HashMap<>();
    private Category currentCat;

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

        Food2 Fettucine = new Food2("Fettucine","Fettucine",4.5,4.5,4.5,null);
        hash1.put(Pasta,new ArrayList<Food2>());
        hash1.get(Pasta).add(Fettucine);
        Food2 Spaghetti = new Food2("Spaghetti","Spaghetti",4.5,4.5,4.5,null);
        hash1.get(Pasta).add(Spaghetti);
        Pasta.getFoodArrayList().add(Spaghetti);
        Food2 Japjae = new Food2("Japjae","Japjae",4.5,4.5,4.5,null);
        hash1.put(Chinese,new ArrayList<Food2>());
        hash1.get(Chinese).add(Japjae);
        Food2 Capcay = new Food2("Capcay","Capcay",4.5,4.5,4.5,null);
        hash1.get(Chinese).add(Capcay);
        Food2 Cheeseburger = new Food2("Cheeseburger","Cheeseburger",4.5,4.5,4.5,null);
        hash1.put(Hamburgers,new ArrayList<Food2>());
        hash1.get(Hamburgers).add(Cheeseburger);
        Food2 Beefburger = new Food2("Beefburger","Beefburger",4.5,4.5,4.5,null);
        hash1.get(Hamburgers).add(Beefburger);
        Food2 Sushi = new Food2("Sushi","Sushi",4.5,4.5,4.5, null);
        hash1.put(Seafood, new ArrayList<Food2>());
        hash1.get(Seafood).add(Sushi);
        Food2 MarinatedCrab = new Food2("Marinated Crab","Marinated Crab",4.5,4.5,4.5,null);
        hash1.get(Seafood).add(MarinatedCrab);
        Food2 Hamburg = new Food2("Hamburg","Hamburg",4.5,4.5,4.5,null);
        hash1.put(Steak, new ArrayList<Food2>());
        hash1.get(Steak).add(Hamburg);
        Food2 Sirloin = new Food2("Sirloin","Sirloin",4.5,4.5,4.5,null);
        hash1.get(Steak).add(Sirloin);
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

    protected boolean rCode(int code)
    {
        return code == RESULT_OK;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //permission
    protected void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public HashMap<Category,ArrayList<Food2>> getHash1()
    {
        return hash1;
    }

    public Category getCurrentCat(){
        return currentCat;
    }

    public void setCurrentCat(Category currentCat)
    {
        this.currentCat = currentCat;
    }


}
