package com.example.animalarmy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalarmy.modelclasses.AnimalShelter;

import java.util.LinkedList;

public class RecyclerViewAdapterForMapFrag extends RecyclerView.Adapter<RecyclerViewAdapterForMapFrag.Holder> {
    LinkedList<AnimalShelter> animalShelterNearMe;

    public RecyclerViewAdapterForMapFrag(LinkedList<AnimalShelter> animalShelterNearMe) {
        this.animalShelterNearMe = animalShelterNearMe;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_layout_for_map_fragment, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.shelterName.setText("Name : " + animalShelterNearMe.get(position).getName());
        holder.shelterPhone.setText("Contact : " + animalShelterNearMe.get(position).getPhoneNumber());
        holder.shelterAdd.setText("Address : " + animalShelterNearMe.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return animalShelterNearMe.size();
    }
    
    class Holder extends RecyclerView.ViewHolder{
        TextView shelterName;
        TextView shelterPhone;
        TextView shelterAdd;
        public Holder(@NonNull View itemView) {
            super(itemView);
            shelterName = itemView.findViewById(R.id.shelterName);
            shelterPhone = itemView.findViewById(R.id.shelterPhone);
            shelterAdd = itemView.findViewById(R.id.shelterAdd);
        }
    }
}
