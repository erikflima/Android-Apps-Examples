package com.appserik.android.animationandtransitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {


    ViewGroup layoutPrincipal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        layoutPrincipal = (ViewGroup) findViewById( R.id.layoutPrincipal);


        //SE clicar em algum lugar na tela faca:
        layoutPrincipal.setOnTouchListener( new RelativeLayout.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                moveButton();

                return true;
            }
        });


    }//onCreate




    public void moveButton() {


        Button botaoNaTela =  (Button) findViewById( R.id.botaoNaTela );


        /*-----------Mudando a posicao do botao na tela-----------*/


        //Deixa a animacao da tela lenta. Caso contrario a animacao fica tao rapida que nem da pra ver
        TransitionManager.beginDelayedTransition ( layoutPrincipal );


        //Criando uma variavel que ira conter uma orientacao de posicao
        RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams (
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );


        //Editando regras de posicao da variavel que criei acima
        positionRules.addRule ( RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE );
        positionRules.addRule ( RelativeLayout.ALIGN_PARENT_RIGHT,  RelativeLayout.TRUE );


        //Adicionado regras de posicao ao botao
        botaoNaTela.setLayoutParams( positionRules );




        /*-----------Mudando o tamanho do botao na tela-----------*/


        //Criando uma variavel que vai conter as dimensoes de tamanho do botao
        ViewGroup.LayoutParams sizeRules = botaoNaTela.getLayoutParams();

       //Editando as dimensoes de tamanho da variavel que criei acima
        sizeRules.width  = 450;
        sizeRules.height = 300;

        //Adicionado regras de tamanho do botao
        botaoNaTela.setLayoutParams( sizeRules );






    }//moveButton


}//class
