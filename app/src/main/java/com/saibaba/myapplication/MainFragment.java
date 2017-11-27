package com.saibaba.myapplication;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saibaba.myapplication.HeaderItem.HeaderItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    public Dialog dialog;
    View view;
    MediaPlayer mediaPlayer;
    ImageView openList;
    String selectedMediaFile;
    int selectedImageFile;
    int selectedIndex;
    int selectedImageIndex;
    String arr[];
    TextView songTitle;
    int imageArray[];
    boolean isLoopingSet;
    boolean isRamdomSelected;
    RelativeLayout qImageView;
    ImageButton button;
    RelativeLayout buttonLayout;
    ImageButton btnFindMe;
    ImageButton img_nxtButton, img_previous, img_repeatButton, img_shuffleButton;
    ImageButton fab_plus, fab_share, fab_download;
    Animation fabOpen, fabClose, fabRotateClockWise, fabRotateAntiClockWise;
    ArrayList<Object> values;
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
        qImageView = (RelativeLayout) view.findViewById(R.id.parentLayout);
        btnFindMe = (ImageButton) view.findViewById(R.id.btnFindMe);
        img_nxtButton = (ImageButton) view.findViewById(R.id.img_nxtButton);
        img_previous = (ImageButton) view.findViewById(R.id.img_previous);
        img_repeatButton = (ImageButton) view.findViewById(R.id.imageButton4);
        img_shuffleButton = (ImageButton) view.findViewById(R.id.imageButton5);
        fab_plus = (ImageButton) view.findViewById(R.id.fab_plus);
        openList = (ImageView) view.findViewById(R.id.openList);
        songTitle = (TextView)  view.findViewById(R.id.songTitle);
        buttonLayout = (RelativeLayout) view.findViewById(R.id.buttonLayout);
        // fab_share = (FloatingActionButton)view.findViewById(R.id.fab_share);
        //fab_download = (FloatingActionButton)view.findViewById(R.id.fab_download);
        fabOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fb_close);
        fabRotateClockWise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_clockwise);
        fabRotateAntiClockWise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_anticlockwise);
        final ListView songList = (ListView) view.findViewById(R.id.songList);

        values = new ArrayList<>();
        values.add(new HeaderItem("SongListHeader1", R.drawable.bg1));
        values.add("Sai tune1");
        values.add("Sai tune2");
        values.add("Sai tune3");
        values.add("Sai tune4");
        values.add("Sai tune5");
        values.add("Sai tune6");
        values.add("Sai tune7");
        values.add("Sai tune8");
        values.add("Sai tune9");
        values.add("Sai tune10");
        values.add(new HeaderItem("SongListHeader2", R.drawable.bg2));
        values.add("Sai tune11");
        values.add("Sai tune12");
        values.add("Sai tune13");
        values.add("Sai tune14");
        values.add("Sai tune15");
        values.add("Sai tune16");
        values.add("Sai tune17");
        values.add("Sai tune18");
        values.add("Sai tune19");
        values.add("Sai tune20");
        values.add("Sai tune21");
        values.add("Sai tune22");
        values.add("Sai tune23");
        values.add("Sai tune24");
        values.add("Sai tune25");
        values.add("Sai tune26");

        values.add("about SaiPatham");


        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listitem, android.R.id.text1, values);
        //songList.setAdapter(adapter);

        songList.setAdapter(new ListAdapter(getActivity(), values));
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(view.getTag() == null) {
                    return;
                }

                if((int) view.getTag() == 1012) {
                    if (i == arr.length - 1) {
                        getFragmentManager().beginTransaction().replace(R.id.content_frame, new AboutSaibabaFragment()).addToBackStack(null).commit();
                    } else {
                        slideToBottom(songList);
                        songTitle.setText(((String) values.get(i)));
                        selectedIndex = i;
                        buttonLayout.setVisibility(view.VISIBLE);
                        playAudio(arr[i], imageArray[i], false);
                    }
                }

            }


        });

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_plus.setVisibility(view.INVISIBLE);
                CustomDialog();
                /*if(isOpen)
                {
                    slideToTop(songList);
                    fab_share.startAnimation(fabClose);
                    fab_download.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRotateAntiClockWise);
                    fab_share.setClickable(false);
                    fab_download.setClickable(false);
                    isOpen = false;
                }
                else
                {
                    slideToBottom(songList);
                    fab_share.startAnimation(fabOpen);
                    fab_download.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabRotateClockWise);
                    fab_share.setClickable(true);
                    fab_download.setClickable(true);
                    isOpen = true;
                }*/
            }
        });

        openList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* fab_plus.setVisibility(view.INVISIBLE);
                CustomDialog();*/
                if (isOpen) {
                    buttonLayout.setVisibility(view.GONE);
                    //openList.setImageResource(R.drawable.closelist);
                    slideToTop(songList);
                   /* fab_share.startAnimation(fabClose);
                    fab_download.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRotateAntiClockWise);
                    fab_share.setClickable(false);
                    fab_download.setClickable(false);*/
                    isOpen = false;
                } else {
                    buttonLayout.setVisibility(view.VISIBLE);
                    // openList.setImageResource(R.drawable.openlist);
                    slideToBottom(songList);
                    /*fab_share.startAnimation(fabOpen);
                    fab_download.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabRotateClockWise);
                    fab_share.setClickable(true);
                    fab_download.setClickable(true);*/
                    isOpen = true;
                }
            }
        });


        arr = new String[]{"s1", "s2", "s3", "s4",
                "s5", "s6", "s7", "s8",
                "s9", "s10",
                "s11", "s12", "s13", "s14",
                "s15", "s16", "s17", "s18",
                "s19", "s20",
                "s21", "s22", "s23", "s24",
                "s25", "s26",
                "babanamam2"};
        imageArray = new int[]{R.drawable.bg1, R.drawable.bg2,
                R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
                R.drawable.bg6, R.drawable.bg7, R.drawable.bg8,
                R.drawable.bg9, R.drawable.bg10, R.drawable.bg11,
                R.drawable.bg1, R.drawable.bg2,
                R.drawable.bg3, R.drawable.bg4,
                R.drawable.bg6, R.drawable.bg7, R.drawable.bg8,
                R.drawable.bg9, R.drawable.bg10, R.drawable.bg11,
                R.drawable.bg1, R.drawable.bg2,
                R.drawable.bg3, R.drawable.bg4, R.drawable.bg5
        };

        if (selectedMediaFile != null && selectedMediaFile != "") {
            mediaPlayer = MediaPlayer.create(getActivity(), getResources().getIdentifier(selectedMediaFile, "raw", getActivity().getPackageName()));
        } else {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.s1);
            selectedMediaFile = "babanamam1";
            songTitle.setText("Sai tune1");
            selectedIndex = 1;
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

       /* fab_share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //String sharePath = Environment.getExternalStorageDirectory().getPath()
                  //      + "android:resource://"+getActivity().getPackageName()+"/raw/"+ selectedMediaFile;
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("audio*//*");// You Can set source type here like video, image text, etc.
                //File file = new File(filePath);
                //Uri uri = Uri.fromFile(file);
                Uri uri = Uri.parse("android:resource://"+getActivity().getPackageName()+"/raw/"+ selectedMediaFile);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(shareIntent, "Share Via!"));
            }
        });*/

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnFindMe.setImageResource(R.drawable.ic_play);
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
                        selectedIndex = 1;
                    }
                }
                if (selectedImageIndex == 13) {
                    selectedImageIndex = 0;
                }
                if(selectedIndex == 0 || selectedIndex == 11)
                {
                    selectedIndex++;
                }
                selectedMediaFile = arr[selectedIndex];
                songTitle.setText(((String) values.get(selectedIndex)));
                playAudio(selectedMediaFile, imageArray[selectedImageIndex], false);
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
            selectedIndex = 1;
        }
        if (++selectedImageIndex >= imageArray.length) {
            selectedImageIndex = 0;
        }
        if(selectedIndex == 0 || selectedIndex == 11)
        {
            selectedIndex++;
        }
        selectedMediaFile = arr[selectedIndex];
        if(values.get(selectedIndex) instanceof String ) {
            songTitle.setText((String) values.get(selectedIndex));
        }
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
        if(selectedIndex == 0 || selectedIndex == 11)
        {
            selectedIndex++;
        }
        selectedMediaFile = arr[selectedIndex];
        if (--selectedImageIndex == -1) {
            selectedImageIndex = imageArray.length - 1;
        }
        songTitle.setText(((String) values.get(selectedIndex)));
        selectedImageFile = imageArray[selectedImageIndex];
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            playAudio(selectedMediaFile, selectedImageFile, false);
        }
    }

    public void ShuffleButtonClicked() {
        if(isRamdomSelected) {
            img_shuffleButton.setImageResource(R.drawable.shuffle);
        }else{
            img_shuffleButton.setImageResource(R.drawable.shuffle_active);
        }
        isRamdomSelected = !isRamdomSelected;

    }

    public void RepeatButtonClicked() {
        if (mediaPlayer.isLooping()) {
            mediaPlayer.setLooping(false);
            isLoopingSet = false;
            img_repeatButton.setImageResource(R.drawable.repeat);
        } else {
            mediaPlayer.setLooping(true);
            isLoopingSet = true;
            img_repeatButton.setImageResource(R.drawable.repeat_active);
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
           // btnFindMe.setImageResource(R.drawable.ic_play);
        } else {

            qImageView.setBackground(getResources().getDrawable(selectedImageFile));

            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                btnFindMe.setImageResource(R.drawable.ic_play);
                mediaPlayer.reset();
            }
            if (getActivity() != null)

                mediaPlayer = MediaPlayer.create(getActivity(), getResources().getIdentifier(mediafile, "raw", getActivity().getPackageName()));
            try {
                btnFindMe.setImageResource(R.drawable.ic_pause);
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

    public void CustomDialog() {

        dialog = new Dialog(getActivity());
        // it remove the dialog title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set the laytout in the dialog
        dialog.setContentView(R.layout.dialogbox);
        // set the background partial transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();
        // set the layout at right bottom
        param.gravity = Gravity.TOP | Gravity.RIGHT;
        // it dismiss the dialog when click outside the dialog frame
        dialog.setCanceledOnTouchOutside(true);
        // initialize the item of the dialog box, whose id is demo1
        View demodialog = (View) dialog.findViewById(R.id.cross);
        //RelativeLayout share = (RelativeLayout) dialog.findViewById(R.id.share);
        final RelativeLayout download = (RelativeLayout) dialog.findViewById(R.id.download);
       /* share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String sharePath = Environment.getExternalStorageDirectory().getPath()
                //      + "android:resource://"+getActivity().getPackageName()+"/raw/"+ selectedMediaFile;
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                //shareIntent.setType("audio/mp3");// You Can set source type here like video, image text, etc.
                //File file = new File(filePath);
                //Uri uri = Uri.fromFile(file);
               // File f = new File();
                shareIntent.setType("audio*//*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://"+"com.saibaba.myapplication"+"/raw/"+ selectedMediaFile));



               // shareIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });*/
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadSong(R.raw.s1, selectedMediaFile);
            }
        });
        // it call when click on the item whose id is demo1.
        demodialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // diss miss the dialog
                fab_plus.setVisibility(view.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);

        // it show the dialog box
        dialog.show();

    }

    // To animate view slide out from top to bottom
    public void slideToBottom(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, getActivity().getWindowManager().getDefaultDisplay().getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from bottom to top
    public void slideToTop(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -15);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);
    }

    public void downloadSong(int songId, String selectedMediaFile) {
        try {
            String path = Environment.getExternalStorageDirectory() + "/SaiNamam";
            File dir = new File(path);
            if (dir.mkdirs() || dir.isDirectory()) {
                String str_song_name = selectedMediaFile + ".mp3";
                CopyRAWtoSDCard(songId, path + File.separator + str_song_name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void CopyRAWtoSDCard(int id, String path) throws IOException {
        InputStream in = getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
            Toast.makeText(getActivity(),"SaiNamam downloaded",Toast.LENGTH_LONG).show();
        } finally {
            in.close();
            out.close();
        }


    }
}
