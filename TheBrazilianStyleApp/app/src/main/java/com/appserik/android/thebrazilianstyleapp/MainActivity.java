package com.appserik.android.thebrazilianstyleapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Criando um objeto webView
    private WebView webView_Obj;

    //Criado um objeto ProgressBar
    private ProgressBar barraDeProgresso;

    //Criado um objeto ProgressBar
    private ImageView img_sem_conexao;


    //Links do site
    private String urlSite      = "http://thebrazilianstyle.com/";
    private String urlPosts     = "http://thebrazilianstyle.com/the-brazilian-style";
    private String urlContact   = "http://thebrazilianstyle.com/contact/";
    private String urlAbout     = "http://thebrazilianstyle.com/about/";
    private String urlFacebook  = "https://www.facebook.com/thebrazilianstyle";
    private String urlInstagram = "https://www.instagram.com/kelvinlopes123/";
    private String urlYoutube   = "https://www.youtube.com/channel/UCavRNi95C44Enj5Pw7-KVyw/";
    private String urlTwitter   = "https://twitter.com/kelvinlopes123/";


    //String auxiliar
    private String urlAtual = urlPosts;


    //Strings
    private String message_to_share         = "#TheBrazilianStyle is a menswear fashion blog designed to influence and inspire men with real style. \n\nCheck it out the blog: http://thebrazilianstyle.com/ \n\nSee you there!";
    private String opc_txt_menu_share       = "Share The Brazilian Style Website";
    private String txt_after_share          = "Thanks for sharing!";
    private String kelvin_email             = "kelvinlopes@thebrazilianstyle.com";
    private String subject_email            = "Hello Kelvin Lopes";
    private String msg_internet_conection   = "Checking your internet connection";


    //FloatButton
    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu_lateral);



        //Inserir barra superior na tela
        Toolbar barra_superior = (Toolbar) findViewById(R.id.id_barra_superior);
        setSupportActionBar(barra_superior);

        //--------------------------------------------------------------------------------------------//


        //FloatButton - Estou usando para atualizar webView
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlAtual);

                Toast.makeText( MainActivity.this, msg_internet_conection, Toast.LENGTH_LONG ).show();

            }
        });

        //--------------------------------------------------------------------------------------------//


        //Vinculando o obj
        webView_Obj = (WebView) findViewById(R.id.id_webView);

        //Setting the webView
        WebSettings webSettings_obj = webView_Obj.getSettings();

        //Se a pagina aberta dentro do WebView tiver javascript, entao sera autorizado o uso
        webSettings_obj.setJavaScriptEnabled(true);



        //erik
        //Linkando a progressBar na tela
        barraDeProgresso = (ProgressBar) findViewById(R.id.login_progress);
        barraDeProgresso.setMax(100);


        //Esse comando eh para evitar que ao clicar em algum item do site que foi aberto
        // na VebView, ele continue dentro do app e não abra o site no browser.
        //Sem esse comando, qualqer coisa do site vai ser aberta diretamente no browser
        webView_Obj.setWebViewClient(new WebViewClient() );



        //Permitir executar video dentro da webView
        // Eh preciso colocar esse esse texto no android manifest para funcionar " android:hardwareAccelerated="true" "
        webView_Obj.setWebChromeClient(new WebChromeClient());





        //Permitir executar video dentro da webView
        // Eh preciso colocar esse esse texto no android manifest para funcionar " android:hardwareAccelerated="true" "
        webView_Obj.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);


                //Deixa a barra de progresso visivel
                barraDeProgresso.setVisibility(ProgressBar.VISIBLE);


                //Deixa a webView Invisivel
                webView_Obj.setVisibility(View.INVISIBLE);


                //Deixa a imagem que fala que nao ha conexao com a internet invisivel
                img_sem_conexao.setVisibility(ImageView.INVISIBLE);


                //Deixa o floatButton invisivel
                fab.setVisibility(View.INVISIBLE);



                //Se acabar o carregamento dentro da webView
                if (newProgress == 100){

                    //Verifica a conexao com a net
                    verificarConexao();


                    //Se nao acabar o carregamento da webView
                }else{
                    barraDeProgresso.setProgress(newProgress);



                }//else

            }


        });




        webSettings_obj.setJavaScriptCanOpenWindowsAutomatically(true);



        //Carregar a pagina mais rapido
        webView_Obj.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView_Obj.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView_Obj.getSettings().setAppCacheEnabled(true);
        webView_Obj.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings_obj.setDomStorageEnabled(true);
        webSettings_obj.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings_obj.setUseWideViewPort(true);

        //webSettings_obj.getSavePassword(true);
        webSettings_obj.setSaveFormData(true);

        //Verifica se tem conecxao com a internet. Se nao tiver, exibe uma imagem na tela
        verificarConexao();

        //Dizendo a url a ser utilizada
        webView_Obj.loadUrl(urlPosts);

        //--------------------------------------------------------------------------------------------//


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, barra_superior, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }//onCreate




    //Funcoes dos botoes do menu lateral
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.id_ic_home:


                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlSite;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlSite);
                break;

            case R.id.id_ic_posts:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlPosts;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlPosts);
                break;


            case R.id.id_about:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlAbout;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlAbout);
                break;



            case R.id.id_ic_contact:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlContact;


                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlContact);
                break;


            case R.id.id_ic_facebook:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlFacebook;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlFacebook);
                break;



            case R.id.id_ic_instagram:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlInstagram;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlInstagram);
                break;




            case R.id.id_youtube:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlYoutube;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlYoutube);
                break;



            case R.id.id_ic_twitter:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlTwitter;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlTwitter);
                break;


            case R.id.nav_share:


                Intent sharingIntent = new Intent(Intent.ACTION_SEND);         //Crio um intent (Intent eh uma mensagem que pede uma acao de outro app ou recurso do android)
                sharingIntent.setType("text/plain");                           //Digo que o intent sera um texto simples
                String stringToShare= message_to_share;                        //Defino o texto que sera compartilhado
                sharingIntent.putExtra(Intent.EXTRA_TEXT, stringToShare);      //Adicono o texto que sera compartilhado

                startActivity(Intent.createChooser(sharingIntent, opc_txt_menu_share)); //Texto que vai aparecer no menu de opcoes a compartilhar
                Toast.makeText(this, txt_after_share, Toast.LENGTH_LONG).show();
                break;


            case R.id.nav_send:

                //Preparando conteudo para ser enviado por e-mail
                Intent intent = new Intent(Intent.ACTION_SENDTO); //Cria um objeto do tipo intent
                intent.setData(Uri.parse("mailto:"+kelvin_email)); //only email aps should handle this

                //Deixando o e-mail ja digitado
                intent.putExtra(intent.EXTRA_SUBJECT, subject_email); //Deixando um subject ja digitado


                if(  intent.resolveActivity(getPackageManager() ) != null)
                {   //se encontrar uma activity para enviar o e-mail faca...
                    startActivity(intent); //Chama o  intent
                }

                break;


            case R.id.id_opc_developer:

                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaDev = new Intent(MainActivity.this, DeveloperScreen.class);
                startActivity(IntentVaiParaTelaDev);


                break;


        }//switch





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//onNavigationItemSelected





    //Funcao tecla voltar do aparelho
    @Override
    public void onBackPressed() {


        //---Faz com que o usuario possa voltar paginas usando o botao 'back' do smartphone--//
        if ( webView_Obj.canGoBack() ){

            //Voltar 1 pagina web dentro do WebView
            webView_Obj.canGoBack();

        }



        //---Se o usuario abrir o menu lateral, ele podera fechar o menu com a tecla 'back" do smartphone--//
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }//onBackPressed




    //Verifica se o smartPhone esta conectado na internet
    public void verificarConexao() {

        img_sem_conexao = (ImageView) findViewById(R.id.id_img_sem_net);

        ConnectivityManager cManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo         nInfo    =  cManager.getActiveNetworkInfo();

        //Se tiver conexao com a internet
        if (nInfo != null && nInfo.isConnected() )
        {

            //Deixa a barra de progresso visivel
            barraDeProgresso.setVisibility(ProgressBar.INVISIBLE);

            //Deixa a imagem que fala que nao ha conexao com a internet invisivel
            img_sem_conexao.setVisibility(ImageView.INVISIBLE);

            //Deixa o floatButton invisivel
            fab.setVisibility(View.INVISIBLE);


            //Deixa a webView Visivel
            webView_Obj.setVisibility(View.VISIBLE);

        }


        //Se nao tiver conexao com a net
        else
        {
            //Deixa a barra de progresso visivel
            barraDeProgresso.setVisibility(ProgressBar.INVISIBLE);

            //Deixa a webView Visivel
            webView_Obj.setVisibility(View.INVISIBLE);

            //Deixa a imagem que fala que nao ha conexao com a internet invisivel
            img_sem_conexao.setVisibility(ImageView.VISIBLE);

            //Deixa o floatButton invisivel
            fab.setVisibility(View.VISIBLE);



            //Toast.makeText( this, "Você esta sem conexão com a Internet", Toast.LENGTH_SHORT ).show();
        }


    }//verificarConexao




    //--------------------------------------------------------------------------------------------//

    //Implementei esses metodos para controlar a webView.
    @Override
    protected void onPause() {
        webView_Obj.onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        webView_Obj.onResume();
        super.onResume();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        webView_Obj.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        webView_Obj.destroy();
        webView_Obj = null;
        super.onDestroy();

    }



}//MainActivity

