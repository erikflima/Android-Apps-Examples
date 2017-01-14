package com.appserik.android.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class TopSectionFragment extends Fragment {


    private static EditText topTextInput;
    private static EditText bottomTextInput;



    //Criando um interface onde os metodos que estao dentro dela deveram obrigatoriamente ser implementados pela classe de que der um "implement TopSectionFragment"
    public interface InterfaceTopSectionListener{

        //Criando um metodo que tem que ser obrigatiamente implementado na classe que der um "implement TopSectionFragment"
        public void alterarTextoDoFragment2 ( String top, String bottom );

    }//TopSectionListener




    //Criando um conector com a MainActivity
    //Assim eh possivel chamar metodos implementados que estao na MainActivity
    InterfaceTopSectionListener activityCommander;



    //Cria uma coneccao com a interface
    //Ou seja, isso permite usar o metodo que implementado dessa classe (alterarTextoDoFragment2), mas que esta na "MainActivity"
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try{
            //Dizendo o contet para a variavel activityCommander
            activityCommander = ( InterfaceTopSectionListener ) context;
        }//try
        catch (ClassCastException e){
            throw new ClassCastException( context.toString() );
        }

    }//onAttach





    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Dizendo com vai ser o layout que o Fragment vai usar
        View view = inflater.inflate( R.layout.top_section_fragment, container, false);



        topTextInput        = (EditText)view.findViewById(R.id.topTextInput);
        bottomTextInput     = (EditText)view.findViewById(R.id.bottomTextInput);;

        final Button button = (Button) view.findViewById(R.id.button);



        //Funcao do botao
        button.setOnClickListener( new View.OnClickListener(){
                                                public void onClick (View v){
                                                                buttonCliked(v);
                                                                                }});
        return view;

    }//onCreateView




   //Funcao do botao
    public void buttonCliked( View view){

        //IMPORTANTE PARA O ENTENDIMENTO!
        //Chamo o metodo "alterarTextoDoFragment2" que esta na "MainActivity"
        //e passo os textos digitados nos EditTexts que estao na tela do fragment1
        //Esse "activityCommander" eh so pra linkar com o metodo "alterarTextoDoFragment2" que esta na "MainActivity"
        activityCommander.alterarTextoDoFragment2( topTextInput.getText().toString(),  bottomTextInput.getText().toString() );

    }//buttonCliked





}//class
