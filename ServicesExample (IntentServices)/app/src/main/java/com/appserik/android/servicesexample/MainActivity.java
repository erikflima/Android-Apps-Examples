package com.appserik.android.servicesexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Criando um objeto do IntentService da classe que criei
        Intent intentDoService = new Intent ( this, ExemploIntentService.class );

        //Inicia o IntentService que vai rodar em background
        startService( intentDoService );




        btn1 = (Button) findViewById(R.id.btn1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Tela2.class); //criando um objeto Intent e dizendo que faz referencia  a tela2
                startActivity(i);                                   //criar a tela
            }
        });


    }//onCreate

}//class
