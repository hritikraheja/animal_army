package com.example.animalarmy;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.animalarmy.modelclasses.AllOrganisations;
import com.example.animalarmy.modelclasses.Organisation;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class DonationActivity extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_donation, container, false);
        TextView aau = view.findViewById(R.id.aau);
        aau.setOnClickListener(this);
        TextView ar = view.findViewById(R.id.ar);
        ar.setOnClickListener(this);
        TextView bgrd = view.findViewById(R.id.bgrd);
        bgrd.setOnClickListener(this);
        TextView bcoi = view.findViewById(R.id.bcoi);
        bcoi.setOnClickListener(this);
        TextView care = view.findViewById(R.id.care);
        care.setOnClickListener(this);
        TextView cupa = view.findViewById(R.id.cupa);
        cupa.setOnClickListener(this);
        TextView drf = view.findViewById(R.id.drf);
        drf.setOnClickListener(this);
        TextView fiapo = view.findViewById(R.id.fiapo);
        fiapo.setOnClickListener(this);
        TextView fs = view.findViewById(R.id.fs);
        fs.setOnClickListener(this);
        TextView his = view.findViewById(R.id.his);
        his.setOnClickListener(this);
        TextView peta = view.findViewById(R.id.peta);
        peta.setOnClickListener(this);
        TextView posh = view.findViewById(R.id.posh);
        posh.setOnClickListener(this);
        TextView pfa = view.findViewById(R.id.pfa);
        pfa.setOnClickListener(this);
        TextView rpr = view.findViewById(R.id.rpr);
        rpr.setOnClickListener(this);
        TextView sgacc = view.findViewById(R.id.sgacc);
        sgacc.setOnClickListener(this);
        TextView vspca = view.findViewById(R.id.vspca);
        vspca.setOnClickListener(this);
        TextView straw = view.findViewById(R.id.straw);
        straw.setOnClickListener(this);
        TextView wsos = view.findViewById(R.id.wsos);
        wsos.setOnClickListener(this);
        TextView wti = view.findViewById(R.id.wti);
        wti.setOnClickListener(this);
        TextView bspca = view.findViewById(R.id.bspca);
        bspca.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String donationUrl = "https://www.google.com";
        AllOrganisations allOrganisations = new AllOrganisations();
        LinkedList<Organisation> o = allOrganisations.getListOfAllOrganisations();
        switch (id) {
            case R.id.aau:
                donationUrl = o.get(0).getDonationUrl();
                break;
            case R.id.ar:
                donationUrl = o.get(1).getDonationUrl();
                break;
            case R.id.bgrd:
                donationUrl = o.get(2).getDonationUrl();
                break;
            case R.id.bcoi:
                donationUrl = o.get(3).getDonationUrl();
                break;
            case R.id.care:
                donationUrl = o.get(4).getDonationUrl();
                break;
            case R.id.cupa:
                donationUrl = o.get(5).getDonationUrl();
                break;
            case R.id.drf:
                donationUrl = o.get(6).getDonationUrl();
                break;
            case R.id.fiapo:
                donationUrl = o.get(7).getDonationUrl();
                break;
            case R.id.fs:
                donationUrl = o.get(8).getDonationUrl();
                break;
            case R.id.his:
                donationUrl = o.get(9).getDonationUrl();
                break;
            case R.id.peta:
                donationUrl = o.get(10).getDonationUrl();
                break;
            case R.id.posh:
                donationUrl = o.get(11).getDonationUrl();
                break;
            case R.id.pfa:
                donationUrl = o.get(12).getDonationUrl();
                break;
            case R.id.rpr:
                donationUrl = o.get(13).getDonationUrl();
                break;
            case R.id.sgacc:
                donationUrl = o.get(14).getDonationUrl();
                break;
            case R.id.straw:
                donationUrl = o.get(15).getDonationUrl();
                break;
            case R.id.bspca:
                donationUrl = o.get(16).getDonationUrl();
                break;
            case R.id.vspca:
                donationUrl = o.get(17).getDonationUrl();
                break;
            case R.id.wsos:
                donationUrl = o.get(18).getDonationUrl();
                break;
            case R.id.wti:
                donationUrl = o.get(19).getDonationUrl();
                break;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(donationUrl));
        startActivity(intent);
    }
}