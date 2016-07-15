package com.projetinho.projetolabmoveis;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Alysson on 29/06/2016.
 */
public class Lembrete {
    Local local;
    private ArrayList<String> lembretes;

    public Lembrete(Local l)
    {
        this.local = l;
        lembretes = new ArrayList<String>();
    }

    public ArrayList<String> getLembretes()
    {
        return lembretes;
    }
    public String getLocalString()
    {
        return local.toString();
    }
    public void addLembrete(String lembrete)
    {
         lembretes.add(lembrete);
    }
    public Local getLocation(){return local;}
}
