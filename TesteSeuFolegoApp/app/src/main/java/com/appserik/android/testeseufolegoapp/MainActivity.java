package com.appserik.android.testeseufolegoapp;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Criando um objeto webView
    private WebView webView_Obj;

    //Criado um objeto ProgressBar
    private ProgressBar barraDeProgresso;

   //Criado um objeto ProgressBar
    private ImageView img_sem_conexao;

    //FloatButton
    FloatingActionButton fab;



    //Links do site
    private String urlBlog      = "http://testeseufolego.blogspot.com.br";
    private String urlFacebook  = "https://www.facebook.com/TesteSeuFolego";
    private String urlInstagram = "https://www.instagram.com/testeseufolego/";
    private String urlYoutube   = "https://www.youtube.com/channel/UCrerFp1ujLi5bcjrQwRIxDA";
    private String urlInscricao = "https://docs.google.com/forms/d/1rQnntyLj-3Xvq5UGO9XOm-NBv6vC2S_a-x9LIiGK7Wo/viewform#start=openform";


    //String auxiliar
    private String urlAtual          = urlBlog;
    private String urlMsgFloatButton = "Verificando conexao com a Internet\n\t\t\tAtualizado a pagina";

    //Strings Endereco do Evento
    private String enderecoEvento = "Avenida Redenção, 271 - Jardim do Mar, São Bernardo do Campo - SP, 09725-680, Brasil";


    //Strings
    private String opc_txt_menu_share = "Divulgue o TSF com seus amigos";
    private String txt_after_share    = "Obrigado por compartilhar!";
    private String tsf_email          = "projetounionbreak@hotmail.com";
    private String subject_email      = "Teste Seu Folego - 'Digite aqui o assunto' ";
    private String message_to_share   = "#Teste seu fôlego é um evento realizado pela produtora Union Break e " +
                                        "tem como proposta realizar campeonatos de break juntando todos elementos " +
                                        "do Hip Hop, propondo elevar o nível de breaking no Brasil.\n\n" +
                                        "\nhttps://www.testeseufolego.blogspot.com.br" +
                                        "\nFacebook: https://www.facebook.com/TesteSeuFolego" +
                                        "\nE-mail: projetounionbreak@hotmail.com" +
                                        "\nInstagram: https://www.instagram.com/testeseufolego/";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView( R.layout.tela_menu_lateral );



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

                Toast.makeText( MainActivity.this, urlMsgFloatButton, Toast.LENGTH_SHORT ).show();

            }
        });

        //--------------------------------------------------------------------------------------------//


        // Load an ad into the AdMob banner view. / Colocando o anuncio do admob
        AdView adView    = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        //--------------------------------------------------------------------------------------------//





        //Vinculando o obj
        webView_Obj = (WebView) findViewById(R.id.id_webView);

        //Setting the webView
        WebSettings webSettings_obj = webView_Obj.getSettings();

        //Se a pagina aberta dentro do WebView tiver javascript, entao sera autorizado o uso
        webSettings_obj.setJavaScriptEnabled(true);




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
        //Toda vez que uma url for carrega, esse metodo tambem eh chamado
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
        webView_Obj.loadUrl(urlBlog);


        //----------------------------------------------------------------------------------------//


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, barra_superior, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }//onCreate



    //--------------------------------------------------------------------------------------------//


    //Funcoes dos botoes do menu lateral
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()){



            case R.id.id_ic_tsf:

            //Passando  a url para usar no floatbutton se precisar atualizar a pagina
            urlAtual = urlBlog;

            //Dizendo a url a ser utilizada
            webView_Obj.loadUrl(urlBlog);
            break;



            case R.id.id_ic_atracao:

                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaDeAtra = new Intent(MainActivity.this, TelaAtracaoPrincipal.class);
                startActivity(IntentVaiParaTelaDeAtra);
                break;


            case R.id.id_djs:

                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaDeDjs = new Intent(MainActivity.this, TelaDjs.class);
                startActivity(IntentVaiParaTelaDeDjs);
                break;



            case R.id.id_ic_jurados:

                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaDeJurados = new Intent(MainActivity.this, TelaJurados.class);
                startActivity(IntentVaiParaTelaDeJurados);
                break;




            case R.id.id_ic_patrocinadores:
                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaPatrocinio = new Intent(MainActivity.this, TelaPatrocinio.class);
                startActivity(IntentVaiParaTelaPatrocinio);
                break;





            case R.id.id_ic_compartilhar:

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);         //Crio um intent (Intent eh uma mensagem que pede uma acao de outro app ou recurso do android)
                sharingIntent.setType("text/plain");                           //Digo que o intent sera um texto simples
                String stringToShare= message_to_share;                        //Defino o texto que sera compartilhado
                sharingIntent.putExtra(Intent.EXTRA_TEXT, stringToShare);      //Adicono o texto que sera compartilhado

                startActivity(Intent.createChooser(sharingIntent, opc_txt_menu_share)); //Texto que vai aparecer no menu de opcoes a compartilhar
                Toast.makeText(this, txt_after_share, Toast.LENGTH_LONG).show();
                break;




            case R.id.id_ic_contato:

                //Preparando conteudo para ser enviado por e-mail
                Intent intent = new Intent(Intent.ACTION_SENDTO); //Cria um objeto do tipo intent
                intent.setData(Uri.parse("mailto:"+tsf_email)); //only email aps should handle this

                //Deixando o e-mail ja digitado
                intent.putExtra(intent.EXTRA_SUBJECT, subject_email); //Deixando um subject ja digitado


                if(  intent.resolveActivity(getPackageManager() ) != null)
                {   //se encontrar uma activity para enviar o e-mail faca...
                    startActivity(intent); //Chama o  intent
                }

                break;


            case R.id.id_ic_localData:
                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaLocalData = new Intent(MainActivity.this, TelaEndereco.class);
                startActivity(IntentVaiParaTelaLocalData);
                break;



            case R.id.id_ic_chegar:

                //"Visualizar Endereço no Map
                Intent   intentVisualizarEnderecoNoMapa  = new Intent( Intent.ACTION_VIEW  );

                //Digo qual recurso quero utilizar. Nesse caso o google Maps
                //O comando ".setData" serve para passar um parametro dentro da Intent
                //-------------------->( Objeto do tipo identificador de recurso, protocolo de geoLocalização)
                intentVisualizarEnderecoNoMapa.setData(Uri.parse("geo:0,0?q=" + enderecoEvento  ) );


                startActivity(intentVisualizarEnderecoNoMapa);

                break;



            case R.id.id_ic_inscricao:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlInscricao;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlInscricao);
                break;



            case R.id.id_ic_blog:

                //Passando  a url para usar no floatbutton se precisar atualizar a pagina
                urlAtual = urlBlog;

                //Dizendo a url a ser utilizada
                webView_Obj.loadUrl(urlBlog);
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


            case R.id.id_opc_developer:
                //Ir para a tela do Desenvolvedor
                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaTelaDev = new Intent(MainActivity.this, TelaDeveloperScreen.class);
                startActivity(IntentVaiParaTelaDev);
                break;



        }//switch




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }//onNavigationItemSelected



    //--------------------------------------------------------------------------------------------//


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




    //--------------------------------------------------------------------------------------------//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.info) {

            //Ir para a tela de info

            //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
            Intent IntentVaiParaTelaInfo = new Intent(MainActivity.this, TelaInfo.class);
            startActivity(IntentVaiParaTelaInfo);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    //--------------------------------------------------------------------------------------------//



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

