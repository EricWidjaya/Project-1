package com.example.eric.project1;

/**
 * Created by Eric on 7/21/2016.
 */
public class Food {

    private int foodId;
    private String foodName;

    public Food(int foodId,String foodName)
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
}
