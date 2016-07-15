package com.projetinho.projetolabmoveis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapaLembretes extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean canAddMarker = false;
    DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_lembretes);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)||(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED))
            mMap.setMyLocationEnabled(true);
        ArrayList<String> marcadores = dbManager.getLembretesStr();
        if(marcadores != null) {
            for (String linha : marcadores) {
                String[] latLng = linha.split(",");
                MarkerOptions marker = new MarkerOptions();
                marker.position(new LatLng(Double.valueOf(latLng[0]), Double.valueOf(latLng[1])));
                mMap.addMarker(marker);
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-22.9065047,-43.1331306)));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(canAddMarker) {
                    // Creating a marker
                    MarkerOptions marker = new MarkerOptions();
                    marker.position(latLng);
                    mMap.addMarker(marker);
                    dbManager.addLembretes(latLng.latitude, latLng.longitude);

                    //Setting the button visible
                    Button b = (Button)findViewById(R.id.addMarkerButton);
                    b.setVisibility(View.VISIBLE);

                    canAddMarker = false;
                    Intent it = new Intent(MapaLembretes.this, ListaLembretes.class);
                    startActivity(it);
                }
            }
        });
    }

    public void addMarker(View v){
        //Setting the button invisible
        Button b = (Button)findViewById(R.id.addMarkerButton);
        b.setVisibility(View.INVISIBLE);
        canAddMarker = true;
    }
}
