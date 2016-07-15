package com.projetinho.projetolabmoveis;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Alysson on 15/07/2016.
 */
public class Local {
    int id;
    LatLng pos;
    public Local(int id, String latlng)
    {
        String coord[] = latlng.split(",");
        pos = new LatLng(Double.parseDouble(coord[0]),Double.parseDouble(coord[1]));
    }

}
