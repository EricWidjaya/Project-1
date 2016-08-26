package com.example.eric.project1;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    private FoodAdapter foodAdapter;
    private ListView listView;
    private Category cat;
    private String path;
    private View viewD;
    private SearchView searchView;

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
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ((MainActivity)getActivity()).verifyStoragePermissions(getActivity());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        cat = ((MainActivity)getActivity()).getCurrentCat();
        ((MainActivity)getActivity()).setActionBarTitle(cat.getFoodName());
        foodAdapter = new FoodAdapter(((MainActivity)getActivity()).getHash1().get(cat),getContext());

        listView = (ListView) getActivity().findViewById(R.id.listView2);
        TextView empty = (TextView) getActivity().findViewById(android.R.id.empty);
        listView.setEmptyView(empty);
        listView.setAdapter(foodAdapter);

        searchView = (SearchView) getActivity().findViewById(R.id.searchView1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                if(TextUtils.isEmpty(text))
                {
                    foodAdapter = new FoodAdapter(((MainActivity)getActivity()).getHash1().get(cat),getContext());
                    listView.setAdapter(foodAdapter);
                }

                else if (!TextUtils.isEmpty(text))
                {
                    foodAdapter.getFilter().filter(text.toString());

                    /*if (foodAdapter.equals(null))
                    {
                        Toast.makeText(getContext(), "No Result Found!", Toast.LENGTH_SHORT).show();
                    }*/
                }
                return false;
            }
        });

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
            case R.id.action_add:
                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                viewD = inflater.inflate(R.layout.dialog_box, null);
                inputAlert.setView(viewD);

                Button buttonLoadImage = (Button) viewD.findViewById(R.id.button);
                buttonLoadImage.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        Intent i = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }
                });

                inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        EditText addName = (EditText) viewD.findViewById(R.id.addName);
                        EditText addDesc = (EditText) viewD.findViewById(R.id.addDesc);
                        EditText addCarb = (EditText) viewD.findViewById(R.id.addCarb);
                        EditText addFat = (EditText) viewD.findViewById(R.id.addFat);
                        EditText addProt = (EditText) viewD.findViewById(R.id.addProtein);
                        String txt5 = path;
                        //ImageView imgs = (ImageView) viewD.findViewById(R.id.imageView);
                        //TextView addImgPath = (TextView) viewD.findViewById(R.id.textVw);

                        int id = cat.getFoodId();
                        String name = addName.getText().toString();
                        String desc = addDesc.getText().toString();
                        double carb = Double.parseDouble(addCarb.getText().toString());
                        double fat = Double.parseDouble(addFat.getText().toString());
                        double prot = Double.parseDouble(addProt.getText().toString());

                        Food2 food2 = new Food2(id, name, desc, carb, fat, prot, txt5);
                        foodAdapter.getFoodArray().add(food2);
                        foodAdapter.notifyDataSetChanged();
                        Log.d("food ID: ",Integer.toString(id) );
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
        }
            //end of switch
            return true;
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == RESULT_LOAD_IMAGE && ((MainActivity) getActivity()).rCode(resultCode) && null != data) {

                    Uri selectedImage = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();


                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    Log.d("Lalala",picturePath);

                    ImageView imageView = (ImageView) viewD.findViewById(R.id.imageView);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                    path = picturePath;

                }

    }
}
