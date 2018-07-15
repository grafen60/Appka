package com.malousek.appka.UI.MeteorDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.malousek.appka.Data.Classes.Meteorite;
import com.malousek.appka.R;
import com.malousek.appka.Utils.AppConstants;

/**
 * Created by Jan Malousek on 14.07.2018.
 *
 * Activity for displaying details about meteorite event
 */

public class MeteorDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Meteorite meteorite;
    private MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteor_detail);

        Intent intent = getIntent();
        meteorite = intent.getParcelableExtra(AppConstants.METEORITE_CLASS);

        initUI();
        initMap(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    private void initUI() {
        TextView meteoriteNameTextView = findViewById(R.id.meteoriteNameInMeteoriteDetails);
        TextView meteoriteDateTextView = findViewById(R.id.yearTextViewInMeteorDetails);
        TextView meteoriteMassTextView = findViewById(R.id.massTextViewInMeteorDetails);
        TextView meteoriteClassTextView = findViewById(R.id.classTextViewInMeteorDetails);

        meteoriteNameTextView.setText(meteorite.getName());
        meteoriteDateTextView.setText(meteorite.getYear().split("-")[0]);
        String mass;
        if(meteorite.getMass()<1000)
            mass = meteorite.getMass() + " g";
        else
            mass = meteorite.getMass()/1000 + " kg";

        meteoriteMassTextView.setText(mass);
        meteoriteClassTextView.setText(meteorite.getRecclass());
    }

    private void initMap(Bundle savedInstanceState) {
        mapView = findViewById(R.id.mapViewInMeteorDetail);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng meteoriteLandingArea = new LatLng(meteorite.getLat(), meteorite.getLng());
        mMap.addMarker(new MarkerOptions().position(meteoriteLandingArea));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(meteoriteLandingArea,2f));
    }


}
