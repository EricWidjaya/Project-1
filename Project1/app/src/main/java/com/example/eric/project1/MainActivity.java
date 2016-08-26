package com.example.eric.project1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    HashMap<Category,ArrayList<Food2>> hash1 = new HashMap<>();
    ArrayList<HashMap<Category,ArrayList<Food2>>> list = new ArrayList<>();
    ArrayList<HashMap> hashmapList = new ArrayList<HashMap>();

    ArrayList<Food2> pasta = new ArrayList<>();
    ArrayList<Food2> chinese = new ArrayList<>();
    ArrayList<Food2> hamburgers = new ArrayList<>();
    ArrayList<Food2> seafood = new ArrayList<>();
    ArrayList<Food2> steak = new ArrayList<>();

    private Category currentCat;

    FeedReaderDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment MainFrag = new MainFragment();

        dbHelper = new FeedReaderDBHelper(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_container, MainFrag);
        transaction.commit();

        if(savedInstanceState == null && (dbHelper.getCategories().size() == 0)) {
            ArrayList<Category> foods = new ArrayList<>();
            Category Pasta = new Category(1, "Pasta");
            Category Chinese = new Category(2, "Chinese");
            Category Hamburgers = new Category(3, "Hamburgers");
            Category Seafood = new Category(4, "Seafood");
            Category Steak = new Category(5, "Steak");

            Model.addKategoriArrayList(Pasta);
            Model.addKategoriArrayList(Chinese);
            Model.addKategoriArrayList(Hamburgers);
            Model.addKategoriArrayList(Seafood);
            Model.addKategoriArrayList(Steak);

            Food2 Fettucine = new Food2(1, "Fettucine", "Fettucine", 4.5, 4.5, 4.5, null);
            hash1.put(Pasta, pasta);
            hash1.get(Pasta).add(Fettucine);
            Food2 Spaghetti = new Food2(1, "Spaghetti", "Spaghetti", 4.5, 4.5, 4.5, null);
            hash1.get(Pasta).add(Spaghetti);
            Pasta.getFoodArrayList().add(Spaghetti);
            Food2 Japjae = new Food2(2, "Japjae", "Japjae", 4.5, 4.5, 4.5, null);
            hash1.put(Chinese, chinese);
            hash1.get(Chinese).add(Japjae);
            Food2 Capcay = new Food2(2, "Capcay", "Capcay", 4.5, 4.5, 4.5, null);
            hash1.get(Chinese).add(Capcay);
            Food2 Cheeseburger = new Food2(3, "Cheeseburger", "Cheeseburger", 4.5, 4.5, 4.5, null);
            hash1.put(Hamburgers, hamburgers);
            hash1.get(Hamburgers).add(Cheeseburger);
            Food2 Beefburger = new Food2(3, "Beefburger", "Beefburger", 4.5, 4.5, 4.5, null);
            hash1.get(Hamburgers).add(Beefburger);
            Food2 Sushi = new Food2(4, "Sushi", "Sushi", 4.5, 4.5, 4.5, null);
            hash1.put(Seafood, seafood);
            hash1.get(Seafood).add(Sushi);
            Food2 MarinatedCrab = new Food2(4, "Marinated Crab", "Marinated Crab", 4.5, 4.5, 4.5, null);
            hash1.get(Seafood).add(MarinatedCrab);
            Food2 Hamburg = new Food2(5, "Hamburg", "Hamburg", 4.5, 4.5, 4.5, null);
            hash1.put(Steak, steak);
            hash1.get(Steak).add(Hamburg);
            Food2 Sirloin = new Food2(5, "Sirloin", "Sirloin", 4.5, 4.5, 4.5, null);
            hash1.get(Steak).add(Sirloin);


        }

         else {
            Log.d("Conditional: ","CONDITIONAL BERHASIL");
            Model.clearKategoriArrayList();

            //ArrayList<Category> categories = savedInstanceState.getParcelableArrayList("HASHMAP");
           // ArrayList<Food2> allFoods = savedInstanceState.getParcelableArrayList("FOODS");
            ArrayList<Category> categories = dbHelper.getCategories();
            ArrayList<Food2> allFoods = dbHelper.getFood();
            if (savedInstanceState != null) {
                currentCat = savedInstanceState.getParcelable("CURRENTCAT");
            }

            for (Category c : categories) {
                ArrayList<Food2> foods = new ArrayList<>();
                //Log.d("AAA: ",foods.toString());
                for (Food2 f : allFoods) {
                    if (f.getCatid() == c.getFoodId()) {
                        foods.add(f);
                        //Log.d("BBB: ",f.toString());
                    }
                }
                hash1.put(c, foods);
                Model.addKategoriArrayList(c);
            }
        }


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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /*outState.putSerializable("Pasta", pasta);
        outState.putParcelableArrayList("Pasta", pasta);
        outState.putSerializable("Chinese",chinese);
        outState.putSerializable("Hamburgers",hamburgers);
        outState.putSerializable("Steak",steak);
        outState.putSerializable("Seafood",seafood);*/
        Log.d("On Save State: ","Save terpanggil!");
//        ArrayList<Food2> allFoods = new ArrayList<>();
//        for (Category c : hash1.keySet()) {
//            ArrayList<Food2> foods = hash1.get(c);
//            allFoods.addAll(foods);
//        }
//        outState.putParcelableArrayList("FOODS", allFoods);
//        outState.putParcelableArrayList("HASHMAP",Model.getKategoriArrayList());
        outState.putParcelable("CURRENTCAT", currentCat);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("On Restore State: ","Restore Terpanggil!");

        /*ArrayList<Food2> pasta = savedInstanceState.getParcelableArrayList("Pasta");
        int fId = pasta.get(0).getCatid();
        ArrayList<Category> cats;
        for (Category c : cats) {
            if (c.getFoodId() == fId) {
                hash1.put(c, pasta);
            }
        }*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.clearDB();
        for (Category c : Model.getKategoriArrayList()) {
            Log.d("CATEGORIES: ",c.toString());
          dbHelper.putCategories(c);
        }

        ArrayList<Food2> allFoods = new ArrayList<>();
        for (Category c : hash1.keySet()) {
            ArrayList<Food2> foods = hash1.get(c);
            allFoods.addAll(foods);
        }

        for(Food2 f : allFoods)
        {
            Log.d("FOODS: ",f.toString());
            dbHelper.putFoods(f);
        }

    }
}
