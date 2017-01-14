package com.example.android.agenda;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;





//Classe responsavel por monitorar a posicao atual que estou usando o GPS
public class Localizador implements GoogleApiClient.ConnectionCallbacks, LocationListener {




    private final GoogleApiClient client;
    private final GoogleMap       mapa;




    public Localizador( Context context, GoogleMap mapa ) {


        //Criando um obj "GoogleApiClient" que permite acessar servicos que estao no smartphone. Nesse caso eh pra acesssar o GPS
        client = new GoogleApiClient.Builder( context )


                //Customizando - Dizendo qual servico quero usar. Nese caso vai ser o "LocationServices.API" que eh o GPS de localizacao
                .addApi( LocationServices.API )


                //Diz quem vai receber a resposta quando o metodo ".connect()" for usado.
                //Ou seja, quando eu usar metodo ".connect()" para conectar com o servico de GPS, pode ser que
                //A conexao seja feita de boas ou que nao deu pra conectar.
                //Ai o android vai mandar um aviso dizendo: "Conectou a parada" ou "Nao deu certo essa fita"
                //Se der certo, o metodo "onConnected()" vai ser executado
                //Se nao, o metodo "onConnectionSuspended()" vai ser executado
                .addConnectionCallbacks( this )



                //Constroi o obj
                .build();


        //Conectar com o servico
        client.connect();




        //Guardando a referencia do mapa
        this.mapa = mapa;


    }//Constructor








    @Override
    //Metodo que veio do implements GoogleApiClient.ConnectionCallbacks
    //Esse metodo vai ser executado
    public void onConnected( @Nullable Bundle bundle ) {


        //Objeto que descreve como eu quero as informacoes do servico de localizacao do smartphone
        //Ou seja, eh a descricao do que e como eu quero pegar do GPS
        LocationRequest request = new LocationRequest();



        //Dizer  qual eh o minimo de deslocamento que o usuario tem que fazer para que eu peca informacoes de posicionamento
        //Pq pode ser que o user fique parado no mesmo lugar, ai nao faz sentido ficar pedindo a localizacao atual
        //---------------------------->( so me mande atualizacoes se o user se mover fora de um raio de 50 metros )
        request.setSmallestDisplacement( 50 );



        //Dizendo qual eh o intervalo de tempo que o GPS vai me mandar atualizacoes de posicao
        //Pq pode ser que o user esta se movendo muito rapido, ai nao faz sendido ficar mandando atualizacoes a cada milisegundos
        //---------------->( 1000 milesegundos = 1 segundo )
        request.setInterval( 1000 );



        //Escolhendo se quer prioridade de precisao muito alta de posicionamento ou economizar bateria
        //Nesse caso estou preferindo a precisao ou inves de economzar bateria
        //Mas posso mudar isso se quiser "LocationRequest.*O que eu quero*"
        request.setPriority( LocationRequest.PRIORITY_HIGH_ACCURACY );



        //Aqui eu peco de fato as informacoes para o Location service
        //----------------------------------------------------->( obj que disse qual servico eu quero, objeto que descreve como eu quero as informacoes, quem vai receber as informacoes e nesse caso vai ser a propria classe)
        LocationServices.FusedLocationApi.requestLocationUpdates( client, request, this );


    }//onConnected





    @Override
    //Metodo que veio do implements GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended( int i ) {

    }//onConnectionSuspended






    @Override
    //Metodo que veio do implements LocationListener
    //Metodo chamado toda vez que oGPS mandar informacoes de localizacao
    public void onLocationChanged( Location location ) {

        LatLng       coordenada   = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng( coordenada );
        mapa.moveCamera( cameraUpdate );

    }//onLocationChanged




}//class