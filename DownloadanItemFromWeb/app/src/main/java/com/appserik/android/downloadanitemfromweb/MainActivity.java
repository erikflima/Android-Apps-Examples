package com.appserik.android.downloadanitemfromweb;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {



    String myHTTPUrl = "http://www.coxandkingsusa.com/resources/images/countries/japan.jpg";

    String myHTTPUrl2 = "http://media.blubrry.com/onomedissoemundo/p/www.onomedissoemundo.com/podcasts/OSDM-ep-013.mp3";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnTrad;
        Button bntDM;

        btnTrad = (Button) findViewById( R.id.bntTradicional);
        bntDM   = (Button) findViewById( R.id.btnDM         );




        //
        btnTrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {


                new MyTask().execute();



            }//onClick
        });



        
        bntDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {




                DownloadManager.Request request = new DownloadManager.Request(Uri.parse ( myHTTPUrl2 ) );


                request.setTitle("Download de arquivo");


                request.setDescription("Baixando o arquivo...");

                //Faz que o arquivo seja baixado apenas por usando WIFI
                //request.setAllowedNetworkTypes( DownloadManager,Request.NETWORK_WIFI );

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility( DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);



            //Definindo o nome do arquivo a ser salvo com o nome original
                String nameOfFile = URLUtil.guessFileName ( myHTTPUrl2, null, MimeTypeMap.getFileExtensionFromUrl(myHTTPUrl2)  );


                request.setDestinationInExternalPublicDir( Environment.DIRECTORY_DOWNLOADS, nameOfFile );

                DownloadManager manager = (DownloadManager) getBaseContext().getSystemService( Context.DOWNLOAD_SERVICE );
                manager.enqueue( request );



                Toast.makeText( getBaseContext() ,  "Passou pelas instrucoes do download manager", Toast.LENGTH_LONG).show();

            }//onClick
        });



    }//onCreate






    //Classe para realizar a conexao com a net
    public class MyTask extends AsyncTask< Void, Void, Void> {






        ////Acoes que vao ser feitas na thread secundaria
        @Override
        protected Void doInBackground(Void... params) {


            try {

                //--ETAPA DE FAZER A CONEXAO

                //Criando a url do arquivo que vou baixar
                URL myurl = new URL( myHTTPUrl );


                //Criado uma variavel de conexao com a net
                HttpURLConnection connection = ( HttpURLConnection ) myurl.openConnection();


                //Digo que "sim" vou pegar a saida
                connection.setDoOutput(true);


                //Vou usar o metodo GET para pegar os dados da net
                connection.setRequestMethod("GET");


                //Faz a conexao
                connection.connect();



                //--ETAPA DE SALVAR O ARQUIVO

                //Definindo onde o arquivo baixado vai ser salvo
                //-------------------------------------------------------------------------->( pasta do android, nome da subpasta )
                File rootDirectory = new File ( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES), "My Pictures" );


                //Verificando se a pasta que vou salvar o arquivo existe
                if ( !rootDirectory.exists() ){

                    //Se a pasta nao existir, entao cria a pasta
                    rootDirectory.mkdirs();

                }//if


                //Definindo o nome do arquivo a ser salvo manualmente
                //String nameOfFile = "nome do arquivo";

                //Definindo o nome do arquivo a ser salvo com o nome original
                String nameOfFile = URLUtil.guessFileName ( myHTTPUrl, null, MimeTypeMap.getFileExtensionFromUrl(myHTTPUrl)  );


                //Criando o arquivo e definindo o local onde sera salvo, e o nome do arquivo
                File file = new File( rootDirectory, nameOfFile );


                //Criando
                file.createNewFile();


                //Pega o
                InputStream inputStream = connection.getInputStream();

                FileOutputStream output = new FileOutputStream( file );


                //Verificando se os dados recebidos estao corretos. NAO ENTENDI PRA QUE ISSO
                byte[] buffer = new byte[1024];
                int byteCount = 0;

                while(  (byteCount = inputStream.read(buffer) ) > 0   ){

                    output.write( buffer, 0, byteCount);

                }//while


                //Fechar o output
                output.close();



                Intent intent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData( Uri.fromFile(file) );
                getBaseContext().sendBroadcast(intent);





            }//try
            catch (MalformedURLException e) {

                e.printStackTrace();
                Log.v("MainActivity","Erro de MalformedURLException: " + e);

            }//catch-1

            catch (IOException e) {
                e.printStackTrace();
                Log.v("MainActivity","Erro de IOException: " + e);
            }//catch-2



            return null;
        }//doInBackground




        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            //Mostrar o resultado que o servidor devolveu na Thread principal
            Toast.makeText( getBaseContext() ,  "Download tradicional Finalizado com sucesso!", Toast.LENGTH_LONG).show();

        }//onPostExecute





    }//MyTask






}//class
