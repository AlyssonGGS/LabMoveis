package com.projetinho.projetolabmoveis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaLembretes extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lembretes);
        ArrayList<String> lembretes = null;
        DBManager dbManager = new DBManager(this);
        lembretes = dbManager.getLembretesStr();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lembretes);
        ListView listView = (ListView)findViewById(R.id.listViewLembretes);
        listView.setAdapter(adapter);

    }

}
