package com.appserik.android.servicesexample;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class ExemploIntentService extends IntentService {

    private static final String TAG = "com.appserik.android.servicesexample";


    //Construtor obrigatorio quado uso IntentService
    public ExemploIntentService(){

        //Eh obrigatrio fazer isso - regras do Android
        super( "ExemploIntentService" );

    }//Construtor




    //Aqui eh descrito o que o IntentService ira fazer
    @SuppressLint("LongLogTag")
    @Override
    protected void onHandleIntent(Intent intent) {


        //Mensagem no log para saber se essa acao esta acontecendo em background
        Log.i( TAG, "Blz, a acao esta rodando em background");

        //Mostrar o resultado que o servidor devolveu na Thread principal
        Toast.makeText( getBaseContext(),  "Blz, a acao esta rodando em background", Toast.LENGTH_SHORT).show();

    }//onHandleIntent


}//class
