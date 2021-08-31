package com.example.animalarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.animalarmy.modelclasses.Video;

public class FullScreenVideoPlayer extends AppCompatActivity {

    VideoView videoView;
    ImageView backButton;
    MediaController mediaController;
    static int currentVideoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_video_player);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        Video video = (Video) intent.getSerializableExtra("video");
        currentVideoPosition  = intent.getIntExtra("position", 0);
        videoView = findViewById(R.id.videoView);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                videoView.pause();
                VideoActivity.currentVideoPosition = videoView.getCurrentPosition();
            }
        });

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + video.getVideoLocation()));
        videoView.requestFocus();
        mediaController = new MediaController(this){
            @Override
            public void hide() {
                super.hide();
            }

            @Override
            public void show() {
                super.show(2000);
            }
        };
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(this.videoView);
        videoView.setMediaController(mediaController);
        videoView.seekTo(currentVideoPosition + 200);
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        currentVideoPosition = videoView.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(currentVideoPosition!=0) {
            videoView.seekTo(currentVideoPosition);
            videoView.start();
        }
    }
}