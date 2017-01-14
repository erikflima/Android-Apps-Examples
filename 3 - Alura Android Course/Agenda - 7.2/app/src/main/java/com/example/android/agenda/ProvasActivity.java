package com.example.android.agenda;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.android.agenda.modelo.Prova;




public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_provas );


        //Criando um objeto do tipo "FragmentManager" para poder manipular e colocar os Framents dentro dessa tela
        //Devo spre usar o metodo "getSupportFragmentManager()" na hora de criar um obj "FragmentManager", pois assim mantenho a compatibilidade com versoes anteriores do android
        FragmentManager fragmentManager = getSupportFragmentManager();


        //Agora eu tenho que substituir o espaco que esta sendo ocupado pelo "FrameLayout" e substitui-lo pelo "Fragment" que eu quero
        //Mas pra fazer isso, eh preciso fazer uma "Transacao" com o obj fragmentManager que criei.
        //Essa transacao eh dizer literalmente o que eu quero fazer na tela e depois precisa commitar a transacao
        //Ai uso o metodo 'beginTransaction()' que retorna uma transacao vazia
        //Doidera :0
        FragmentTransaction tx = fragmentManager.beginTransaction();



        //Agora sim! Vou pedir para substituir o "FrameLayout" pelo "Fragment" que quero. So a transacao pode fazer isso
        //------->( o que quero substituir, pelo o que sera substituido )
        tx.replace(R.id.frame_principal, new ListaProvasFragment());


        //Verifico em qual posicao o smartphone. Pois se tiver, ai eu preciso colocar o outro 'Fragment'
        //Se estiver na posicao horizontal, entao
        if( estaNoModoPaisagem() ) {

            //Colocando o segundo "Fragment" na tela
            //------->( o que quero substituir, pelo o que sera substituido )
            tx.replace( R.id.frame_secundario, new DetalhesProvaFragment() );

        }//if




        //Comportamento do botao "back" do device
        ///Esse comando adiciona a transacao na pilha de telas do botao back d device
        //Ou seja, se o botao back for pressionado, a mesma tela ser apresentada sem as alteracoes que a transacao fez, como um "rollback"
        tx.addToBackStack( null );







        //Por fim, por se tratar de uma transaction, eh necessario o commit pra funcionar
        tx.commit();





    }//onCreate






    //Verifica qual a orientacao que o smartPhone esta
    private boolean estaNoModoPaisagem() {

        return getResources().getBoolean( R.bool.modoPaisagem );

    }//estaNoModoPaisagem







    //Faz a troca do fragment que esta no frameLayut da tela ProvasActivity
    public void selecionaProva( Prova prova ) {



        //Criando um objeto do tipo "FragmentManager" para poder manipular e colocar os Framents dentro dessa tela
        FragmentManager manager = getSupportFragmentManager();



        //Se o device nao estiver no modo paisagem
        if ( !estaNoModoPaisagem() ) {



            //Agora eu tenho que substituir o espaco que esta sendo ocupado pelo "FrameLayout" e substitui-lo pelo "Fragment" que eu quero
            //Mas pra fazer isso, eh preciso fazer uma "Transacao" com o obj fragmentManager que criei.
            //Essa transacao eh dizer literalmente o que eu quero fazer na tela e depois precisa commitar a transacao
            //Ai uso o metodo 'beginTransaction()' que retorna uma transacao vazia
            //Doidera :0
            FragmentTransaction tx = manager.beginTransaction();


            DetalhesProvaFragment detalhesFragment = new DetalhesProvaFragment();


            //Objeto Bundle eh uma caixa onde um posso colocar dados para trafegar
            Bundle parametros = new Bundle();



            //Anexando o objeto do tipo "Prova" que recebi
            parametros.putSerializable( "prova", prova );



            detalhesFragment.setArguments( parametros );



            //Agora sim! Vou pedir para substituir o "FrameLayout" pelo "Fragment" que quero. So a transacao pode fazer isso
            //------->( o que quero substituir, pelo o que sera substituido )
            tx.replace( R.id.frame_principal, detalhesFragment  );


            //Por fim, por se tratar de uma transaction, eh necessario o commit pra funcionar
            tx.commit();


        }//if
        else{

            //Se o device estiver no modo paisagem, ai eu vou querer apenas popular o 'Fragment de detalhe', mas preciso fazer o findFragmentById
            DetalhesProvaFragment detalhesFragment = (DetalhesProvaFragment) manager.findFragmentById( R.id.frame_secundario );


            //Populo os campos da tela do Fragment
            detalhesFragment.populaCamposCom( prova );



        }//else



    }//selecionaProva







}//class
