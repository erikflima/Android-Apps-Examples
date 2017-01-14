package com.example.android.agenda;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.util.List;




//A extensao "SupportMapFragment" herda as funcionalidades de um mapa do google
//A implementacao "OnMapReadyCallback" herda methodos para manipular o objeto do tipo GoogleMap que vou usar
public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {


    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );



        //Cria um objeto do GoogleMaps para que eu possa manipular e editar como eu quiser
        getMapAsync( this );



    }//onCreate










    //Metodo chamado depois que do metodo "getMapAsync()" quando o mapa estiver pronto e for devolvido. Ai aqui eu faco as configuracoes que quero
    @Override
    public void onMapReady( GoogleMap googleMap ) {



        Log.v( "MapaFragment","************PASSO1: ENviou o endereco");

        //Aqui eu digo com eh a latitude e longitude do local
        LatLng posicaoDaEscola = pegaCoordenadaDoEndereco("Rua Vergueiro 3185, Vila Mariana, São Paulo");




        //Se a cordenada recebida nao for vazia
        if( posicaoDaEscola != null ){



            //Preparando um objeto que contem configuracoes que eu quero, para depois disso passar para o metodo "moveCamera()" que vai nagegar no mapa
            //---------------------------------------------------->( coordenadas LntLng, nivel de zoom )
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom( posicaoDaEscola, 17 );



            //Posicionando e colocando um pino no mapa
            googleMap.moveCamera( update );




        }//if



        /*

        //Agora vou pegar todos os alunos do DB para exibir com um pino no mapa

        AlunoDAO alunoDAO = new AlunoDAO ( getContext() );



        //Para cada item que veio no retorno do metodo faca...
        for( Aluno aluno : alunoDAO.buscaAlunos() ){


            LatLng coordenada  = pegaCoordenadaDoEndereco( aluno.getEndereco() );

            ///Se o endereco do aluno que esta no bd for valido, entao coloca um pino no mapa
            if ( coordenada != null){


                //Objeto que server para criar pinos no em locais no GoogleMaps
                MarkerOptions marcador = new MarkerOptions();



                //---Editando as caracteriscas do Marcador-----


                //Posicao do marcador
                marcador.position(coordenada);

                ////Texto curt em cima do marcador
                marcador.title( aluno.getNome() );

                //Subtexto em abaixo do titulo do marcador
                marcador.snippet( String.valueOf( aluno.getNota() ) );

                //Colocando o marcadr no mapa
                googleMap.addMarker(marcador);



            }//if


        }//for

        //fechar
        alunoDAO.close();

    */


    }//onMapReady









    private LatLng pegaCoordenadaDoEndereco( String endereco ){



        //Nesse parte do codigo vai ser feito acesso a internet, ai tem que colocar dentro do try se rolar excecao
        try {



            //O tipo Geocoder converte um endereco em texto para numeracao de "latitude" e "longitude"
            //Esse objeto acessa a internet para fazer essa conversao
            Geocoder geocoder = new Geocoder( getContext() );



            Log.v( "MapaFragment","************PASSO2: Endereco que foi pego no google: " +endereco );



            //Convertendo um endereco em lat e longi
            //------------------------->( Endereco, Na hora da busca do endereco pode ser que tenha mais de um resultado - entao digo que quero pegar o primeiro resultado que aparecer)
            List<Address> resultados = geocoder.getFromLocationName( endereco , 1 );


            Log.v( "MapaFragment","************PASSO2: Endereco que foi pego no google: " +resultados );




            //Se o Google Maps me devolveu algum resultado na busca
            if ( !resultados.isEmpty() ){

                Log.v( "MapaFragment","************PASSO3: Retornou algo"  );

                //Extraindo a Lat e Lng--->(   Pegando a Ltn                  , pegando a Lng )
                LatLng posicao = new LatLng( resultados.get(0).getLatitude(), resultados.get(0).getLongitude() );







                //Devolvo as coordenadas do local
                return posicao;

            }//if




        }//try
        catch ( IOException e ) {

            e.printStackTrace();


        }//catch


        Log.v( "MapaFragment","************PASSO4: Retornou vazio"  );



        //*******
        LatLng posicao = new LatLng(-33.867, 151.206);


        //Se nao houver nenhuma coordenada encontrada
        return posicao;

    }//pegaCoordenadaDoEndereco






}//class
