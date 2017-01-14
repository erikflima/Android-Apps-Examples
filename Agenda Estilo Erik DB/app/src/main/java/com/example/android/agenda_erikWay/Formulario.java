package com.example.android.agenda_erikWay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.android.agenda_erikWay.modelo.Aluno;


public class Formulario extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

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


                //Objeto que pega os dados preenchidos da tela do formulario
                FormularioHelper helper = new FormularioHelper();


                //Objeto aluno que vai receber os dados da tela do formulario
                Aluno objAluno = new Aluno();


                //Pegando os dados da tela. Obs: Passar a tela que estou como parametro
                objAluno = helper.pegaAluno(Formulario.this);



                Toast.makeText(Formulario.this, "Aluno \n"

                        + "Nome: "     + objAluno.getNome()     + "\n"
                        + "Endereço: " + objAluno.getEndereco() + "\n"
                        + "Telefone: " + objAluno.getTelefone() + "\n"
                        + "Site: "     + objAluno.getSite()     + "\n"
                        + "Nota: "     + objAluno.getNota()     + "\n"

                        , Toast.LENGTH_LONG).show();



                //Destroi a tela atual e volta para a tela anterior
                finish();
                break;

        }//switch


        return super.onOptionsItemSelected(item);
    }



}//class
