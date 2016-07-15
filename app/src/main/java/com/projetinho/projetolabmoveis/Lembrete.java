package com.projetinho.projetolabmoveis;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Alysson on 29/06/2016.
 */
public class Lembrete {
    LatLng pos;
    private String local;//deve ser modificado para receber o objeto do mapa
    private ArrayList<String> lembretes;

    public Lembrete(String local,String latlng)
    {
        this.local = local;
        lembretes = new ArrayList<String>();
        String coord[] = latlng.split(",");
        pos = new LatLng(Double.parseDouble(coord[0]),Double.parseDouble(coord[1]));
    }

    public ArrayList<String> getLembretes()
    {
        return lembretes;
    }
    public String getLocalString()
    {
        return local;
    }
    public void addLembrete(String lembrete)
    {
         lembretes.add(lembrete);
    }
    public LatLng getLocation(){return pos;}
}
