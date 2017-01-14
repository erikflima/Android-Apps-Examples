package com.example.android.agenda;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;




public class MapaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);



        //Criando um objeto do tipo "FragmentManager" para poder manipular e colocar os Framents dentro dessa tela
        //Devo sempre usar o metodo "getSupportFragmentManager()" na hora de criar um obj "FragmentManager", pois assim mantenho a compatibilidade com versoes anteriores do android
        FragmentManager manager = getSupportFragmentManager();





        //Agora eu tenho que substituir o espaco que esta sendo ocupado pelo "FrameLayout" e substitui-lo pelo "Fragment" que eu quero
        //Mas pra fazer isso, eh preciso fazer uma "Transacao" com o obj fragmentManager que criei.
        //Essa transacao eh dizer literalmente o que eu quero fazer na tela e depois precisa commitar a transacao
        //Ai uso o metodo 'beginTransaction()' que retorna uma transacao vazia
        //Doidera :0
        FragmentTransaction tx = manager.beginTransaction();





        //Agora sim! Vou pedir para substituir o "FrameLayout" pelo "Fragment" que quero. So a transacao pode fazer isso
        //------->( o que quero substituir, o mapa que criei  )
        tx.replace( R.id.frame_mapa, new MapaFragment() );


        //OBS: Chamada do mapa normal sem nenhum comportamento. DEIXEI AQUI SO PRA NAO ESQUECER
        //------->( o que quero substituir, fragment que quero colocar no lugar - nesse caso "new SupportMapFragment()" ja eh um fragment dado pelo proprio android que ja tem as funcionalidades do mapa  )
        //tx.replace( R.id.frame_mapa, new SupportMapFragment() );


        //Por fim, por se tratar de uma transaction, eh necessario o commit pra funcionar
        tx.commit();



    }//onCreate



}//class
