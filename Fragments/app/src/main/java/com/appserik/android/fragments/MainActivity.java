package com.appserik.android.fragments;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


//------------------------------------------------->Implamentando a interface do fragment1
public class MainActivity extends AppCompatActivity implements TopSectionFragment.InterfaceTopSectionListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate





    //Metodo que veio da interface InterfaceTopSectionListener.
    //Eh obrigatorio implementar esse metodo
    //e esse metodo vai ser chamado pelo botao que esta no fragment1
    @Override
    public void alterarTextoDoFragment2(String textRecebido_top, String textoRecebido_bottom) {

        //Pegando a referencia do Fragment2
        BottomPictureFragment bottomFragment = (BottomPictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        //Passando o texto para o Fragment2
        bottomFragment.setMemeText( textRecebido_top, textoRecebido_bottom );

    }//createMeme








}//class
