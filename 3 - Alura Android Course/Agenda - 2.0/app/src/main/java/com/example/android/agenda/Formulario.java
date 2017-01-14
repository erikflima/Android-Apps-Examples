package com.example.android.agenda;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.android.agenda.dao.AlunoDAO;
import com.example.android.agenda.modelo.Aluno;

public class Formulario extends AppCompatActivity {

    //Criando um objeto FormularioHelper
    private FormularioHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        //Declarando o helper para ajudar a maipular os campos do formulario na tela
        helper = new FormularioHelper(this);


        //Pegando a Intent enviada pela tela ListaAlunosActivty
        Intent intentRecebido = getIntent();

        //Pegando  informacao Extra que esta dentro do intent recebido
        Aluno alunoRecebidoDoIntent;
        alunoRecebidoDoIntent = (Aluno) intentRecebido.getSerializableExtra( "id_IntentExtraAluno" );


        //Verificando se a informacao Extra esta vazia
        //ou seja, verificar se a intent que eu capturei eh realmente a intent que me passa o aluno
        //Isso eh feito pq aquele botao de adicionar na tela, tambem cria um intent quando pressionado
        //Ai ja viu! tem que diferenciar os intents
        if( alunoRecebidoDoIntent != null){

            //preenche o formulario na tela
            helper.preencheFormulario( alunoRecebidoDoIntent );


        }//if


    }//onCreate



    @Override
    // Esse metodo veio da AppCompatActivity e devolve um objeto Inflater
    // Inflate the menu; this adds items to the action bar if it is present.
    public boolean onCreateOptionsMenu(Menu menu) {

        //Criando um objeto MenuInflater que tem acesso ao metodo inflate()
        MenuInflater inflater = getMenuInflater();

        //Peco para inflar o menu da tela
        inflater.inflate(R.menu.menu_formulario, menu);


        return true;
    }//onCreateOptionsMenu



    @Override //Metodo executado toda vez que o usuario selecionar uma opcao na barra de menu superior
    public boolean onOptionsItemSelected(MenuItem item) {//Recebo o item da opção escolhida


        switch (item.getItemId()){

            case R.id.menu_formulario_ok:




                //Pegando os dados da tela do formulario
                Aluno alunoRecebido = helper.pegaAluno();


                //Passando a tela que estou "this" para o construtor do AlunoDAO
                AlunoDAO dao = new AlunoDAO(this);

                ///**************************
                //***************************

                //Verifica se o formulario preenchido que esta na tela eh um novo aluno ou
                //se trata-se de um aluno a ser atualizado
                if ( alunoRecebido.getId() != null ){ //Se tiver id..entao trata-se de uma alteracao



                    //Realiza o update do aluno que ja existe na tabela "Alunos" no BD "Agenda"
                    dao.altera( alunoRecebido );
                    dao.close();

                }

                else{

                    //****Etapa de Inserir no banco de dados****

                    //Fazendo o insert
                    dao.insere(alunoRecebido);
                    dao.close();


                }


                Toast.makeText(Formulario.this, "Aluno \n"

                        + "Nome: "     + alunoRecebido.getNome()     + "\n"
                        + "Endereço: " + alunoRecebido.getEndereco() + "\n"
                        + "Telefone: " + alunoRecebido.getTelefone() + "\n"
                        + "Site: "     + alunoRecebido.getSite()     + "\n"
                        + "Nota: "     + alunoRecebido.getNota()     + "\n"

                        , Toast.LENGTH_LONG).show();



                //Destroi a tela atual e volta para a tela anterior
                finish();
                break;

        }//switch


        return super.onOptionsItemSelected(item);
    }



}//class
