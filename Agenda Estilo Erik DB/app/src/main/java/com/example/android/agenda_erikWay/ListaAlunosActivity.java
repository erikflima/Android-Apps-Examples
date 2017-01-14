package com.example.android.agenda_erikWay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //Criando um array com os itens que vou exibir no ListView na tela
        String [] alunos = {"Daniel", "Ronaldo", "Jeferson", "Felipe","Daniel", "Ronaldo", "Jeferson", "Felipe","Daniel", "Ronaldo", "Jeferson", "Felipe","Daniel", "Ronaldo", "Jeferson", "Felipe"};

        //Associando
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        Button   novoAluno   = (Button)   findViewById(R.id.novo_aluno);


        //O ArrayAdapter eh um retangulo para jogar o conteudo de arrays dentro do ListView
        //Se eu nao tivesse usado o ArrayAdapter, teria que ter colocado item por item dentro do ListView manualmente, o que seria zuado
        //--------Explicando os parametros--------------->(Em qual tela o ArrayAdapater vai ser usado, qual vai ser o layout para mostrar o conteudo da array , dizer qual é a array)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, alunos);

        //Colocando o Arrayadapter no ListView
        listaAlunos.setAdapter(adapter);



        //Funcao do botao para adicionar um novo contato na agenda_erikWay
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiProFormulario = new Intent( ListaAlunosActivity.this, Formulario.class);
                startActivity(IntentVaiProFormulario);
            }
        });









    }
}
