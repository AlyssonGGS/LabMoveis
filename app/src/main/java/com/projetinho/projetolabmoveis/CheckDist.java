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
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

public class CheckDist extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient apiClient;
    Location myLastLocation;

    public void onCreate() {
        apiClient = new GoogleApiClient.Builder(this).
                addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }
    //acontece quando começa o serviço
    //quando adicionar um novo local este método deveria ser executado de nov

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
                    //fazer os testes de proximidades
                    //caso esteja proximo
                    //criar e exibir uma mensagem de proximidade com os lembretes cadastrados
                    if(myLastLocation != null)
                        showLocationMessage(myLastLocation.toString());
                }
            }
        });
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

    }

    public void showLocationMessage(String m)
    {
        final Context ctx = this;
        final String message = m;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx,message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
