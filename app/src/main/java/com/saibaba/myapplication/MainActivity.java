package com.saibaba.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MediaPlayer mediaPlayer;
    String selectedMediaFile;
    int selectedImageFile;
    int selectedIndex;
    int selectedImageIndex;
    String arr[];
    int imageArray[];
    boolean isLoopingSet;
    boolean isRamdomSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arr = new String[]{"babanamam1", "babanamam2", "babanamam3", "babanamam4", "babanamam5"};
        //imageArray = new int[]{R.drawable.saibaba, R.drawable.saibaba1, R.drawable.saitune3, R.drawable.saibaba4, R.drawable.saibaba5};
        imageArray = new int[]{R.drawable.bg1, R.drawable.bg2,
                R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
                R.drawable.bg6, R.drawable.bg7, R.drawable.bg8,
                R.drawable.bg9, R.drawable.bg10, R.drawable.bg11,
                R.drawable.bg12, R.drawable.bg13, R.drawable.bg14};


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (selectedMediaFile != null && selectedMediaFile != "") {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(selectedMediaFile, "raw", getPackageName()));
        } else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.babanamam2);
            selectedMediaFile = "babanamam1";
            selectedIndex = 0;
        }

        if (selectedMediaFile != null) {
            getSupportActionBar().setTitle("SaiBaba Namam");
        }
        ImageButton button = (ImageButton) findViewById(R.id.btnFindMe);

        if (mediaPlayer.isPlaying()) {
            //button.setImageResource(R.drawable.pause);
            button.setImageResource(R.drawable.pause);

        } else {
            //button.setImageResource(R.drawable.play1);
            button.setImageResource(R.drawable.play);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkFirstRun(drawer);

    }

    public void checkFirstRun(DrawerLayout drawer) {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun) {
            // Place your dialog code here to display the dialog

            drawer.openDrawer(GravityCompat.START);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }
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

    public void NextButtonClicked(View view) {
        if (++selectedIndex >= arr.length) {
            selectedIndex = 0;
        }
        if(++selectedImageIndex >= imageArray.length)
        {
            selectedImageIndex = 0;
        }
        selectedMediaFile = arr[selectedIndex];
        selectedImageFile = imageArray[selectedImageIndex];
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            playAudio(selectedMediaFile, selectedImageFile, false);
            initMediaPlayer();
        }
    }

    public void PreviousButtonClicked(View view) {
        if (--selectedIndex == -1) {
            selectedIndex = arr.length - 1;
        }
        selectedMediaFile = arr[selectedIndex];
        if(--selectedImageIndex == -1)
        {
            selectedImageIndex = imageArray.length-1;
        }
        selectedImageFile = imageArray[selectedImageIndex];
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            playAudio(selectedMediaFile, selectedImageFile, false);
        }
    }

    public void ShuffleButtonClicked(View view) {
        isRamdomSelected = !isRamdomSelected;
    }

    public void RepeatButtonClicked(View view) {
        if (mediaPlayer.isLooping()) {
            mediaPlayer.setLooping(false);
            isLoopingSet = false;
        } else {
            mediaPlayer.setLooping(true);
            isLoopingSet = true;
        }
    }


    public void PlayButtonClicked(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.btnFindMe);

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            button.setImageResource(R.drawable.play);
            playAudio(arr[selectedIndex], imageArray[selectedImageIndex], true);

        } else {
            button.setImageResource(R.drawable.pause);
            playAudio(arr[selectedIndex], imageArray[selectedImageIndex], false);
        }
    }


    private void initMediaPlayer() {
        ImageButton button = (ImageButton) findViewById(R.id.btnFindMe);
        try {
            button.setImageResource(R.drawable.pause);

        } catch (Exception e) {
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        getSupportActionBar().setTitle("SaiBaba Namam");
        //ImageView qImageView = (ImageView) findViewById(R.id.imageView1);
        //qImageView.setImageResource(R.drawable.saibaba1);
        if (id == R.id.babanamam1) {
            // Handle the camera action
            //Toast.makeText(this, "Saibaba", Toast.LENGTH_SHORT).show();
            //getSupportActionBar().setTitle("Baba Namam");
            selectedMediaFile = "babanamam1";
            selectedImageFile = R.drawable.bg1;

            // qImageView.setImageResource(R.drawable.saibaba);
        } else if (id == R.id.babanamam2) {
            selectedMediaFile = "babanamam2";
            selectedImageFile = R.drawable.bg2;

        } else if (id == R.id.babanamam3) {
            selectedMediaFile = "babanamam3";
            selectedImageFile = R.drawable.bg3;
        } else if (id == R.id.babanamam4) {
            selectedMediaFile = "babanamam4";
            selectedImageFile = R.drawable.bg4;
        } else if (id == R.id.babanamam5) {
            selectedMediaFile = "babanamam5";
            selectedImageFile = R.drawable.bg5;
        }
        selectedIndex = Arrays.asList(arr).indexOf(selectedMediaFile);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        mediaPlayer.stop();
        playAudio(selectedMediaFile, selectedImageFile, false);

        initMediaPlayer();

        return true;
    }

    private void SetSelectedIndexOnShuffleSelected()
    {
        Random rnd = new Random();
        int randIndex = rnd.nextInt(arr.length);
        if (randIndex == selectedIndex) {
            if (randIndex == arr.length) {
                randIndex--;
            }
            if (randIndex == 0) {
                randIndex++;
            }

        }
        selectedIndex = randIndex;
    }

    public void playAudio(String mediafile, int selectedImageFile, boolean isPauseClicked) {
        if (isPauseClicked) {
            mediaPlayer.pause();
        }
        else {
          //  if (selectedImageIndex >= 2) {
            //    selectedImageIndex = 0;
            //}
            ImageView qImageView = (ImageView) findViewById(R.id.imageView1);
            qImageView.setImageResource(selectedImageFile);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(mediafile, "raw", getPackageName()));
            try {
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                        if (isRamdomSelected) {
                            SetSelectedIndexOnShuffleSelected();
                        } else {

                            if (selectedIndex < arr.length) {
                                selectedIndex++;
                                selectedImageIndex++;
                            }
                            if (selectedIndex == arr.length) {
                                selectedIndex = 0;
                            }
                        }
                        if (selectedImageIndex == 13) {
                            selectedImageIndex = 0;
                        }

                        selectedMediaFile = arr[selectedIndex];
                        playAudio(selectedMediaFile, imageArray[selectedImageIndex], false);
                    }
                });
                mediaPlayer.start();
                mediaPlayer.setLooping(isLoopingSet);

            } catch (Exception e) {
                Log.i("media", e.getMessage());
            }
        }
    }
}
