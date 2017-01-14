package com.appserik.android.servicesexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Tela2 extends AppCompatActivity {

    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2);



        Intent i = new Intent( this,MeuService.class );
        startService( i );





        btn2 = (Button) findViewById(R.id.btn2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(v.getContext(), MainActivity.class); //criando um objeto Intent e dizendo que faz referencia  a tela1
                startActivity(i2);                                          //criar a tela


            }
        });

    }//onCreate


}//class
