package com.example.eric.project1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Eric on 7/21/2016.
 */
public class Category implements Parcelable {

    private int foodId;
    private String foodName;
    private ArrayList<Food2> food2ArrayList = new ArrayList<>();


    public Category(int foodId, String foodName)
        {
            this.foodId=foodId;
            this.foodName=foodName;
        }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Category(Parcel in) {
        foodName = in.readString();
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public ArrayList<Food2> getFoodArrayList()
    {
        return food2ArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getFoodName());
        parcel.writeParcelableArray(food2ArrayList.toArray(new Category[0]), i);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

}
