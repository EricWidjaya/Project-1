package com.example.eric.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.eric.project1.FeedReaderContract.FoodEntry;
import com.example.eric.project1.FeedReaderContract.CatEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mico Yohanes on 28/07/2016.
 */

public class FeedReaderDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Food.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String COMMA_SEP = ",";


    public FeedReaderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_FOODS =
                "CREATE TABLE " + FoodEntry.TABLE_NAME + " (" +
                        FoodEntry._ID + " INTEGER PRIMARY KEY," +
                        FoodEntry.COLUMN_ID + INT_TYPE + COMMA_SEP +
                        FoodEntry.COLUMN_FOODNAME + TEXT_TYPE + COMMA_SEP +
                        FoodEntry.COLUMN_DESC + TEXT_TYPE + COMMA_SEP +
                        FoodEntry.COLUMN_CARBO + DOUBLE_TYPE + COMMA_SEP +
                        FoodEntry.COLUMN_FAT + DOUBLE_TYPE + COMMA_SEP +
                        FoodEntry.COLUMN_PROTEIN + DOUBLE_TYPE + COMMA_SEP +
                        FoodEntry.COLUMN_IMG + TEXT_TYPE +
                        " )";

        String SQL_CREATE_CATEGORIES =
                "CREATE TABLE " + CatEntry.TABLE_NAME + " (" +
                        CatEntry._ID + " INTEGER PRIMARY KEY," +
                        CatEntry.COLUMN_CAT_ID + INT_TYPE + COMMA_SEP +
                        CatEntry.COLUMN_CAT_NAME + TEXT_TYPE +
                        " )";

        sqLiteDatabase.execSQL(SQL_CREATE_FOODS);
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void putFoods(Food2 food2)
    {
//        FeedReaderDBHelper db = this.getWritableDatabase();
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FoodEntry.COLUMN_ID, food2.getCatid());
        values.put(FoodEntry.COLUMN_FOODNAME, food2.getName());
        values.put(FoodEntry.COLUMN_DESC, food2.getDescription());
        values.put(FoodEntry.COLUMN_CARBO, food2.getCarbo());
        values.put(FoodEntry.COLUMN_FAT, food2.getFat());
        values.put(FoodEntry.COLUMN_PROTEIN, food2.getProtein());
        values.put(FoodEntry.COLUMN_IMG, food2.getImages());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FoodEntry.TABLE_NAME,
                null,
                values);
    }

    public void putCategories(Category category)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CatEntry.COLUMN_CAT_ID,category.getFoodId());
        values.put(CatEntry.COLUMN_CAT_NAME,category.getFoodName());

        long newRowId;
        newRowId = db.insert(
                CatEntry.TABLE_NAME,
                null,
                values);
    }

    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> categoriesList = new ArrayList<>();

        String query = "SELECT * FROM " + CatEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(CatEntry.COLUMN_CAT_ID));
                String name = cursor.getString(cursor.getColumnIndex(CatEntry.COLUMN_CAT_NAME));

                Category c = new Category(id, name);
                categoriesList.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categoriesList;
    }

    public ArrayList<Food2> getFood()
    {
        ArrayList<Food2> foodList = new ArrayList<>();

        String query = "SELECT * FROM " + FoodEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(FoodEntry.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(FoodEntry.COLUMN_FOODNAME));
                String desc = cursor.getString(cursor.getColumnIndex(FoodEntry.COLUMN_DESC));
                double carbo = cursor.getDouble(cursor.getColumnIndex(FoodEntry.COLUMN_CARBO));
                double fat = cursor.getDouble(cursor.getColumnIndex(FoodEntry.COLUMN_FAT));
                double protein = cursor.getDouble(cursor.getColumnIndex(FoodEntry.COLUMN_PROTEIN));
                String image = cursor.getString(cursor.getColumnIndex(FoodEntry.COLUMN_IMG));

                Food2 f = new Food2(id, name, desc, carbo, fat, protein, image);
                foodList.add(f);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public void clearDB()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(FoodEntry.TABLE_NAME,null,null);
        db.delete(CatEntry.TABLE_NAME,null,null);
    }


}
