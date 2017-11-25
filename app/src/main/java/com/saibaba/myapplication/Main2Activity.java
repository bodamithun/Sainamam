package com.saibaba.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class Main2Activity extends AppCompatActivity
      /*  implements NavigationView.OnNavigationItemSelectedListener */{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
        //set the default fragment
        android.support.v4.app.FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.content_frame, new MainFragment()).commit();

    }

    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {*/
            super.onBackPressed();
        /*}*/
    }



  /*  @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Boolean mainFrame = false;


        android.support.v4.app.FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        //noinspection SimplifiableIfStatement
        // Handle navigation view item clicks here.
        if (id == R.id.babanamam1) {
            fragmentManager.replace(R.id.content_frame, new MainFragment()).commit();
            mainFrame = true;
        } else if (id == R.id.babanamam2) {

            mainFrame = true;
        } else if (id == R.id.babanamam3) {

            mainFrame = true;
        } else if (id == R.id.babanamam4) {

            mainFrame = true;
        } else if (id == R.id.babanamam5) {

            mainFrame = true;
        }
        else if (id == R.id.aboutsaibaba) {

            //mainFrame = true;
            fragmentManager.replace(R.id.content_frame, new AboutSaibabaFragment()).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


}
