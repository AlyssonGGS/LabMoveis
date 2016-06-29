package com.projetinho.projetolabmoveis;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Lembretes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembretes);
        
        //os dados do DB têm que preencher algo parecido com isso para gerar as string do adapter
        final ArrayList<String> locaisString = new ArrayList<String>();
        /////////////////////////////////////////////////////////////////////////////////////////
        locaisString.add("Pro");
        locaisString.add("Jetinho");
        setSpinnerContent(locaisString);

        //quando clicar nesse botão tem que adicionar um novo objeto Lembrete
        ImageButton b = (ImageButton) findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = (TextView)findViewById(R.id.lembrete);
                locaisString.add(t.getText().toString());
                setSpinnerContent(locaisString);
            }
        });
    }

    //usado para preencher a lista de locais marcado pelo usuário
    //aqui deve ser feito algo para pegar os dados do sqlite e do maps para que possa preencher as opções
    private void setSpinnerContent(ArrayList<String> locaisString)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, locaisString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner s = (Spinner)findViewById(R.id.locais);
        s.setAdapter(adapter);
    }
}
