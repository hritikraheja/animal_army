package com.example.animalarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.animalarmy.modelclasses.Video;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    ImageView icon;
    TextView titleTV;
    TextView channelTV;
    TextView descriptionTV;
    MediaController mediaController;
    ImageView backButton;
    TextView warningTV;
    ImageView warningIcon;
    TextView warningMessageTV;
    Button proceedButton;
    static int currentVideoPosition;
    ImageView fullScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.videoView);
        currentVideoPosition = 0;
        icon = findViewById(R.id.icon);
        titleTV = findViewById(R.id.titleTV);
        channelTV = findViewById(R.id.channelTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        backButton = findViewById(R.id.backButton);
        fullScreenButton = findViewById(R.id.fullscreenButton);
        warningIcon = findViewById(R.id.warningIcon);
        warningMessageTV = findViewById(R.id.warningMessageTV);
        warningTV = findViewById(R.id.warningTV);
        proceedButton = findViewById(R.id.proceedButton1);
        videoView.setVisibility(View.INVISIBLE);
        fullScreenButton.setVisibility(View.INVISIBLE);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warningTV.setVisibility(View.INVISIBLE);
                warningIcon.setVisibility(View.INVISIBLE);
                warningMessageTV.setVisibility(View.INVISIBLE);
                proceedButton.setVisibility(View.INVISIBLE);
                videoView.setVisibility(View.VISIBLE);
                fullScreenButton.setVisibility(View.VISIBLE);
                videoView.start();
            }
        });
        Intent intent = getIntent();
        final Video video = (Video) intent.getSerializableExtra("video");
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
        icon.setImageResource(video.getIconLocation());
        titleTV.setText(video.getTitle());
        channelTV.setText(video.getOrganisationName());
        descriptionTV.setText(video.getDescription());
        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, FullScreenVideoPlayer.class);
                intent.putExtra("video", video);
                videoView.pause();
                intent.putExtra("position",videoView.getCurrentPosition());
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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