package com.example.android.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.android.agenda.dao.AlunoDAO;
import com.example.android.agenda.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    //views da tela
    private ListView listaAlunos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //Associando
        Button btnNovoAluno = (Button)   findViewById(R.id.novo_aluno);
        listaAlunos         = (ListView) findViewById(R.id.lista_alunos);





        //Funcao do botao para adicionar um novo contato na agenda
        btnNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiProFormulario = new Intent(ListaAlunosActivity.this, Formulario.class);
                startActivity(IntentVaiProFormulario);
            }
        });






        //Funcao para click simples sobre os subcomponentes da listView qu esta na tela
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //-------------------->(Objeto pai dos subItem que foi clicado, subitem que fui clicado, posicao do subItem clicado, id no subItem clicado)      )
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {


                //Pegando um objeto aluno que vem do subItem que foi clicado
                Aluno alunoVindoDoSubItem = (Aluno) listaAlunos.getItemAtPosition( position );


                //Parametros do intent------------------->(Em que tela estou, Para qual tela eu vou)
                Intent IntentVaiProFormulario = new Intent(ListaAlunosActivity.this, Formulario.class);



                //Colocando informacoes extras no intent que vai ser passado
                //Importante: O objeto que vou passar, tem que ter "implements Serializable" na classe dele.
                //"Serializable" Significa que o objeto pode ser mudado para binario e convertido para objeto novamente na tela que receber o objeto
                //---------------------------->(id que dou para o objeto que vou passar, objeto que vai ser passado para a outra tela )
                IntentVaiProFormulario.putExtra("id_IntentExtraAluno", alunoVindoDoSubItem );

                startActivity(IntentVaiProFormulario);







            }
        });


        //Deixei isso aqui anotado so para referencia mesmo.....
        /** Exemplo de implementacao de click longo nos subitens de um listView usando arrayAdpter
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //---------------------->(Objeto pai dos subItem que foi clicado, subitem que fui clicado, posicao do subItem clicado, id no subItem clicado)      )
            public boolean onItemLongClick(AdapterView<?> lista, View item, int position, long id) {


                Toast.makeText(ListaAlunosActivity.this, " Teste - Clique longo ", Toast.LENGTH_SHORT).show();

                //true  = Faz somente esse evento e para
                //false = Executa o proximo evento que inicia com clique longo
                return true;
            }
        });
         **/



        //Vincula o "contextMenu" ao objeto "lista alunos" eu esta na telam
        //Long click para chamar o contextMenu
        registerForContextMenu(listaAlunos);

    }






    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();
    }


    //Preenche a listView da tela com  conteudo da tabela "Alunos" do DB "Agenda"
    private void carregaLista() {



        //Passando a tela que estou "this" para o construtor do AlunoDAO
        AlunoDAO dao = new AlunoDAO(this);


        //Criando uma lista com os itens que vao vir da tabela no BD, ai depois vou exibir no ListView na tela
        List<Aluno> listaRetornadaDeAlunos =  dao.buscaAlunos();
        dao.close();


        //O ArrayAdapter eh um retangulo para jogar o conteudo de arrays dentro do ListView
        //Se eu nao tivesse usado o ArrayAdapter, teria que ter colocado item por item dentro do ListView manualmente, o que seria zuado
        //--------Explicando os parametros--------------->(Em qual tela o ArrayAdapater vai ser usado, qual vai ser o layout para mostrar o conteudo da array , dizer qual é a array ou lista que estao dos dados)
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>( this, R.layout.support_simple_spinner_dropdown_item, listaRetornadaDeAlunos );




        //Colocando o Arrayadapter no ListView

        //---**Obs Super Importante!**---
        //O "ArrayAdapter" tem a funcao de receber um objeto array e jogar em uma listView
        //para ai sim exibir na tela.
        //So que tem um porem :( , ele pega o objeto recebido e converte tudo para
        //string usando o metodo "toString()" da class object (mae de todos os objetos)
        //Ai se as posicoes da array nao forem String, ele vai mostrar o endereco de memoria do objeto
        //Entao, pra funcionar, eh preciso sobre escrever o metodo "toString"

        listaAlunos.setAdapter(adapter);
    }



    @Override
    /**                                         !------Importante------!
     Posso usar o um objeto "MenuInflater" e um "xml" para montar o menu, ou usar o metodo "add()". As duas opcoes sao certas
     Esse eh o menu de opcoes quando um clickLongo sobre um objeto
     Nesse caso o menu vai aparecer quando eu pressioar alguma opcao da lista da agenda
     ------------------------------>(x, tela em que o menu esta, informacoes adicionais do item da lista que foi clicado) **/
     public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        //Adicionando uma opcao no ContextMenu
        MenuItem opcDeletar = menu.add("Deletar");

        opcDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //nao entendi nada
                //O que entendi: Tenho que pegar as informacoes adicinais do item clicado e guardar em uma variavel
                //Nada ve isso :(
                AdapterView.AdapterContextMenuInfo infomacaoAdicional = (AdapterView.AdapterContextMenuInfo) menuInfo;
 
                //nao entendi nada
                //O que entendi: Aqui pego a posicao do objeto da lista
                //Feito isso, eu guardo os todos os dados do objeto
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition( infomacaoAdicional.position );



                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta( aluno );


                dao.close();


                Toast.makeText(ListaAlunosActivity.this, "Aluno deletado :)", Toast.LENGTH_LONG).show();


                //Atualiza a lista que esta sendo exibida na tela
                carregaLista();



                return false;
            }
        });




    }












}
