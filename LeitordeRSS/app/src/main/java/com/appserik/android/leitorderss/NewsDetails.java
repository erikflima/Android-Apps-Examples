package com.appserik.android.leitorderss;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;




public class NewsDetails extends AppCompatActivity {

    WebView webView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);



        webView       = (WebView) findViewById( R.id.webviewDaTela );


        //Pega o link que veio do anexo do Intent que veio para essa tela
        Bundle bundle = getIntent().getExtras();
        webView.loadUrl( bundle.getString( "Link") );

    }//onCreate


}//class
