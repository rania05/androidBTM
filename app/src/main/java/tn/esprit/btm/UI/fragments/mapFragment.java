package tn.esprit.btm.UI.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import Entity.JsonParserr;
import tn.esprit.btm.R;

import static android.R.layout.simple_spinner_item;


public class mapFragment extends Fragment {
double currentLat =0, currentLong=0;
    Spinner spType;
    Button btnFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        //assign variable
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googlemap);
        spType = view.findViewById(R.id.sp_type);
        btnFind = view.findViewById(R.id.bt_find);
        // array
        String[] placeTypeList = {"bus", "train", "metro"};
        String[] placeNameList = {"bus1", "train2", "metro3"};
        spType.setAdapter(new ArrayAdapter<String>(getActivity(), simple_spinner_item, placeNameList));
        // Initialize Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
// check permission
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // when permission granted
            //call method
            getCurrentLocation();
        }
        else
        {
            //Request permissin
            ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);

        }
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i =spType.getSelectedItemPosition();
                String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json"+"?location="+currentLat+","+currentLong
                        +"&radius=5000"+"&types="+placeTypeList[i]+"&sensor=true"+"&key"+getResources().getString(R.string.google_maps_key);
                new PlaceTask().execute(url);

            }
        });



        return view;
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                                          @Override
                                          public void onSuccess(Location location) {
                                              if(location!=null)
                                              {
                                               // get current latitiude
                                                  currentLat = location.getLatitude();
                                                  currentLong = location.getLongitude();//sync map
                                                  supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                                      @Override
                                                      public void onMapReady(GoogleMap googleMap) {
                                                          // when map is loaded
                                                          googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                                              @Override
                                                              public void onMapClick(LatLng latLng) {
                                                                  //when clicked on map
                                                                  // Initialaze marker option
                                                                  map=googleMap;
                                                                 MarkerOptions markerOptions = new MarkerOptions();
                                                                  markerOptions.position(new LatLng(currentLat,currentLong));
                                                                 // markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                                                                  // remove  all marker
                                                                  googleMap.clear();
                                                                  //animatigin to zoom marker
                                                                  googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                                         new LatLng(currentLat,currentLong), 10
                                                                  ));
                                                                  googleMap.addMarker(markerOptions);
                                                              }
                                                          });
                                                      }
                                                  });

                                              }
                                          }
                                      });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                // initialize data
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            // execute parser task
          //  new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string)  throws IOException {
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line ="";
        while ((line = reader.readLine()) != null)
        {
            builder.append(line);
        }
        String data =builder.toString();
        reader.close();
        return data;
    }

   /* private class ParserTask  extends AsyncTask <String,Integer, List<HashMap<String,String>> {


        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            //create json parser class
            JsonParserr
            return null;
        }
    }*/
}