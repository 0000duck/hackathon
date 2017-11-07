package com.keba.keba.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.keba.keba.R;
import com.keba.keba.backend.Backend;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *  Created by Sparrow on 10/3/2017.
 */
public class AddVideoActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;

    @BindView(R.id.activity_addvideo_save) Button saveButton;
    @BindView(R.id.activity_addvideo_video) VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvideo);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));

//        // 1. Create a default TrackSelector
//        Handler mainHandler = new Handler();
//        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
//
//        // 2. Create the player
//        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);


        videoView.setVideoPath("http://" + Backend.IP + "/keba_video1_wo.mp4");

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    //////////////////////////
    // Action bar button
    /////////////////////////
    @OnClick(R.id.activity_addvideo_cancel)
    public void onClickCancelTask(View view) {
        this.finish();
    }

    @OnClick(R.id.activity_addvideo_camera)
    public void onClickVideoButton(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @OnClick(R.id.activity_addvideo_save)
    public void onClickSaveButton(View view) {
        
        this.finish();
    }




    //////////////////////////
    // Helper
    /////////////////////////

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case REQUEST_VIDEO_CAPTURE: {
                Uri videoUri = intent.getData();
                videoView.setVideoURI(videoUri);



                videoView.start();


                break;
            }
        }

    }
    
}