package com.example.android.agenda;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.example.android.agenda.dao.AlunoDAO;
import com.example.android.agenda.modelo.Aluno;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;




//A extensao "SupportMapFragment" herda as funcionalidades de um mapa do google
//A implementacao "OnMapReadyCallback" herda methodos para manipular o objeto do tipo GoogleMap que vou usar
public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Cria um objeto do GoogleMaps para que eu possa manipular e editar como eu quiser
        getMapAsync( this );

    }//onCreate





    //Metodo chamado depois que do metodo "getMapAsync()" quando o mapa estiver pronto e for devolvido. Ai aqui eu faco as configuracoes que quero
    @Override
    public void onMapReady(GoogleMap googleMap) {



        LatLng posicaoDaEscola = pegaCoordenadaDoEndereco("Rua Vergueiro 3185, Vila Mariana, Sao Paulo");


        //Se a cordenada recebida nao for vazia
        if (posicaoDaEscola != null) {


            //Preparando um objeto que contem configuracoes que eu quero, para depois disso passar para o metodo "moveCamera()" que vai nagegar no mapa
            //---------------------------------------------------->( coordenadas LntLng, nivel de zoom )
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom( posicaoDaEscola, 3 );


            //Posicionando e colocando um pino no mapa
            googleMap.moveCamera(update);

        }//if




        //Etapa em que vou colocar um marcador para cada aluno cadastrado na agenda


        //Criando um obj Aluno
        AlunoDAO alunoDAO = new AlunoDAO( getContext() );


        //Para cada aluno encontrado faca
        for ( Aluno aluno : alunoDAO.buscaAlunos() ) {


            //Pegando o endereco de cada aluno
            LatLng coordenada = pegaCoordenadaDoEndereco(aluno.getEndereco());


            //Se nao vazio
            if (coordenada != null)
            {

                //Criando um marcador
                MarkerOptions marcador = new MarkerOptions();

                //Posicao do marcador
                marcador.position( coordenada );

                //Titulo do Marcador
                marcador.title( aluno.getNome() );

                //Subtitulo do Marcador
                marcador.snippet (   String.valueOf( aluno.getNota() )    );

                //Colocando de fato o marcador no mapa
                googleMap.addMarker( marcador );

            }//if

        }//for


        alunoDAO.close();




        //Isso faz com que o quando o user se mover a posicao que ele esta no mapa vai ser atualizado
        new Localizador( getContext(), googleMap );




    }//onMapReady








    private LatLng pegaCoordenadaDoEndereco( String endereco ) {


        //Nesse parte do codigo vai ser feito acesso a internet, ai tem que colocar dentro do try se rolar excecao
        try {


            //O tipo Geocoder converte um endereco em texto para numeracao de "latitude" e "longitude"
            //Esse objeto acessa a internet para fazer essa conversao
            Geocoder geocoder = new Geocoder( getContext() );



            //********************************************
            // ESSA MELDA NAO ESTA RETORNANDO NADA        *
            //*********************************************
            //Convertendo um endereco em lat e longi
            //------------------------->( Endereco, Na hora da busca do endereco pode ser que tenha mais de um resultado - entao digo que quero pegar o primeiro resultado que aparecer)
            List<Address> resultados = geocoder.getFromLocationName( endereco, 1 );



            //Se o Google Maps me devolveu algum resultado na busca
            if (!resultados.isEmpty())
            {



                Log.v("Erik","Entrou no if de nao vazio");

                //Extraindo a Lat e Lng--->(   Pegando a Ltn                  , pegando a Lng )
                LatLng posicao = new LatLng( resultados.get(0).getLatitude(), resultados.get(0).getLongitude());

                return posicao;

            }//if

        }//try
        catch (IOException e)
        {
            e.printStackTrace();
        }//catch




        Log.v("Erik","Retornou vazio");

        //Coordenadas USA
        LatLng sydney = new LatLng(39.562791, -102.832031);
        return sydney;


        //return null;

    }//pegaCoordenadaDoEndereco





}//class
