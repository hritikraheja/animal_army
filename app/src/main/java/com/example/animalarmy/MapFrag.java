package com.example.animalarmy;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalarmy.modelclasses.AnimalShelter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class MapFrag extends Fragment{
    LinkedList<AnimalShelter> animalSheltersNearLocation = new LinkedList<>();
    String phoneNumber;
    MapView mapView;
    private GoogleMap googleMap;
    String name;
    String address;
    double lat;
    double lng;
    String placeId;
    RecyclerView recyclerView;
    double myLat;
    double myLng;

    public double getMyLat() {
        return myLat;
    }

    public void setMyLat(Double myLat) {
        this.myLat = myLat;
    }

    public double getMyLng() {
        return myLng;
    }

    public void setMyLng(Double myLng) {
        this.myLng = myLng;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map_fragment, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        recyclerView = view.findViewById(R.id.recyclerViewForMapFrag);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Places.initialize(view.getContext(), getString(R.string.google_places_api_key));
        PlacesClient placesClient = Places.createClient(view.getContext());
        final FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        @SuppressLint("MissingPermission") final Task<Location> location = fusedLocationProviderClient.getLastLocation();
        location.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location myLocation = task.getResult();
                    try {
                        setMyLat(myLocation.getLatitude());
                        setMyLng(myLocation.getLongitude());
                    } catch (Exception e1) {
                        setMyLat(26.847698895647113);
                        setMyLng(80.947585162432);
                    }
                    int numberOfTries = 0;
                    while(getMyLat()==26.847698895647113 && getMyLng() == 80.947585162432){
                        try {
                            setMyLat(myLocation.getLatitude());
                            setMyLng(myLocation.getLongitude());
                        } catch (Exception e1) {
                            setMyLat(26.847698895647113);
                            setMyLng(80.947585162432);
                        }
                        numberOfTries++;
                        if(numberOfTries >= 5){
                            getFragmentManager().beginTransaction().replace(R.id.frameLayout, new MapFrag()).commit();
                        }
                    }
                }
                new GetJsonFromUrl().execute(String.valueOf(getMyLat()), String.valueOf(getMyLng()));
            }
        });
        //Toast.makeText(MapFrag.this.getContext(), this.getMyLat() + ", " + this.getMyLng(), Toast.LENGTH_LONG).show();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(@NonNull final GoogleMap gMap) {
                googleMap = gMap;
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        LatLng myLocation = new LatLng(getMyLat(), getMyLng());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @SuppressLint("StaticFieldLeak")
    class GetJsonFromUrl extends AsyncTask<String, Integer, String>{
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected String doInBackground(String... string) {
            double mLat = Double.parseDouble(string[0]);
            double mLng = Double.parseDouble(string[1]);
            Looper.prepare();
            Toast.makeText(getContext(), "Your Coordinates : " + mLat + ", " + mLng, Toast.LENGTH_LONG).show();
            String result = null;
            try{
                URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query=animal+shelter&location="+ mLat + "," + mLng +"&radius=10000&key=" + getString(R.string.google_places_api_key));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder sb = new StringBuilder();
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null){
                        sb.append(temp);
                        sb.append("\n");
                    }
                    result = sb.toString();
                } else {
                    result = "ERROR: HTTP NOT OK";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject allData = new JSONObject(s);
                JSONArray array = allData.getJSONArray("results");
                for(int i = 0; i < array.length(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    name = obj.getString("name");
                    address = obj.getString("formatted_address");
                    lat = Double.parseDouble(obj.getJSONObject("geometry").getJSONObject("location").getString("lat"));
                    lng = Double.parseDouble(obj.getJSONObject("geometry").getJSONObject("location").getString("lng"));
                    placeId = obj.getString("place_id");
                    AnimalShelter animalShelter = new AnimalShelter(name,lat,lng,address,"Not Available");
                    animalSheltersNearLocation.add(animalShelter);
                    new GetPhoneNumberFromUrl().execute(placeId, String.valueOf(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class GetPhoneNumberFromUrl extends AsyncTask<String, Integer, String>{
        int pos;
        @Override
        protected String doInBackground(String... strings) {
            String pId = strings[0];
            pos = Integer.parseInt(strings[1]);
            String result = "";
            try{
                URL url = new URL("https://maps.googleapis.com/maps/api/place/details/json?place_id="+pId+"&fields=formatted_phone_number&key=" + getString(R.string.google_places_api_key));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder sb = new StringBuilder();
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null){
                        sb.append(temp);
                        sb.append("\n");
                    }
                    result = sb.toString();
                } else {
                    result = "ERROR: HTTP NOT OK";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s).getJSONObject("result");
                try{
                    phoneNumber = object.getString("formatted_phone_number");
                } catch (JSONException e) {
                    phoneNumber = "Not Available";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            animalSheltersNearLocation.get(pos).setPhoneNumber(phoneNumber);
            recyclerView.setAdapter(new RecyclerViewAdapterForMapFrag(animalSheltersNearLocation));
            for(AnimalShelter animalShelter : animalSheltersNearLocation){
                double shelterLatitude = animalShelter.getLat();
                double shelterLongitude = animalShelter.getLng();
                LatLng location = new LatLng(shelterLatitude, shelterLongitude);
                googleMap.addMarker(new MarkerOptions().position(location).title(animalShelter.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                LatLng myLocation = new LatLng(myLat, myLng);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(11.2f));
            }
        }
    }

}