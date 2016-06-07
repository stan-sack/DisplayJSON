package com.example.stan.displayjson;

import android.graphics.Bitmap;

/**
 * Created by stan on 6/06/16.
 */
public class TableRow {
    private String name;
    private String description;
    private Bitmap icon;

    public TableRow(String name, String description, Bitmap icon){
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Bitmap getBitmap(){
        return icon;
    }
}
