package com.appserik.android.leitorderss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Criando um obj da classe 'ReadRss' que criei, e passo o 'this' como parametro do construtor
        ReadRss readRss = new ReadRss ( this );


        //Executa ( onPreExecute(), depois doInBackground() e por fim onPostExecute()  )
        readRss.execute();



    }//onCreate




}//class
