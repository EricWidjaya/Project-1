package com.example.eric.project1;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Eric on 7/22/2016.
 */
public class Model {
    private static ArrayList<Category> kategoriArrayList = new ArrayList<>();

    public static void clearKategoriArrayList() {
        kategoriArrayList.clear();
    }


    public static void addKategoriArrayList(Category kat) {
        kategoriArrayList.add(kat);
    }

    public static ArrayList<Category> getKategoriArrayList() {
        return new ArrayList<>(kategoriArrayList);
    }


}
