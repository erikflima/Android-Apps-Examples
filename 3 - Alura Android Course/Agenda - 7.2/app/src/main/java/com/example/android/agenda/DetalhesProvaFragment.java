package com.example.android.agenda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.agenda.modelo.Prova;


public class DetalhesProvaFragment extends Fragment {


    private TextView campoMateria;
    private TextView campoData;
    private ListView listaTopicos;


    //Metodo chamado pelo android para construir o Fragment
    //Eh praticamento o onCreate() de um Fragment
    //Os parametros desse metodo sao passados pelo android
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {


        //Crio uma view que vai ser pega e colocada dentro do XMl que o Fragment vai ser colcado (pois o fragment sempre vai estar dentro de uma tela, afinal eh pra isso que um fragment serve rs)
        //------------------------->( layout que vou usar, onde vou colocar o fragment, false = nao infla de imediato )
        View view = inflater.inflate( R.layout.fragment_detalhes_prova, container, false );



        //
        campoMateria = (TextView) view.findViewById( R.id.detalhes_prova_materia );
        campoData    = (TextView) view.findViewById( R.id.detalhes_prova_data    );
        listaTopicos = (ListView) view.findViewById( R.id.detalhes_prova_topicos );


        //Verifica se veio algum Bundle (obj de tragego de dados) ao criar um objeto do tipo "DetalhesProvaFrament", se tiver eu pego
        Bundle parametros = getArguments();

        if( parametros !=null) {

            //Pego o objeto do tipo prova que veio como parametro
            Prova prova = (Prova) parametros.getSerializable( "prova" );


            //Populo os campos da tela com o obj do tipo "prova" que recebi
            populaCamposCom( prova );
        }//if



        return view;


    }//onCreateView





//Preenchendo as campos do Fragment
public void populaCamposCom ( Prova prova){


    campoMateria.setText( prova.getMateria() );
    campoData   .setText( prova.getData()    );


    //---------------------------------------------------------->( contexto, layout que vai ser usado para mostar os itens, conteudo a ser exibido )
   ArrayAdapter<String> adapterTopicos = new ArrayAdapter<String>( getContext(), android.R.layout.simple_list_item_1, prova.getTopicos() );

   listaTopicos.setAdapter( adapterTopicos );




}//populaCamposCom





}//class
