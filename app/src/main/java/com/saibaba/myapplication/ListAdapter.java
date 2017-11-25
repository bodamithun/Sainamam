package com.saibaba.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.saibaba.myapplication.HeaderItem.HeaderItem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by MITHUN on 11/24/2017.
 */

public class ListAdapter extends BaseAdapter {
    ArrayList<Object> list;
    private static final int List_item = 0;
    private static final int Header = 1;
    private LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<Object> list)
    {
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position){
        if(list.get(position) instanceof HeaderItem){
            return Header;
        }
        else {
            return List_item;
        }
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null)
        {
            switch(getItemViewType(position))
            {
                case List_item:
                    view = inflater.inflate(R.layout.listitem, null);
                    view.setTag(1012);

                    break;
                case Header:
                    view = inflater.inflate(R.layout.listitem_header, null);
                    view.setTag(1011);
                    break;
            }

        }
        switch(getItemViewType(position)){
            case List_item:
                TextView songTitle =(TextView) view.findViewById(android.R.id.text1);
                songTitle.setText((String)list.get(position));
                break;
            case Header:
                ImageView imageView = (ImageView) view.findViewById(R.id.headerImageView);
                TextView textView = (TextView) view.findViewById(R.id.headerText);

                imageView.setImageResource(((HeaderItem)list.get(position)).getImageName());
                break;
        }
        return view;
    }
}
