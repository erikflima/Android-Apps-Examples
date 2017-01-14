package com.example.android.webviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Criando um objeto webView
    private WebView webView_Obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vinculando o obj
        webView_Obj = (WebView) findViewById(R.id.webView_erik);

        //Setting the webView
        WebSettings webSettings_obj = webView_Obj.getSettings();

        //Se a pagina aberta dentro do WebView tiver javascript, entao sera autorizado o uso
        webSettings_obj.setJavaScriptEnabled(true);

        //Dizendo a url a ser utilizada
        webView_Obj.loadUrl("http://www.google.com");


        //Esse comando eh para evitar que ao clicar em algum item do site que foi aberto
        // na VebView, ele continue dentro do app e não abra o site no browser.
        //Sem esse comando, qualqer coisa do site vai ser aberta diretamente no browser
        webView_Obj.setWebViewClient(new WebViewClient() );

    }//onCreate



    @Override
    //Metodo da tecla "Back" do aparelho
    //Quando clicada, o metodo vai verificar se da ou nao para voltar a pagina anterior dentro da webview
    // se tiver pagina para voltar, vai voltar 1 pagina dentro do app.
    // se nao, vai sair do app.

    public void onBackPressed() {

        //Se tiver alguma pagina para voltar dentro do WebView
        if ( webView_Obj.canGoBack() ){
            Toast.makeText(MainActivity.this, "Tem algo pra voltar", Toast.LENGTH_LONG).show();


            //Voltar 1 pagina web dentro do WebView
            webView_Obj.canGoBack();
        }
        else
            {
                Toast.makeText(MainActivity.this, "nao tem nada pra voltar", Toast.LENGTH_LONG).show();
                //Sair do app
                super.onBackPressed();
            }


    }//onBackPressed




}//MainActivity

