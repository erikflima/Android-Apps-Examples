package com.appserik.android.gestures;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {


    private TextView              myText;
    private GestureDetectorCompat gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Texto que esta na tela
        myText = (TextView)findViewById(R.id.textView);


        //Autorizar o objeto a detectar gestos
        this.gestureDetector = new GestureDetectorCompat(this, this);


        //Autoriza o objeto a detectar duplo toque na tela
        gestureDetector.setOnDoubleTapListener( this );


    }//onCreate



    //IMPORTANTE!
    //Esse eh o metodo default do android que eh chamado toda vez que o usuario toca a tela
    //Ai esse metodo captura que tipo de toque o usuario fez na tela.
    //Sendo assim, eu estou pegando esse evento (tipo de toque na tela) e passando para o obj gestureDetector que criei.
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Pegando o tipo de toque que o usuario fez na tela e passando para o obj gestureDetector que criei
        this.gestureDetector.onTouchEvent( event );

        return super.onTouchEvent(event);
    }


    //------------------------------------Metodos de cada toque diferente na tela-----------------//

    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        myText.setText("onSigleTapConfirmed");
        Log.v("MainActivity","*****Gesto: onSigleTapConfirmed");

        return true;
    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onDoubleTap(MotionEvent e) {

        myText.setText("onDoubleTap");
        Log.v("MainActivity","*****Gesto: onDoubleTap");

        return true;
    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        myText.setText("onDoubleTapEvent");
        Log.v("MainActivity","*****Gesto: onDoubleTap");

        return true;
    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onDown(MotionEvent e) {

        myText.setText("onDown");
        Log.v("MainActivity","*****Gesto: onDown");

        return true;
    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public void onShowPress(MotionEvent e) {

        myText.setText("onShowPress");
        Log.v("MainActivity","*****Gesto: onShowPress");

    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        myText.setText("onSingleTapUp");
        Log.v("MainActivity","*****Gesto: onSingleTapUp");

        return true;
    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        myText.setText("onScroll");
        Log.v("MainActivity","*****Gesto: onScroll");

        return true;
    }


    //Metodo obrigatorio da classe GestureDetector
    @Override
    public void onLongPress(MotionEvent e) {

        myText.setText("onLongPress");
        Log.v("MainActivity","*****Gesto: onLongPress");

    }



    //Metodo obrigatorio da classe GestureDetector
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        myText.setText("onFling");
        Log.v("MainActivity","*****Gesto: onFling");

        return true;
    }

    //--------------------------------------------------------------------------------------------//


}
