package com.example.android.overflowmenuexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override // Inflate the menu; this adds items to the action bar if it is present.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override//Metodo executado toda vez que o usuario selecionar uma opcao na barra de menu superior
    public boolean onOptionsItemSelected(MenuItem item) { //Recebo o item da opção escolhida

        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.main_view); //crio uma variavel e associo com o layout
        ImageView      erikImage = (ImageView)      findViewById(R.id.imageView1);


        switch (item.getItemId()){ //pego o id do item que foi escolhido na tela

            //***
            case R.id.menu_blue:
                if(item.isChecked()){ //Isso aqui eh se o usurio deselecionar o item. Ou seja, se o item ja estiver selecionado e o usuario quiser desmarcar
                    item.setChecked(false);}

                else{
                    item.setChecked(true);
                    main_view.setBackgroundColor(Color.BLUE);
                    erikImage.setImageResource(R.drawable.erikblue);
                    return true;}

            //***
            case R.id.menu_yellow:
                if(item.isChecked()){
                    item.setChecked(false);}

                else{
                    item.setChecked(true);
                    main_view.setBackgroundColor(Color.YELLOW);
                    erikImage.setImageResource(R.drawable.erikyellow);
                    return true;}

            //***
            case R.id.menu_green:
                if(item.isChecked()){
                    item.setChecked(false);}

                else{
                    item.setChecked(true);
                    main_view.setBackgroundColor(Color.GREEN);
                    erikImage.setImageResource(R.drawable.erikgreen);
                    return true;}

            //***
            default:
                return super.onOptionsItemSelected(item);//NAO ENTENDI ESSA LINHA

        }//switch


    }
}

