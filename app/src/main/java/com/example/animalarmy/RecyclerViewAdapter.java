package com.example.animalarmy;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalarmy.modelclasses.Organisation;

import java.util.LinkedList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    LinkedList<Organisation> allOrganisations;

    public RecyclerViewAdapter(LinkedList<Organisation> allOrganisations){
        this.allOrganisations = allOrganisations;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        holder.nameView.setText(allOrganisations.get(position).getName());
        holder.iconView.setImageResource(allOrganisations.get(position).getIcon());
        String url = allOrganisations.get(position).getSiteUrl();
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.button.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allOrganisations.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        ImageView iconView;
        Button button;
        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            iconView = view.findViewById(R.id.icon);
            button = view.findViewById(R.id.siteButton);
        }
    }
}
