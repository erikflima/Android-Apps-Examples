package com.appserik.android.leitorderss;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;





public class ReadRss extends AsyncTask <Void, Void, Void> {


    //URL do RSS que vou acessar
    String adress = "https://www.sciencemag.org/rss/news_current.xml";

    //Objeto URL
    URL url;


    //Preparado um contexto
    Context context;

    //Preparando uma barra de progresso
    ProgressDialog progressDialog;




    //Construtor
    public ReadRss ( Context context){

        //Passando o contexto recebido para a variavel local context
        this.context = context;

        //Setting da barra de progresso
        progressDialog = new ProgressDialog( context );

        //Adicionando o texto na hora do load
        progressDialog.setMessage("Carregando feed :)");


    }//construtor







    @Override
    protected void onPreExecute() {

        //Exibir a barra de load na tela
        progressDialog.show();

        super.onPreExecute();
    }//onPreExecute





    @Override
    protected Void doInBackground(Void... params) {

        // O metodo GetData() vai retornar o XMl do RSS
        //Ai, uso esse metodo 'ProcessXml()" para pegar o nome original do RSS
        ProcessXml ( Getdata() );


        return null;

    }//doInBackground





    //Recebe o XML do RSS, pega o nome orinal dele e devolve
    private void ProcessXml( Document data ) {



        //Se o XML recebido nao estiver vazio
        if ( data !=null ) {


            //Pega o nome do RSS e ebibe no log
            //Lembrado que "Root" eh so uma palavra pra que na hora que eu for procurar esse print no logcat
            Log.d("Erik", "*******Erik - Nome do XML que foi baixado: " +data.getDocumentElement().getNodeName() + " *******" );


            //Criando um array de FeedItem, que vou usar mais adiante no codigo
            ArrayList< FeedItem > feedItems = new ArrayList<>();





            //*****Criando referencia as tags do XML que foi baixado****//


            //Crio um obj pra guardar a tag raiz do XML. Ou seja guarda a primeira tag do xml que eh "<rss>"
            Element  root    = data.getDocumentElement();


            //Crio um obj pra guardar a tag "<channel>"
            Node     channel = root.getChildNodes().item( 1 );


            //Guarda todas as Subtags <item> do XML
            NodeList items   = channel.getChildNodes();



            //*****Fazendo looping e exibindo das o conteudo das tags no log****//


            //Laco para percorrer todos os <item> do xml
            for ( int i=0;   i < items.getLength();   i++){


                //item atual
                Node currentchild = items.item( i );


                //Verifica se trata-se realamente de um item da tag <item>
                if(  currentchild.getNodeName().equalsIgnoreCase("item")  ) {


                    //Criando um obj da classe FeedItem que criei
                    FeedItem item = new FeedItem();


                    //Guarda os subtags abaixo da tag <item>
                    NodeList itemchilds = currentchild.getChildNodes();


                    //Laco para percorrer todos os subitens da tag <item>
                    for (int j = 0; j < itemchilds.getLength(); j++) {

                        //subitem atual
                        Node current = itemchilds.item(j);




                        //Verifico em qual tag estou. pego o conteudo que esta nela e coloco dentro do objeto item
                        switch (  current.getNodeName()  ) {

                            case "title":
                                item.setTitle( current.getTextContent() );
                                break;

                            case "link":
                                item.setLink( current.getTextContent() );
                                break;


                            case "description":
                                item.setDescription( current.getTextContent() );
                                break;


                            case "pubDate":
                                item.setPubDate( current.getTextContent() );
                                break;


                            case "media:thumbnail":
                                item.setThumbnailUrl( current.getTextContent() );
                                break;
                        }//switch




                    }//for-2


                    //Adicionando o item preenchido no array que criei
                    feedItems.add( item );


                    //Exibindo o que foi guardado do item atual
                    Log.d("Erik- Conteudo Item XML", "");
                    Log.d("Erik- Conteudo Item XML", "-------------------------------------------");
                    Log.d("Erik- Conteudo Item XML", "item-Title      : " + item.getTitle()       );
                    Log.d("Erik- Conteudo Item XML", "item-Description: " + item.getDescription() );
                    Log.d("Erik- Conteudo Item XML", "item-Link       : " + item.getLink()        );
                    Log.d("Erik- Conteudo Item XML", "item-PubDate    : " + item.getPubDate()     );
                    Log.d("Erik- Conteudo Item XML", "-------------------------------------------");
                    Log.d("Erik- Conteudo Item XML", "");
                }//if-2

            }//for-1


        }//if-1


    }//ProcessXml






    //Esse metodo vai pegar o xml do RSS e retornar um objeto do tipo 'Document'
    public Document Getdata(){


        try {

            //Passando o endereco do RSS para o objeto do tipo URL
            url = new URL ( adress );

            //Criando um objeto HttpURLConnection para receber o resultado da conexao com a net
            HttpURLConnection connection = (HttpURLConnection)  url.openConnection();

            //Dizendo que vou usar  metodo "GET"
            connection.setRequestMethod("GET");

            //Pegando o conteudo recebido no get e passando em um ojb do tipo InputStream
            InputStream inputStream = connection.getInputStream();



            //**--------Parte complicada-------**//

            //Criando um objeto DocumentBuilderFactory
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            //Criando um objeto do tipo 'DocumentBuilder'
            DocumentBuilder builder = builderFactory.newDocumentBuilder();


            //Criando um obj do tipo Document
            //Ai eu uso a instancia do 'DocumentBuilder' para pegar o conteudo do inputStream
            //e passar isso para o obj 'xmlDoc' que criei
            Document xmlDoc = builder.parse( inputStream );


            //Por fim, retorno o documento pronto com o conteudo do XML dentro dele :)
            return xmlDoc;


        }//try
        catch (Exception e) {

            e.printStackTrace();
            Log.d( "Erik","Aconteceu um erro de execao no programa. Mensagem da variavel e: " +e );

            return null;

        }//catch


    }//Getdata







    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);



        //Fechar a tela de loading
        progressDialog.dismiss();


        //Mostrar o resultado que o servidor devolveu na Thread principal
        Toast.makeText( context,  "O app funcionou!\nVerifique o log para mais detalhes :)", Toast.LENGTH_LONG).show();

    }//onPostExecute



}//class
