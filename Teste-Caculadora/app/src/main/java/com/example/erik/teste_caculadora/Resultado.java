package com.example.erik.teste_caculadora;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Resultado extends AppCompatActivity {


    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        //Pegando a Intent enviada pela tela ListaAlunosActivty
        Intent intentRecebido = getIntent();

        int a = (int) intentRecebido.getSerializableExtra( "total" );


        total     = (TextView)   findViewById  ( R.id.total );
        total.setText( Integer.toString(a) );




        Button btn   = (Button)     findViewById  ( R.id.button    );




        //Funcao do botao para adicionar um novo contato na agenda
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                Intent IntentVaiParaMain = new Intent( Resultado.this, MainActivity.class );
                startActivity(IntentVaiParaMain);
            }
        });
    }
}
