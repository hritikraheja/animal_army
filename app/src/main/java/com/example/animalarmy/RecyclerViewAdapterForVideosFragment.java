package com.example.animalarmy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalarmy.modelclasses.Video;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapterForVideosFragment extends RecyclerView.Adapter<RecyclerViewAdapterForVideosFragment.ViewHolder> {

    ArrayList<Video> videos;

    public RecyclerViewAdapterForVideosFragment(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view_for_videos_frag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.thumbnail.setImageResource(videos.get(position).getVideoThumbnailLocation());
        holder.icon.setImageResource(videos.get(position).getIconLocation());
        holder.titleTV.setText(videos.get(position).getTitle());
        holder.organisationNameTV.setText(videos.get(position).getOrganisationName());
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VideoActivity.class);
                intent.putExtra("video", videos.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        ImageView icon;
        TextView titleTV;
        TextView organisationNameTV;
        ImageView playButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            icon = itemView.findViewById(R.id.icon);
            titleTV = itemView.findViewById(R.id.title);
            organisationNameTV = itemView.findViewById(R.id.channel);
            playButton = itemView.findViewById(R.id.playButton);
        }
    }

}
