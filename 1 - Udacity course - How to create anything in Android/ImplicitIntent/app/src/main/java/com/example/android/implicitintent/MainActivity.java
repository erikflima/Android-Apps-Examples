package com.example.android.implicitintent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override // Inflate the menu; this adds items to the action bar if it is present.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override//Metodo executado toda vez que o usuario selecionar uma opcao na barra de menu supeior
    public boolean onOptionsItemSelected(MenuItem item) { //Recebo o item da opção escolhida

        //Pego o id do item que foi clicado
        int id = item.getItemId();

        //Se o item clicado for a opacao "share"
        if (id == R.id.implicit_share){


            Intent sharingIntent = new Intent(Intent.ACTION_SEND);         //Crio um intent (Intent eh uma mensagem que pede uma acao de outro app ou recurso do android)
            sharingIntent.setType("text/plain");                           //Digo que o intent sera um texto simples
            String stringToShare= "Exemplo de texto que sera comparlhado"; //Defino o texto que sera compartilhado
            sharingIntent.putExtra(Intent.EXTRA_TEXT, stringToShare);      //Adicono o texto que sera compartilhado
            startActivity(Intent.createChooser(sharingIntent, "Share pela forma que criei")); //Texto que vai aparecer no menu de opcoes a compartilhar

        }//if

        return true;
    }//onOptionsItemSelected



}
