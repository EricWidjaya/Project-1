package com.example.eric.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdFragment extends Fragment implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    private String path;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);

        Button buttonLoadImage = (Button) view.findViewById(R.id.button2);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });



        return view;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.button :

                Button button = (Button) view.findViewById(R.id.button);
                button.setOnClickListener(this);

                EditText editText = (EditText) getView().findViewById(R.id.name);
                String text = editText.getText().toString();
                EditText editText1 = (EditText) getView().findViewById(R.id.desc);
                String text1 = editText1.getText().toString();
                EditText editText2 = (EditText) getView().findViewById(R.id.carbo);
                String text2 = editText2.getText().toString();
                EditText editText3 = (EditText) getView().findViewById(R.id.fat);
                String text3 = editText3.getText().toString();
                EditText editText4 = (EditText) getView().findViewById(R.id.protein);
                String text4 = editText4.getText().toString();
                TextView editText5 = (TextView) getView().findViewById(R.id.imagespath);
                String text5 = path;

                FourthFragment newFragment = new FourthFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();

                bundle.putString("name", text);
                bundle.putString("desc", text1);
                bundle.putString("carbo", text2);
                bundle.putString("fat", text3);
                bundle.putString("protein", text4);
                bundle.putString("images", text5);

                newFragment.setArguments(bundle);

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && ((MainActivity)getActivity()).rCode(resultCode) && null != data) {
            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = ((MainActivity)getActivity()).getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) getView().findViewById(R.id.imageView2);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

           // TextView editText5 = (TextView) getView().findViewById(R.id.imagespath);
            //String text5 = editText5.getText().toString();
            //editText5.setText(picturePath);

            path = picturePath;

        }
    }

}
