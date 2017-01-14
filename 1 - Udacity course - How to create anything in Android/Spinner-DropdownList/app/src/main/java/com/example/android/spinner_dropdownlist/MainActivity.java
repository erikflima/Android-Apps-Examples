package com.example.android.spinner_dropdownlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spnr;

    String[] countriesList = {
            "Australia",
            "Canada",
            "China",
            "Japan",
            "United States of America",
            "Ireland",
            "South Africa"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associa o spinner ao objeto da tela
        spnr = (Spinner) findViewById(R.id.spinner);

        //Criando um objeto ArrayAdpter e dizendo que ele sera um simple spinner item com os nomes dos paises que passei
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countriesList);

        //Setting o objeto com as adptacoes que descrevi
        spnr.setAdapter(adapter);

        //Deixo um Listener para agir se algum item do spinner for selecionado
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition(); //Pegando a posicao do item selecionado no spinner

                        Toast.makeText(getApplicationContext(), "You have selected " + countriesList[+position], Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override //Obrigatoriamente esse metodo precisa estar aqui, se nao da erro :/
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                }
        );


    }//onCreate
}//main
