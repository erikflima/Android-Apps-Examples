package com.example.android.mediaplayerdemonstration;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mysound;
    boolean     playing = false;
    ImageView   imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mysound = MediaPlayer.create(this, R.raw.rap);
    }

    //Esse metodo eh executado toda vez que o botao na tela for clicado
    public void playMusic(View view) {

        if (playing == false)
            {
                playing = true;
                mysound.start();
                ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                imageView2.setImageResource(R.drawable.figurepause);
            }
        else
            {
                playing = false;
                mysound.pause();
                ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                imageView2.setImageResource(R.drawable.figureplay);
            }
    }

    @Override
    //Se sair se a janela do aplicativo for fechada, a musica vai parar de tocar
    protected void onPause() {
        super.onPause();
        mysound.release();


    }
}
