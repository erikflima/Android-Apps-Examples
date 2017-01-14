package com.example.android.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.agenda.modelo.Prova;


public class DetalhesProvaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_prova);


        //Pegando a intent que veio da tela anterior
        Intent intent = getIntent();

        //Pegando o obj do tipo "prova" que veio dentro da intent
        Prova prova = ( Prova ) intent.getSerializableExtra( "prova");



        //Associando os campos da tela
        TextView materia      = (TextView) findViewById( R.id.detalhes_prova_materia );
        TextView data         = (TextView) findViewById( R.id.detalhes_prova_data );
        ListView listaTopicos = (ListView) findViewById( R.id.detalhes_prova_topicos );


        //Passando os dados do obj que veio na Intent para os itens da tela
        materia     .setText( prova.getMateria() );
        data        .setText( prova.getMateria() );


        //Criando um adapter para a lista de topicos
        //-----------------------------------------------------(Contexto, layout que vai ser usado, Array com os dados a serem exbibidos)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, prova.getTopicos()  );

        //Passando o adapter para o item da tela
        listaTopicos.setAdapter( adapter );

    }//onCreate




}//class
