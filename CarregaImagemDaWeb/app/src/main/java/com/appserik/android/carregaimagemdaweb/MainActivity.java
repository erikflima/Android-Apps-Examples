package com.appserik.android.carregaimagemdaweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {


    //Esse objeto vai ser usado para acessar a thread primaria mesmo estando na thread secundaria
    //O tipo Handler permite realizar alteracoes na tela, mesmo estando em um thread secundaria
    private Handler handlerDaThreadPrimaria = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate



    public void loadImg(View view){

        //Criando um thread secundaria para fazer uma conexao a net
        new Thread(){
            public void run(){




                //A imagem da web vem em formato Bitmap
                Bitmap imgPegaDaWeb = null;

                try{

                    //URL da imagem a ser carregada
                    URL url = new URL ("http://wordpress.tokyotimes.org/archives/vuitton_japan03.jpg");


                    //Criando um objeto para realizar a conexao com a web
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();


                    //Guardando o conteudo recebido na conexao
                    InputStream input = conexao.getInputStream();


                    //Decodificando a imagem recebida da web
                    imgPegaDaWeb = BitmapFactory.decodeStream( input );


                }//try
                catch( IOException e ){

                    Log.v("MainActivity","Erro de IOException: " + e);
                }//catch


                //Vou precisar passar a imagem para dentro da subclasse abaixo
                //Por isso, tive que criar essa variavel auxiliar do com o final, pois o java
                //nao permite passar a variavel da imagem diretamente para uma innerclass
                final Bitmap imgAux = imgPegaDaWeb;



                //Acessando a thread principal e passando a imagem para o imageView que esta na tela
                handlerDaThreadPrimaria.post( new Runnable() {


                    //entrando na thread principal de fato
                    public void run(){


                        //Criando uma webView e colocando na tela. Lembrado que poderia ter criado a ImageView ja no xml e ter feito um findViewById bem aqui.
                        //Importante--------------------------------->( Esse aqui eh o contexto que a imageView vai ser inserida, o comando getBaseContext() pega o contexto certo )
                        ImageView imageViewQvaiPraTela = new ImageView( getBaseContext() );


                        //Passando a imagem para o imageView na tela
                        imageViewQvaiPraTela.setImageBitmap( imgAux  );


                        //Linkando com o layout que esta tela
                        LinearLayout layoutNaTela = (LinearLayout) findViewById (R.id.layoutDaTela);


                        //Adicionado o ImageView que fiz no layout da tela
                        layoutNaTela.addView( imageViewQvaiPraTela );

                    }//run 2

                } );//handler


            }//run 1

        }///Thread
                 //Rodar o Runnable
                .start();






    }//loadImg



}//class
