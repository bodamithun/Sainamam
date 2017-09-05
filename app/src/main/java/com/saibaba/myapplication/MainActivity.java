package com.saibaba.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MediaPlayer mediaPlayer;
    String selectedMediaFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if(selectedMediaFile!= null && selectedMediaFile != "")
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(selectedMediaFile, "raw", getPackageName()));
        }
        else
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.babanamam2);
            selectedMediaFile = "babanamam1";
        }

        if(selectedMediaFile != null) {
            getSupportActionBar().setTitle("Sri Saibaba Namam");
        }
        ImageButton button =(ImageButton) findViewById(R.id.btnFindMe);

        if(mediaPlayer.isPlaying()) {
            //button.setImageResource(R.drawable.pause);
            button.setImageResource(android.R.drawable.ic_media_pause);

        }
        else
        {
            //button.setImageResource(R.drawable.play1);
            button.setImageResource(android.R.drawable.ic_media_play);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void PlayButtonClicked(View view)
    {
        ImageButton button =(ImageButton) findViewById(R.id.btnFindMe);
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            //button.setImageResource(R.drawable.play1);
            button.setImageResource(android.R.drawable.ic_media_play);

        }
        else
        {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            //button.setImageResource(R.drawable.pause);
            button.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    private void initMediaPlayer(String mediafile) {
        ImageButton button =(ImageButton) findViewById(R.id.btnFindMe);
        try {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(mediafile, "raw", getPackageName()));
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                //button.setImageResource(R.drawable.pause);
                button.setImageResource(android.R.drawable.ic_media_pause);

            }
            else
            {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(mediafile, "raw", getPackageName()));
                //button.setImageResource(R.drawable.play1);
                button.setImageResource(android.R.drawable.ic_media_play);

            }
        }
        catch (Exception e) {
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        getSupportActionBar().setTitle("Baba Namam");
        if (id == R.id.babanamam1) {
            // Handle the camera action
            //Toast.makeText(this, "Saibaba", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().setTitle("Baba Namam");
            selectedMediaFile = "babanamam1";
            initMediaPlayer("babanamam1");
        } else if (id == R.id.babanamam2) {
            selectedMediaFile = "babanamam2";
        }
        else if (id == R.id.babanamam2) {
            selectedMediaFile = "babanamam2";
        }
        else if (id == R.id.babanamam4) {
            selectedMediaFile = "babanamam4";
        }
        else if (id == R.id.babanamam5) {
            selectedMediaFile = "babanamam5";
        }
        initMediaPlayer(selectedMediaFile);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
