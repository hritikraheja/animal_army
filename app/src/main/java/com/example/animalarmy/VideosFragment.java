package com.example.animalarmy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalarmy.modelclasses.AllVideos;
import com.example.animalarmy.modelclasses.Video;

import java.util.ArrayList;

public class VideosFragment extends Fragment {

    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_videos, container, false);
        recyclerView = view.findViewById(R.id.videosRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ArrayList<Video> videos = AllVideos.getAllVideos();
        recyclerView.setAdapter(new RecyclerViewAdapterForVideosFragment(videos));
        return view;
    }
}