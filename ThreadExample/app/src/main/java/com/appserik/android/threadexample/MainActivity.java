package com.appserik.android.threadexample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate





    public void clickNoBotao( View view){


        //Esse objeto eh uma descricao de tarefa que pode ser rodada dentro de uma Thread.
        //Ou seja,  O tipo 'Runnable" serve para descrever o que vai ser executado e depois rodo isso dentro de uma thread que vou criar
        Runnable objetoRunnable = new Runnable(){


            @Override
            public void run(){

                //Adicioando 5 segundos (5 mil milisegundos) + o momento atual
                long futureTime = System.currentTimeMillis() + 5000;


                //Aguarda 10 segundos
                while ( System.currentTimeMillis() < futureTime ) {


                    //Sincronizar - Rodar ao paralelamente com a thread primaria
                    synchronized (this) {

                        try {
                            //Faz a execucao do pgm aguardar 5 segundos
                            wait(futureTime - System.currentTimeMillis());
                        }//try
                        catch (Exception e) {
                        }//catch

                    }//synchronized

                }//while


                //Chamo o objetoHandler do tip Handler para realizar acoes na thread primaria
                objetoHandler.sendEmptyMessage( 0 );


            }//run


        };//Runnable




        //Crio um Thread e passa a variavel do tipo Runnable que contem as instrucoes de execucao
        Thread threadSecundaria = new Thread( objetoRunnable );

        //Inicio a thread
        threadSecundaria.start();



    }//clickNoBotao





    //O Handler server para acessar os componentes da tela que estao sobe controle da Thread primaria
    //Ou seja, nao da pra mexer nas views da tela na thread secundaria, ai eu uso um objeto Handler para mexar nas view
    //--estando na thread secundaria
    Handler objetoHandler = new Handler(){

        @Override
        public void handleMessage( Message msg ){

            TextView textonaTela = (TextView) findViewById (R.id.textonaTela);

            textonaTela.setText( "Deu certo!, a execução do programa aguardou 5 segundos e depois alterou esse texto");

        }//handleMessage

    };//Handler




}//class
