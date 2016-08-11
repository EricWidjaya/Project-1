package com.example.eric.project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Mico Yohanes on 28/07/2016.
 */
public class FeedReaderContract {


    public FeedReaderContract() {

    }

    public static abstract class FoodEntry implements BaseColumns {

        public static final String TABLE_NAME = "FOODS";
        public static final String COLUMN_ID = "ID";

        public static final String COLUMN_FOODNAME = "foodName";
        public static final String COLUMN_DESC = "foodDesc";
        public static final String COLUMN_CARBO = "foodCarbo";
        public static final String COLUMN_FAT = "foodFat";
        public static final String COLUMN_PROTEIN = "foodProtein";
        public static final String COLUMN_IMG = "image";
    }

    public static abstract class CatEntry implements BaseColumns
    {

        public static final String TABLE_NAME = "CATEGORY";
        public static final String COLUMN_CAT_ID = "catID";
        public static final String COLUMN_CAT_NAME = "catName";
    }


}
