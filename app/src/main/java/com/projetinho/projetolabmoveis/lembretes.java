package com.projetinho.projetolabmoveis;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Lembretes extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    int id = 0;
    ArrayList<String> lembretes;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lembretes);
        Intent check = new Intent(this,CheckDist.class);
        startService(check);
        //-----------------------------------------------------------//
        dbManager = new DBManager(this);
        lembretes = dbManager.getLembretesStr();
        setSpinnerContent(lembretes);
        //quando clicar nesse botão tem que adicionar um novo objeto Lembrete
        ImageButton b = (ImageButton) findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            //esse metodo está modificando errado
            public void onClick(View v) {
                TextView t = (TextView) findViewById(R.id.lembrete);
                String local = ((Spinner)findViewById(R.id.locais)).getSelectedItem().toString();
                String texto = t.getText().toString();
                t.setText("");
                if (texto.length() > 0) {
                    TableLayout myLayout = (TableLayout) findViewById(R.id.lembretes);
                    generateLembreteLayout(texto,local, myLayout,id++);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //usado para preencher a lista de locais marcado pelo usuário
    //aqui deve ser feito algo para pegar os dados do sqlite e do maps para que possa preencher as opções
    private void setSpinnerContent(ArrayList<String> locaisString) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, locaisString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner s = (Spinner) findViewById(R.id.locais);
        s.setAdapter(adapter);
    }

    private void generateLembreteLayout(String lemb,String local, TableLayout gl,int id) {
        RelativeLayout r = (RelativeLayout)getLayoutInflater().inflate(R.layout.lembrete_layout,null,true);
        //layout que segura o botao e os textos
        View relative = r.getChildAt(0);
        TextView t = (TextView)((ViewGroup)relative).getChildAt(0);
        t.setText(lemb);
        TextView t2 = (TextView)((ViewGroup)relative).getChildAt(1);
        t2.setText(t2.getText() + local);
        gl.addView(r);

        /*for (String s : l.getLembretes())
        {
            t = new TextView(this);
            t.setText(s);
            t.setId(id);
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.cancelAll();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Lembretes Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.projetinho.projetolabmoveis/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Lembretes Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.projetinho.projetolabmoveis/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    public void toMap(View v){
        Intent it = new Intent(Lembretes.this, MapaLembretes.class);
        startActivity(it);
    }
}
