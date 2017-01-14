package com.example.android.agenda;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.android.agenda.modelo.Prova;
import java.util.Arrays;
import java.util.List;




public class ListaProvasFragment extends Fragment {



    //Metodo chamado pelo android para construir o Fragment
    //Eh praticamento o onCreate() de um Fragment
    //Os parametros desse metodo sao passados pelo android
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {




        //Crio uma view que vai ser pega e colocada dentro do XMl que o Fragment vai ser colcado (pois o fragment sempre vai estar dentro de uma tela, afinal eh pra isso que um fragment serve rs)
        //------------------------->( layout que vou usar, onde vou colocar o fragment, false = nao infla de imediato )
        View view = inflater.inflate( R.layout.fragment_lista_provas, container, false );




        //Criando um obj do tipo List
        List<String> topicosPort = Arrays.asList( "Sujeito", "Objeto Direto","Outro topico " );
        Prova provaPortugues     = new Prova( "Portugues", "25/05/2016", topicosPort );



        List<String> topicosMat = Arrays.asList( "Algebra", "Trigonometria" );
        Prova provaMatematica     = new Prova( "Matematica", "27/05/2016", topicosMat );



        List<Prova> provas = Arrays.asList( provaPortugues, provaMatematica);

        //------------------------------------------------------>Esse layout eh do tipo simples que o android disponibiliza, ele pega o retorno do metodo toString() do objeto que esta sendo passado
        //----------------------------------------------O Frament nao eh considerado uma tela por isso toda vez que preciso dizem em qual tela estou uso o metodo getContext() e ja era
        ArrayAdapter<Prova> adapter = new ArrayAdapter<> ( getContext(), android.R.layout.simple_list_item_1, provas);



        //Obs: Fragments nao tem findViewById, e no lugar dissso os Fragments tem o metodo view.findViewById()
        ListView lista = (ListView) view.findViewById( R.id.provas_lista);
        lista.setAdapter( adapter );




        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //-------------------->(Objeto pai dos subItem que foi clicado, subitem que fui clicado, posicao do subItem clicado, id no subItem clicado)      )
            public void onItemClick( AdapterView<?> parent, View item, int position, long id ) {



                Prova prova = (Prova) parent.getItemAtPosition ( position);


                Toast.makeText( getContext() , "Clicou na prova de " +prova, Toast.LENGTH_LONG ).show();


                //Vou avisar a activity que estou que quero colocar um novo fragment no lugar do 'frameLayout' que esta na tela
                ProvasActivity provasActivity = ( ProvasActivity ) getActivity();


                //Esse metodo esta na classe "ProvasActivity"
                //Faz a troca do fragment que esta no frameLayut da tela ProvasActivity
                provasActivity.selecionaProva( prova );



            }
        });


        //Retorno a view que construi para o android, afinal eh o android quem chama o onCreatView
        return view;


    }//onCreateView








}//class
