package com.example.erik.teste_caculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Referencias globais

    EditText    editText1;
    EditText    editText2;
    Button      btn;
    RadioButton rbAdd;
    RadioButton rbSub;
    RadioGroup  radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Referenciando as views da tela
        editText1     = (EditText)   findViewById  ( R.id.editText1 );
        editText2     = (EditText)   findViewById  ( R.id.editText2 );
        btn           = (Button)     findViewById  ( R.id.button    );
        radioGroup    = (RadioGroup) findViewById  ( R.id.radioGroup);





        //Editando o ouvnte do Radio
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                  @Override // Mudando a funcao ja existente para deixar do jeito que quero
                  public void onCheckedChanged(RadioGroup group, int checkedId) {

                      RadioButton rb1  = (RadioButton) group.findViewById(checkedId);


                      rbAdd  = (RadioButton) group.findViewById( R.id.radioAdicao   );
                      rbSub  = (RadioButton) group.findViewById( R.id.radioSubtracao);


                      if ( rbAdd != null) {
                          Toast.makeText(MainActivity.this, "Operação adição selecionada :)", Toast.LENGTH_SHORT).show();
                      }
                      else {

                          Toast.makeText(MainActivity.this, "Operação subtração selecionada :)", Toast.LENGTH_SHORT).show();

                      }//else



                  }//onCheckedChanged
              }//setOnCheckedChangeListener
        );








        //Funcao do botao para adicionar um novo contato na agenda
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //Validando os editTexts na tela//

                //if ( editText1.getText().toString() == "") {
                if(editText1.getText().length() == 0 || editText2.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Preencha os campos antes de efetuar a operação :(", Toast.LENGTH_SHORT).show();

                }


                else {

                    //Pegando a String que foi digitada nos EditTexts e convertendo para um numero inteiro
                    int valorEditText1 = Integer.parseInt(editText1.getText().toString());
                    int valorEditText2 = Integer.parseInt(editText2.getText().toString());
                    int resultado;





                    if ( rbAdd.isChecked() ) {

                        resultado = valorEditText1 + valorEditText2;
                    }
                    else {

                        resultado = valorEditText1 - valorEditText2;

                    }//else




                    //Parametros do intent------------------->(Em tela estou, Para qual tela eu vou)
                    Intent IntentVaiParaResultado = new Intent(MainActivity.this, Resultado.class);




                    //Cria um bundle para passar os dados para a outra Activity
                    //Bundle  b     = new Bundle();
                    //Integer total = Integer.parseInt(total.toString());
                    //b.putInt(total);
                    IntentVaiParaResultado.putExtra("total", resultado );



                    startActivity(IntentVaiParaResultado);



                }//else


                }
            });




    }//onCreate





}//class
