package com.example.android.agenda;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.android.agenda.dao.AlunoDAO;
import com.example.android.agenda.modelo.Aluno;

import java.io.File;

public class Formulario extends AppCompatActivity {



    //Constante que criei para substituir o numero
    public static final int CODIGO_TIRAR_FOTO = 100;

    //Variavel axiliar
    public String caminhoDaFoto;


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

        //Se tiver alguma informcao extra no intent, entao pegue-a
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




        //Associando
        Button btnPhoto = (Button)   findViewById(R.id.formulario_btnPhoto);

        //Funcao do botao de photo
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Parametros do intent-------------->(evocando a acao de captura de imagem)
                //
                Intent intentTirarPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Preparando o caminho da foto e o nome que o arquivo que for salvo vai ter
                //-------------------->Pego o endereco da pasta da minha aplicacao, pego o momento em milisegundos para nao ter foto repetida, adiciono ".jpg" no final)

                //caminhoDaFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                caminhoDaFoto = "/storage/emulated/0/DCIM/100ANDRO/" +"erik"+  System.currentTimeMillis() + ".jpg";


                //Crio um objeto do tipo "File" e coloco o caminho da foto que vai ser salva nele.
                //
                File arquivoFoto = new File(caminhoDaFoto);


                //Passando o caminho onde a foto deve ser salva, para isso um objeto do tipo "File" deve ser passado como parametro
                //O objeto file ja recebeu o caminho da foto que vai ser tirada
                intentTirarPhoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile( arquivoFoto ));


                //Chama a app da camera para tirar a foto
                //Nesse momento o controle do android e passado para o app da camera.
                //Ai, quando o controle for devolvido para esse app, o metodo "onActivityResult()" vai
                //estar com o resultado.
                //------------------->(Intent, um id para quando eu for pegar o resultado no metodo "onActivityResult()" )
                startActivityForResult(intentTirarPhoto, CODIGO_TIRAR_FOTO);
            }
        });




    }//onCreate




    @Override
    //Esse metodo eh executado toda vez que eu usar  "startActivityForResult()"
    //Ele pega o resultado que foi pedido pelo Intent que chamou o metodo "startActivityForResult()"
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //Pode ser que o usuario apertou o botao foto, abriu a camera e simplemente cancelou a acao!
        //Se o usuario realmente tirou a foto...entao
        if (resultCode == Activity.RESULT_OK) {

            //Verifico o requestCode
            //
            if (requestCode == CODIGO_TIRAR_FOTO) {

                //Pra nao ter repeticao de codigo o melhor eh que  helper faca o carregamento da foto
                //
                helper.carregaImagemNaTela( caminhoDaFoto );


            }//if 2

        }//if 1

    }//onActivityResult




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
                //
                AlunoDAO dao = new AlunoDAO(this);



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



                //Destroi a tela atual e volta para a tela anterior. Por causa do clico vida da Activity
                finish();
                break;

        }//switch


        return super.onOptionsItemSelected(item);
    }



}//class
