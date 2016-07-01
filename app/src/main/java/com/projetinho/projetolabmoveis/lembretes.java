package com.projetinho.projetolabmoveis;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Lembretes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lembretes);
        //-----------------------------------------------------------//
        ArrayList<Lembrete> lembretes = new ArrayList<Lembrete>();
        lembretes.add(new Lembrete("casa"));
        lembretes.get(0).addLembrete("Ola");
        lembretes.get(0).addLembrete("Ola2");
        lembretes.get(0).addLembrete("Ola3");
        //----------------------------------------------------------//

        //RelativeLayout myLayout = (RelativeLayout)findViewById(R.id.exibeLembretes);
        //generateLembreteLayout(lembretes.get(0),myLayout);
        //os dados do DB têm que preencher algo parecido com isso para gerar as string do adapter
        final ArrayList<String> locaisString = new ArrayList<String>();
        /////////////////////////////////////////////////////////////////////////////////////////
        setSpinnerContent(locaisString);
        //quando clicar nesse botão tem que adicionar um novo objeto Lembrete
        ImageButton b = (ImageButton) findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            //esse metodo está modificando errado
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

    private void generateLembreteLayout(Lembrete l,RelativeLayout rl)
    {
        TextView t;
        int i = 0;
        for(String s : l.getLembretes()) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            t = new TextView(this);
            t.setText(s);
            t.setId(i);
            i++;
            //ll.addView(t, lp);
        }
    }
}
