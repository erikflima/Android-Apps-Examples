package com.example.android.agenda;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Browser;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.android.agenda.dao.AlunoDAO;
import com.example.android.agenda.meusAdapters.AlunosAdapter;
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


        //<editor-fold">

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
        }
         );
         **/

        //</editor-fold>



        //Vincula o "contextMenu" ao objeto "lista alunos" eu esta na telam
        //Long click para chamar o contextMenu
        registerForContextMenu(listaAlunos);

    }//onCreate


    @Override
    protected void onResume() {
        super.onResume();

        carregaLista();
    }



    //Preenche a listView da tela com  conteudo da tabela "Alunos" do DB "Agenda"
    private void carregaLista() {



        //Passando a tela que estou "this" para o construtor do AlunoDAO
        //
        AlunoDAO dao = new AlunoDAO(this);


        //Criando uma lista com os itens que vao vir da tabela no BD, ai depois vou exibir no ListView na tela
        //
        List<Aluno> listaRetornadaDeAlunos =  dao.buscaAlunos();
        dao.close();


        //
        //
        //-------------------------------------->(tela que estou, array de onde a lista vai pegar os dados)
        AlunosAdapter adapter = new AlunosAdapter(this, listaRetornadaDeAlunos );


        //Colocando o adapter personalizado no ListView
        //---**Obs Super Importante!**---
        //Nesse momento o listaAlunos faz as sequintes acoes:
        // 1) Chama o metodo  "adapter.getCount()" e pergunta quantos itens vao ser exibidos na tela
        // 2) Depois chama o metodo "adapter.getView()" e pedi os dados a serem exibidos a cada linha da lista na tela.
        // 3)
        // 4)
        listaAlunos.setAdapter( adapter );
    }




    @Override
    //                                                                                                                                       <editor-fold>
    //                                         !------Importante------!
    // Posso usar o um objeto "MenuInflater" e um "xml" para montar o menu, ou usar o metodo "add()". As duas opcoes sao certas
    // Esse eh o menu de opcoes quando um clickLongo sobre um objeto
     //Nesse caso o menu vai aparecer quando eu pressioar alguma opcao da lista da agenda
    //
    //----------------------------->(x, tela em que o menu esta, informacoes adicionais do item da lista que foi clicado)                   </editor-fold>
     public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {



        //**------------------------Pegando as informcoes do aluno do item que recebeu o click-----------*
        //O que entendi: Tenho que pegar as informacoes adicinais do item clicado e guardar em uma variavel
        //Nada ve isso :(
        AdapterView.AdapterContextMenuInfo infomacaoAdicional = (AdapterView.AdapterContextMenuInfo) menuInfo;

        //nao entendi nada
        //O que entendi: Aqui pego a posicao do objeto da lista
        //Feito isso, eu guardo os todos os dados do objeto
        //O comando "final" significa que o objeto pode ser visivel nos submetodos.
        final Aluno alunoRecebidoDoContextMenu = (Aluno) listaAlunos.getItemAtPosition(infomacaoAdicional.position);







        //Adicionando uma opcao no ContextMenu
        //
        MenuItem opcRealizarChamada = menu.add("Realizar chamada");

        opcRealizarChamada.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //Verifico se o usuario autorizou a permissao de ligacao de chamada telefonica
                //Apartir do Android 6, eh preciso fazer essa verificao
                //Se a permissao nao esta ativa, faca...
                //------------------------------------>( Qual tela estou, Qual a permissão que quero verificar )
                if ( ActivityCompat.checkSelfPermission( ListaAlunosActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {


                    //Pedir permissao de chamada ao usuario
                    //------------------------------>(Qual tela estou, array de string com as permissoes que quero solicitar ao usuario, um numero inteiro que sera um numero de id para ser usado no metodo "onRequestPermissionsResult()" se eu quiser implementar alguma acao adicional depois que o usuario negar ou  aceitar a solicitacao de permissao)
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 100);

                }//if

                //Se eu ja tiver a permissao,
                // entao faz a ligacao
                else {

                    //Criando a funcao da opcao "Realizar chamada"
                    //Usando "ACTION_CALL"
                    Intent intentRealizarChamada = new Intent(Intent.ACTION_CALL);

                    //Digo qual recurso quero utilizar. Nesse caso chamada telefonica
                    //O comando ".setData" serve para passar um parametro dentro da Intent
                    //-------------------->( Objeto do tipo identificador de recurso, protocolo da acao)
                    intentRealizarChamada.setData(Uri.parse("tel:" + alunoRecebidoDoContextMenu.getTelefone()));


                    //Faz chamada se clicado
                    //
                    startActivity(intentRealizarChamada);

                }//else


                return false;
            }
        });




        //Adicionando uma opcao no ContextMenu
        //
        MenuItem opcEnviarSMS = menu.add("Enviar um SMS");

        //Criando a funcao da opcao "Enviar um SMS" - Jeito 1 de fazer isso
        //
        Intent   intentMandaSMS  = new Intent( Intent.ACTION_VIEW  );

        //Digo qual recurso quero utilizar. Nesse caso o SMS
        //O comando ".setData" serve para passar um parametro dentro da Intent
        //-------------------->( Objeto do tipo identificador de recurso)
        intentMandaSMS.setData ( Uri.parse ( "sms:" +alunoRecebidoDoContextMenu.getTelefone() ) );

        //Ativa acao. Se a opcao for clicada faz a acao do Intent
        //
        opcEnviarSMS.setIntent( intentMandaSMS );








        //Adicionando uma opcao no ContextMenu
        //
        MenuItem opcVisualizarEndereco = menu.add("Visualizar Endereço no Mapa");

        //Criando a funcao da opcao "Visualizar Endereço no Map" - Jeito 1 de fazer isso
        //
        Intent   intentVisualizarEnderecoNoMapa  = new Intent( Intent.ACTION_VIEW  );

        //Digo qual recurso quero utilizar. Nesse caso o google Maps
        //O comando ".setData" serve para passar um parametro dentro da Intent
        //-------------------->( Objeto do tipo identificador de recurso, protocolo de geoLocalização)
        intentVisualizarEnderecoNoMapa.setData(Uri.parse("geo:0,0?q=" + alunoRecebidoDoContextMenu.getEndereco()));

        //Ativa acao. Se a opcao for clicada faz a acao do Intent
        //
        opcVisualizarEndereco.setIntent( intentVisualizarEnderecoNoMapa );









        //Adicionando uma opcoao no ContextMenu
        //
        MenuItem opcVisitarSite = menu.add("Visitar Site");


        //Criando a funcao da opcao "Visitar site" - Jeito 1 de fazer isso
        //Crio um intent para fazer uma acao--->(acao a ser realizada.visualizar algo)
        Intent   intentVaiParaSite  = new Intent( Intent.ACTION_VIEW );


        String enderecoDoSite = alunoRecebidoDoContextMenu.getSite();

        if(! enderecoDoSite.startsWith("https://") ){

            enderecoDoSite = "https://" + enderecoDoSite;
        }


        //Definindo que sera visualizado, pois poder ser ite, imagem, etc)
        //O comando ".setData" serve para passar um parametro dentro da Intent
        //---------------------->(Objeto do tipo identificador de recurso)
        intentVaiParaSite.setData( Uri.parse(enderecoDoSite) );


        //Ativa acao. Se a opcao for clicada faz a acao do Intent
        //
        opcVisitarSite.setIntent(intentVaiParaSite );








        //Adicionando uma opcoes no ContextMenu
        //
        MenuItem opcDeletar     = menu.add("Deletar");

        //Definindo a funcao da opcao "Deletar"
        //Criando a funcao da opcao "opcDeletar" - Jeito 2 de fazer isso
            opcDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

                @Override
                public boolean onMenuItemClick (MenuItem item){




                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta( alunoRecebidoDoContextMenu );
                dao.close();


                Toast.makeText(ListaAlunosActivity.this, "Aluno deletado :)", Toast.LENGTH_LONG).show();


                //Atualiza a lista que esta sendo exibida na tela
                //
                carregaLista();


                return false;
            }
             }
              );



    }//onCreateContextMenu



    @Override
    //Toda vez que o usuario aceita ou nega uma opcao de permissao, o android chama esse metodo
    //dessa forma eu posso criar alguma acao adicional aqui!
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Mandar um Toast se o usuario negar ou aceitar a permissao de ligacao
        //--> Request code da solicitacao de chamada telefonica da opcRealizarChamada
        //
        if( requestCode == 100) {

            Toast.makeText(ListaAlunosActivity.this, "Obrigado por responder a solicitacao de permisao :)", Toast.LENGTH_SHORT).show();

        }


    }//onRequestPermissionsResult







}//ListaAlunosActivity
