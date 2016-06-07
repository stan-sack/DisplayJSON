package com.example.stan.displayjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by stan on 7/06/16.
 */
public class CustomAdapter extends ArrayAdapter<Object> {

    public CustomAdapter(Context context, Object[] rows){
        super(context,R.layout.row_layout_with_image, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.row_layout_with_image, parent, false);
        

    }

    @Override
    public Object getItem(int position) {
        TableRow = super(position);
        return position;
    }

}
