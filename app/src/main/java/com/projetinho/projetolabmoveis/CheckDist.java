package com.projetinho.projetolabmoveis;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

public class CheckDist extends Service {
    public void onCreate()
    {
    }
    //acontece quando começa o serviço
    //quando adicionar um novo local este método deveria ser executado de nov

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            Toast t = Toast.makeText(getApplicationContext(),"hahahha",Toast.LENGTH_SHORT);
            //onde vai rodar o loop de teste
            @Override
            public void run() {
                //erro ao criar elementos gráficos dentro do run, dar uma olhada em algo parecido com invoke(hamilton)
                //Toast t = Toast.makeText(getApplicationContext(),"hahahha",Toast.LENGTH_SHORT);
                while(true){
                    try {
                        Thread.sleep(5000);
                    }catch (Exception e) {
                        stopSelf();
                    }
                    t.show();
                    //pegar informações do DB
                    //fazer os testes de proximidades
                    //caso esteja proximo
                    //criar e exibir uma mensagem de proximidade com os lembretes cadastrados
                }
            }
        }).start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
