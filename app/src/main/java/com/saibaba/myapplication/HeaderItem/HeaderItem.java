package com.saibaba.myapplication.HeaderItem;

import android.preference.PreferenceActivity;

import java.security.PublicKey;

/**
 * Created by MITHUN on 11/24/2017.
 */

public class HeaderItem
{
    public String name;
    public int imageName;

    public HeaderItem(String name, int imageName){
        this.name = name;
        this.imageName = imageName;
    }

    public String getName(){
        return name;
    }

    public int getImageName(){
        return imageName;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImageName(int imageName){
        this.imageName = imageName;
    }
}
