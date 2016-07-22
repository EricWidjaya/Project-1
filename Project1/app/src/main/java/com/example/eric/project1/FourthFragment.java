package com.example.eric.project1;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FourthFragment extends Fragment {


    public FourthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String desc = bundle.getString("desc");
        String carbo = bundle.getString("carbo");
        String fat = bundle.getString("fat");
        String protein = bundle.getString("protein");
        String images = bundle.getString("images");

        TextView nameView = (TextView) view.findViewById(R.id.name1);
        TextView descView = (TextView) view.findViewById(R.id.desc1);
        TextView carboView = (TextView) view.findViewById(R.id.carbo1);
        TextView fatView = (TextView) view.findViewById(R.id.fat1);
        TextView proteinView = (TextView) view.findViewById(R.id.protein1);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);

        Bitmap bmImg = BitmapFactory.decodeFile(images);

        nameView.setText(name);
        descView.setText(desc);
        carboView.setText(carbo);
        fatView.setText(fat);
        proteinView.setText(protein);
        imageView.setImageBitmap(bmImg);


        return view;
    }
    /*
    public void decodeUri(Uri uri) {
        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = ((MainActivity)getActivity()).getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            // the new size we want to scale to
            final int REQUIRED_SIZE = 1024;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

            imageview.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            // handle errors
        } catch (IOException e) {
            // handle errors
        } finally {
            if (parcelFD != null)
                try {
                    parcelFD.close();
                } catch (IOException e) {
                    // ignored
                }
        }
    }*/

}
