package com.appserik.android.leitorderss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {



    //Criando um objeto RecyclerView
    RecyclerView recyclerview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerview = (RecyclerView) findViewById( R.id.recyclerview );




        //Criando um obj da classe 'ReadRss' que criei, e passo o 'this' como parametro do construtor
        ReadRss readRss = new ReadRss ( this, recyclerview );



        //Executa ( onPreExecute(), depois doInBackground() e por fim onPostExecute()  )
        readRss.execute();



    }//onCreate




}//class
