package com.projetinho.projetolabmoveis;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telecom.Connection;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CheckDist extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient apiClient;
    Location myLastLocation;
    DBManager dbManager = new DBManager(this);
    ArrayList<Lembrete> lembretes;

    public void onCreate() {
        if(apiClient == null) {
            apiClient = new GoogleApiClient.Builder(this).
                    addConnectionCallbacks(this).
                    addOnConnectionFailedListener(this).
                    addApi(LocationServices.API).build();
        }
    }
    //acontece quando começa o serviço
    //quando adicionar um novo local este método deveria ser executado de nov

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        lembretes = dbManager.getLembretes();
        new Thread(new Runnable() {
            @Override
            public void run() {
                apiClient.connect();
                if ((ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                        (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                    return;
                }
                myLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                while(true){
                   try {
                        Thread.sleep(5000);
                    }catch (Exception e) {
                        stopSelf();
                        apiClient.disconnect();
                    }
                    //pegar informações do DB
                    lembretes = dbManager.getLembretes();
                    //fazer os testes de proximidades
                    //caso esteja proximo
                    //criar e exibir uma mensagem de proximidade com os lembretes cadastrados
                    if(myLastLocation != null) {
                        LatLng myLat = new LatLng(myLastLocation.getLatitude(),myLastLocation.getLongitude());
                        LatLng otherLat = new LatLng(myLastLocation.getLatitude(),myLastLocation.getLongitude());
                        showLocationMessage(String.valueOf(CalculationByDistance(myLat,otherLat)));
                        //showLocationMessage(myLastLocation.toString());
                    }
                    else
                    {
                        showLocationMessage("nao encontramos voce, gatao");
                    }
                }
            }
        }).start();
        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("erro",connectionResult.getErrorMessage());
    }

    public void showLocationMessage(String m)
    {
        final String message = m;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.d("hahaha","whiskas!");
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.d("erro", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }
}
