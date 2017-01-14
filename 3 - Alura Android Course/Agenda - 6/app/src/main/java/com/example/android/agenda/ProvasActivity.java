package com.example.android.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;


public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);



        //Criando um obj do tipo List
        List<String> topicosPort = Arrays.asList( "Sujeito", "Objeto Direto","Outro topico " );
        Prova provaPortugues     = new Prova( "Portugues", "25/05/2016", topicosPort );



        List<String> topicosMat = Arrays.asList( "Algebra", "Trigonometria" );
        Prova provaMatematica     = new Prova( "Matematica", "27/05/2016", topicosMat );



        List<Prova> provas = Arrays.asList( provaPortugues, provaMatematica);

        //------------------------------------------------------>Esse layout eh do tipo simples que o android disponibiliza, ele pega o retorno do metodo toString() do objeto que esta sendo passado
        ArrayAdapter<Prova> adapter = new ArrayAdapter<> ( this, android.R.layout.simple_list_item_1, provas);


        ListView lista = (ListView) findViewById( R.id.provas_lista);
        lista.setAdapter( adapter );



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //-------------------->(Objeto pai dos subItem que foi clicado, subitem que fui clicado, posicao do subItem clicado, id no subItem clicado)      )
            public void onItemClick( AdapterView<?> parent, View item, int position, long id ) {



                Prova prova = (Prova) parent.getItemAtPosition ( position);


                Toast.makeText( ProvasActivity.this, "Clico na prova de " +prova, Toast.LENGTH_LONG ).show();


                Intent vaiParaDetalhes = new Intent ( ProvasActivity.this, DetalhesProvaActivity.class );

                //Colocando dados na Intent
                //---------------------->(nome qualquer para o item, item q quero passar)
                vaiParaDetalhes.putExtra ( "prova", prova );


                startActivity( vaiParaDetalhes );



            }
        });



    }//onCreate



}//class
