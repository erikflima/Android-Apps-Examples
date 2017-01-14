package com.example.android.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Criando um objeto do tipo "Notification"
    NotificationCompat.Builder notification_Obj;

    //Criando um id unico para a notificacao
    private static final int uniqueID = 111;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando?
        notification_Obj = new NotificationCompat.Builder(this);

        //Apagar o icone de notificacao no canto superior da tela depois que o usuario ver.
        //Caso contrario o icone vai ficar la pra sempre :(
        notification_Obj.setAutoCancel(true);



    }

    //Metodo se o botao for clicado
    public void ntButtonClick( View view){


     //--Building the notitication / Criando as caracteristicas da notificacao---//

        //Escolhendo o icone da notificacao que aparece no canto superior da tela
        notification_Obj.setSmallIcon(R.drawable.nticon);

        //Escolhendo o som que vai tocar ao receber a notificacao, nesse caso sera o som padrao do sistema
        Uri tocarSom = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification_Obj.setSound(tocarSom);

        //Pegando a hora que vai ser exibida na notificacao
        notification_Obj.setWhen( System.currentTimeMillis() );

        //Texto que vai aparecer como titulo da notificacao
        notification_Obj.setContentTitle("Enjoy this new Notification :)" );

        //Texto do corpo da notificacao
        notification_Obj.setContentText("Checkout the ErikApps news!");





     //--Preparando um Intent para enviar a notificacao--//
     //---------------------------------------->( Onde estou, para que tela vou )
        Intent intentDaNotificacao = new Intent ( this, MainActivity.class);


        //Dando a aplicao acesso ao intent. Nao entendi muito bem essa linha
        //O que entendi, eh que PendingIntent eh um objeto que tem a acao de colocar na tela a notificacao
        PendingIntent pendingIntent = PendingIntent.getActivity( this, 0, intentDaNotificacao, PendingIntent.FLAG_UPDATE_CURRENT);


        //Colocando mais uma opcao que vai ficar abixo da descricao da notificao. Isso eh opcional
        notification_Obj.addAction( 0, "Load WebSite", pendingIntent);


        //Vinculando o intent a notificacao
        notification_Obj.setContentIntent(pendingIntent);




     //--Criando um obj NotificationManager, pois ele tem o metodo que executa a notificacao--//

        //Criando um objeto NotificationManager
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //Chamando o metoto que executa a notificacao
        //------>(id da notiticacao, o objeto notificaction que criei)
        nm.notify(uniqueID, notification_Obj.build());


    }//ntButtonClick



}
