package com.example.android.agenda.ConexaoComServidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Classe responsavel por mandar informacoes para o servidor
 */
public class WebClient {


    //Metodo  para realizar a coneccao com o servidor e realizar um "post"
    public String post( String jsonASerEnviado){

        try {


            //Definindo a URL do servidor
            URL url = new URL( "https://www.caelum.com.br/mobile" );


            //Criando um objeto HttpURLConnection que faz conexao
            //o metodo openConnection() retorna uma url
            HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();


            //Aviso ao servidor o formato de informacao que estou enviando, nesse caso sera JSON
            connection.setRequestProperty("Content-type", "application/json");


            //Aviso o servidor o formato que espero que as informacoes sejam devolvidas
            connection.setRequestProperty("Accept", "application/json");


            //Avisar a conection que eu vou fazer um "post"
            //e vou escrever dados para enviar na requisicao
            connection.setDoOutput( true );


            //Criando uma variavel para escrever as informacoes
            // que vou enviar para o servidor
            PrintStream output = new PrintStream( connection.getOutputStream() );


            //Escrevendo o que vai ser enviado na requisicao
            output.println( jsonASerEnviado ) ;


            //Fazendo a coneccao de fato!
            connection.connect();


            //Pegando o resultado que o servidor devolveu
            Scanner scanner = new Scanner ( connection.getInputStream() );
            String resposta = scanner.next();


            //Retornando o que peguei no servidor :)
            return resposta;


        } catch (MalformedURLException e) {

            //Se a url nao estiver corretamente formatada
            e.printStackTrace();


        } catch (IOException e) {

            //Se acontercer algum erro de envio ou devolucao dos dados
            e.printStackTrace();
        }

        //Se der algum erro retorna vazio
        return "Conexão não realizada, servidor fora ou sem net :(";

    }//post




}//class
