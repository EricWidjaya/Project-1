package com.example.eric.project1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Eric on 7/21/2016.
 */
public class Food2 implements Parcelable{

    private String name;
    private String description;
    private double carbo;
    private double fat;
    private double protein;
    private Category category;


    public Food2 (String name, String description,double carbo, double fat , double protein, Category category) {

        this.name = name;
        this.description = description;
        this.category = category;
        this.carbo = carbo;
        this.fat = fat;
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCarbo() {
        return carbo;
    }

    public void setCarbo(double carbo) {
        this.carbo = carbo;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }




    public Food2(Parcel in)
    {
        name = in.readString();
        description = in.readString();
        carbo = in.readDouble();
        protein = in.readDouble();
        fat = in.readDouble();
        category = in.readParcelable(null);
    }



    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeParcelable(category, i);
    }

    public static final Parcelable.Creator<Food2> CREATOR = new Parcelable.Creator<Food2>() {
        public Food2 createFromParcel(Parcel in) {
            return new Food2(in);
        }

        public Food2[] newArray(int size) {
            return new Food2[size];
        }
    };
}
