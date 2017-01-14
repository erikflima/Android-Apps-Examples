package com.example.android.staticautocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lista de nomes que ira aparecer nas ocoes do autoComplete//
        String names[]={"Australia","Canada","China","United States of America","Japan","Ireland"};

        //Associando a varivavel com o objeto da tela
        AutoCompleteTextView t1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        //Criando um objeto ArrayAdpter e dizendo que ele sera um dropdwn item com os nomes que passei
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,names);

        //Oferecer a opcao de auto completar apartir da primeira letra digitada pelo usuario
        t1.setThreshold(1);

        //Adicionando a dropdown item no objeto da tela
        t1.setAdapter(adp);
    }


}
