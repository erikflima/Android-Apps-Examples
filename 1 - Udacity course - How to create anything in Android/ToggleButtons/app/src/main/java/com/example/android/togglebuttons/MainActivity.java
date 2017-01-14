package com.example.android.togglebuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    private ToggleButton VarToggleButton1;
    private ToggleButton VarToggleButton2;
    private Button       VarBtnDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addListenerOnButton();//Deixa preparado o metodo addListenerOnButton para caso o Toggle button da tela seja pressionado
    }


    public void addListenerOnButton() {

        VarToggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1); //Associando a variavel ao botao que esta na tela
        VarToggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2); //Associando a variavel ao botao que esta na tela
        VarBtnDisplay    = (Button)       findViewById(R.id.btnDisplay);    //Associando a variavel ao botao que esta na tela

        VarBtnDisplay.setOnClickListener(new View.OnClickListener() { //Se o botao "Display" for pressionado, faca..

            public void onClick(View v) {

                String textToggleButton1 = (String) VarToggleButton1.getText();
                String textToggleButton2 = (String) VarToggleButton2.getText();
                String textToggleButton3 = "\nStatus ToggleButton1 : " + textToggleButton1 + "\nStatus ToggleButton2 : " +textToggleButton2;

                Toast.makeText(MainActivity.this, textToggleButton3 , Toast.LENGTH_SHORT).show(); //Mostra os textos dos Toggle Buttons


              /** Outra forma de pegar os textos dos toggle Buttons e exibir no toast
                StringBuffer result = new StringBuffer();
                result.append("ToggleButton1 : ")  .append(VarToggleButton1.getText()); //Pega o texto que esta no Toggle Button
                result.append("\nToggleButton2 : ").append(VarToggleButton2.getText()); //Pega o texto que esta no Toggle Button

                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show(); //Mostra os textos dos Toggle Buttons
              **/

            }//onClick
        }//;setOnClickListener

        );

    }//addListenerOnButton


}//main
