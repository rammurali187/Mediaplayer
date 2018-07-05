package com.sssss.httpwww.mediaplayer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private VideoSurfaceView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private SurfaceMediaController mediaControls;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the main layout of the activity
        setContentView(R.layout.activity_main);

        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = new SurfaceMediaController(MainActivity.this);
        }

        //initialize the VideoView
        myVideoView = (VideoSurfaceView) findViewById(R.id.video_view);



        try {
            //set the media controller in the VideoView
            myVideoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();

        myVideoView.start();


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }
}