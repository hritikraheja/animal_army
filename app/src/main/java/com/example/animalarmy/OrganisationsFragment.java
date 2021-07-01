package com.example.animalarmy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalarmy.modelclasses.AllOrganisations;
import com.example.animalarmy.modelclasses.Organisation;

import java.util.LinkedList;

public class OrganisationsFragment extends Fragment {

    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_organisations_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AllOrganisations allOrganisations = new AllOrganisations();
        LinkedList<Organisation> listOfOrganisations = allOrganisations.getListOfAllOrganisations();
        recyclerView.setAdapter(new RecyclerViewAdapter(listOfOrganisations));
        return view;
    }
}