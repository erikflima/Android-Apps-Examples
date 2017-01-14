package com.appserik.android.sendbroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }//onCreate




    public void sendOutBroadcast( View view){

        //Criando o intent
        Intent i = new Intent();

        //Enviar o intent nao para uma outra tela, mas sim para o sistema do android.
        //Outras aplicacoes instaladas no celular podem receber esse intent
        i.setAction( "com.appserik.android.sendbroadcast" );

        //Faz com que o intent seja compativel com outras versoes do android
        i.addFlags( Intent.FLAG_INCLUDE_STOPPED_PACKAGES );

        //Enviar o intent
        sendBroadcast(i);


    }//sendOutBroadcast






}//clas
