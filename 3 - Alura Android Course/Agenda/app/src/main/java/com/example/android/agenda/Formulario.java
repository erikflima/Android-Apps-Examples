package com.example.android.agenda;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.android.agenda.modelo.Aluno;

public class Formulario extends AppCompatActivity {

    //Criando um objeto FormularioHelper
    private FormularioHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        //Nao entendi pq declarar aqui
        helper = new FormularioHelper(this);

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


                Aluno alunoRecebido = helper.pegaAluno();



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
