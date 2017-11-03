package com.saibaba.myapplication;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
View view;
    MediaPlayer mediaPlayer;
    String selectedMediaFile;
    int selectedImageFile;
    int selectedIndex;
    int selectedImageIndex;
    String arr[];
    int imageArray[];
    boolean isLoopingSet;
    boolean isRamdomSelected;
    ImageView qImageView;
    ImageButton button;
    ImageButton btnFindMe;
    ImageButton img_nxtButton,img_previous, img_repeatButton, img_shuffleButton;
    FloatingActionButton fab_plus, fab_share, fab_download;
    Animation fabOpen, fabClose, fabRotateClockWise, fabRotateAntiClockWise;
    Boolean isOpen = false;
    public MainFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.main_fragment, container, false);
        qImageView = (ImageView)view.findViewById(R.id.imageView1);
        btnFindMe = (ImageButton)view.findViewById(R.id.btnFindMe);
        img_nxtButton = (ImageButton)view.findViewById(R.id.img_nxtButton);
        img_previous = (ImageButton)view.findViewById(R.id.img_previous);
        img_repeatButton = (ImageButton)view.findViewById(R.id.imageButton4);
        img_shuffleButton = (ImageButton)view.findViewById(R.id.imageButton5);
        fab_plus = (FloatingActionButton)view.findViewById(R.id.fab_plus);
        fab_share = (FloatingActionButton)view.findViewById(R.id.fab_share);
        fab_download = (FloatingActionButton)view.findViewById(R.id.fab_download);
        fabOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fb_close);
        fabRotateClockWise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_clockwise);
        fabRotateAntiClockWise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_anticlockwise);
        fab_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(isOpen)
                {
                    fab_share.startAnimation(fabClose);
                    fab_download.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRotateAntiClockWise);
                    fab_share.setClickable(false);
                    fab_download.setClickable(false);
                    isOpen = false;
                }
                else
                {
                    fab_share.startAnimation(fabOpen);
                    fab_download.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabRotateClockWise);
                    fab_share.setClickable(true);
                    fab_download.setClickable(true);
                    isOpen = true;
                }
            }
        });





        arr = new String[]{"babanamam1", "babanamam2", "babanamam3", "babanamam4", "babanamam5"};
        imageArray = new int[]{R.drawable.bg1, R.drawable.bg2,
                R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
                R.drawable.bg6, R.drawable.bg7, R.drawable.bg8,
                R.drawable.bg9, R.drawable.bg10, R.drawable.bg11,
                R.drawable.bg12, R.drawable.bg13, R.drawable.bg14};

        if (selectedMediaFile != null && selectedMediaFile != "") {
            mediaPlayer = MediaPlayer.create(getActivity(), getResources().getIdentifier(selectedMediaFile, "raw",getActivity().getPackageName()));
        } else {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.babanamam1);
            selectedMediaFile = "babanamam1";
            selectedIndex = 0;
        }

        if (selectedMediaFile != null) {
           // getActivity().getActionBar().setTitle("SaiBaba Namam");
        }

        if (mediaPlayer.isPlaying()) {
            btnFindMe.setImageResource(R.drawable.ic_pause);
        } else {

            btnFindMe.setImageResource(R.drawable.ic_play);
        }

        btnFindMe.setOnClickListener(this);
        img_nxtButton.setOnClickListener(this);
        img_previous.setOnClickListener(this);
        img_repeatButton.setOnClickListener(this);
        img_shuffleButton.setOnClickListener(this);

        fab_share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //String sharePath = Environment.getExternalStorageDirectory().getPath()
                  //      + "android:resource://"+getActivity().getPackageName()+"/raw/"+ selectedMediaFile;
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("audio/*");// You Can set source type here like video, image text, etc.
                //File file = new File(filePath);
                //Uri uri = Uri.fromFile(file);
                Uri uri = Uri.parse("android:resource://"+getActivity().getPackageName()+"/raw/"+ selectedMediaFile);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(shareIntent, "Share Via!"));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Saibaba Namam");
    }
    public void NextButtonClicked() {
        if (++selectedIndex >= arr.length) {
            selectedIndex = 0;
        }
        if (++selectedImageIndex >= imageArray.length) {
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

    public void PreviousButtonClicked() {
        if (--selectedIndex == -1) {
            selectedIndex = arr.length - 1;
        }
        selectedMediaFile = arr[selectedIndex];
        if (--selectedImageIndex == -1) {
            selectedImageIndex = imageArray.length - 1;
        }
        selectedImageFile = imageArray[selectedImageIndex];
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            playAudio(selectedMediaFile, selectedImageFile, false);
        }
    }

    public void ShuffleButtonClicked() {
        isRamdomSelected = !isRamdomSelected;
    }

    public void RepeatButtonClicked() {
        if (mediaPlayer.isLooping()) {
            mediaPlayer.setLooping(false);
            isLoopingSet = false;
        } else {
            mediaPlayer.setLooping(true);
            isLoopingSet = true;
        }
    }


    public void PlayButtonClicked() {


        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnFindMe.setImageResource(R.drawable.ic_play);
            playAudio(arr[selectedIndex], imageArray[selectedImageIndex], true);

        } else {
            btnFindMe.setImageResource(R.drawable.ic_pause);
            playAudio(arr[selectedIndex], imageArray[selectedImageIndex], false);
        }
    }


    private void initMediaPlayer() {
        try {
            btnFindMe.setImageResource(R.drawable.ic_pause);

        } catch (Exception e) {
        }
    }


    private void SetSelectedIndexOnShuffleSelected() {
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
        } else {

            qImageView.setImageResource(selectedImageFile);
            if(getActivity()!=null)
            mediaPlayer = MediaPlayer.create(getActivity(), getResources().getIdentifier(mediafile, "raw", getActivity().getPackageName()));
            try {
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
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
//                Log.i("media", e.getMessage());
            }
        }
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnFindMe:
                    PlayButtonClicked();
                    break;
                case R.id.img_nxtButton:
                    NextButtonClicked();
                    break;
                case R.id.img_previous:
                    PreviousButtonClicked();
                    break;

                case R.id.imageButton4:
                    RepeatButtonClicked();
                    break;

                case R.id.imageButton5:
                    ShuffleButtonClicked();
                    break;
            }
    }
}
