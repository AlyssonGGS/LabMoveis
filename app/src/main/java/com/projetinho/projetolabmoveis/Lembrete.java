package com.projetinho.projetolabmoveis;

import java.util.ArrayList;

/**
 * Created by Alysson on 29/06/2016.
 */
public class Lembrete {
    public Lembrete(String local)
    {
        this.local = local;
        lembretes = new ArrayList<String>();
    }
    private String local;//deve ser modificado para receber o objeto do mapa
    private ArrayList<String> lembretes;

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
}
