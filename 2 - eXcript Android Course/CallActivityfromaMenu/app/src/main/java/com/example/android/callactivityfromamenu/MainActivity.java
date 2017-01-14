package com.example.android.callactivityfromamenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        //Se o item clicado for o opn1
        if (id == R.id.opn1){


            Intent i = new Intent(this, Tela2.class); //criando um objeto Intent e dizendo que faz referencia  a tela2
            startActivity(i);                         //criar a tela

        }//if

        return true;
    }//onOptionsItemSelected

}
