package com.appserik.android.servicesexample;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MeuService extends Service {


    private static final String TAG = "com.appserik.android.servicesexample";


    //Construtor
    public MeuService() {}


    //Acao a ser realizada quando o servico comecar
    @SuppressLint("LongLogTag")
    @Override
    public int onStartCommand( Intent intent, int flags, int startId ) {



        //Mensagem no log para saber se essa acao esta acontecendo em background
        Log.i( TAG, "Chamou o onStartCommand() ou seja, uma acao esta sendo rodada no background");





        Runnable objRunnable = new Runnable(){
            @Override
            public void run() {

                for ( int i=0; i<5; i++){

                    long futureTime = System.currentTimeMillis() +5000;


                    while ( System.currentTimeMillis() < futureTime){

                        synchronized ( this ){}//synchronized

                        try{

                            wait( futureTime - System.currentTimeMillis() );

                            Log.i( TAG, "Rodando o servico em background");
                        }//try
                        catch( Exception e ){}//catch


                    }//while


                }//for


            }//run

        };




        Thread objThread = new Thread( objRunnable );

        objThread.start();


        //Se algo acontecer e o servico for destruido pelo metodo onDestroy(), esse comando faz a reinicializacao do servico
        return Service.START_STICKY;
    }





    @SuppressLint("LongLogTag")
    @Override
    public void onDestroy() {

        //Mensagem no log para saber se essa acao esta acontecendo em background
        Log.i( TAG, "Chamou o onDestroy()");


        super.onDestroy();

    }//onDestroy



    //Nao sei pra que serve esse metodo
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }//onBind




}//class
