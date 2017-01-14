package com.example.android.agenda.meusAdapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.agenda.ListaAlunosActivity;
import com.example.android.agenda.R;
import com.example.android.agenda.modelo.Aluno;

import java.util.List;

//BaseAdapter eh uma classe abstrata, e pede a implementacao de metodos obrigatorios
public class AlunosAdapter extends BaseAdapter{


    //Lista auxiliar
    private final List<Aluno> auxListDeAlunos;

    //Referencia da tela que criou um objeto alunosAdapter
    private final Context     auxContext;



    //Constructor
    //----------------->(Content eh a classe pai de todas as views)
    //public class ListaAlunosActivity extends AppCompatActivity {

        public AlunosAdapter(Context telaRecebida, List<Aluno> listaRecebidaDeAlunos) {

        this.auxContext      = telaRecebida;
        this.auxListDeAlunos = listaRecebidaDeAlunos;
    }




    @Override
    //Primero metodo que eh chamado pela "ListView" que est usando um "AlunosAdapter"
    //Esse metodo diz quantos itens existem para ser exibidos
    public int getCount() {

        return auxListDeAlunos.size();
    }



    @Override
    //Segundo metodo que eh chamado pela "ListView" que est usando um "AlunosAdapter"
    //Esse metodo devolve o que sera exibido a cada linha da listView que esta na tela
    //Ou seja, a listView dizer: "manda ai o conteudo a ser exibido em cada linha!"
    public View getView( int position, View convertView, ViewGroup parent) {



        //Pegando o aluno da posicao que o ListView esta pedindo
        //
        Aluno aluno = auxListDeAlunos.get( position );

        //Criando um objeto do tipo LayoutInflater
        //
        LayoutInflater inflater = LayoutInflater.from(auxContext);

        //Inflando o ListView da tela com o layout xml que fiz
        //------------------------->( xml qe sera colocado na tela, digo que a View pai eh realmente
        //                           a que esta no XML - mais detalhes no video 3.3[min 12:55] sobre
        //                           3. Melhorando o visual da lista, false: nao inflar automaticamente
        //                            e sim pegar o paramentro que estou passsado - explicao melhor no video )
        View view = inflater.inflate(R.layout.list_item, parent, false);


        //Associando os campos do XML
        //
        TextView campoNomeDoXML  = (TextView) view.findViewById(R.id.item_nome);
        campoNomeDoXML.setText(aluno.getNome());

        //Associando os campos do XML
        //
        TextView campoTelefoneDoXML  = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefoneDoXML.setText(aluno.getTelefone());


        //Associando os campos do XML
        //
        ImageView campoFotoDoXML  = (ImageView) view.findViewById(R.id.item_foto);

        String caminhoDaFoto = aluno.getCaminhoFotoDoAluno();

        if ( caminhoDaFoto != null) {

            //Pegando a foto que esta guardada, decodificando e convertendo para Bitmap
            //
            Bitmap bitmapRecebido = BitmapFactory.decodeFile(caminhoDaFoto);


            //Reduzindo a resolucao para colocar no imageView da tela
            //------------------------------------------------>(imagem, tamannho desejado, passar filtro para melhorar a qualidade da imagem )
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmapRecebido, 50, 50, true);


            //Coloco a imagem em Bitmap e finalmente jogo na dentro no imageView da tela
            //
            campoFotoDoXML.setImageBitmap( bitmapReduzido );


            //Ajustar a imagem estendendo ela
            //
            campoFotoDoXML.setScaleType(ImageView.ScaleType.FIT_XY);

        }//if


        return view;




/** ----Codigo anterior de exemplo - Nao apaguei so pra deixar eu mesmo ver futuramente e lembrar o que tinha aprendido!
        //Nao entendi mais fmz...
        //-------------------------->( Tela que foi recebida)
        TextView view = new TextView ( auxContext );

        //Pegando o aluno da posicao que o ListView esta pedindo
        Aluno aluno = auxListDeAlunos.get( position );

        //Pegar o resultado do metodo "toString()"
        view.setText( aluno.toString() );

        return view;
**/

    }//getView




    @Override
    //getItemAtPosition()
    //Esse metodo aqui eh quando eu precisar usar o metodo "getItemAtPosition()"
    // que eh chamado da  ListView.
    public Object getItem(int position) {

        //Devolve o objeto inteiro da posicao correspondente
        return auxListDeAlunos.get( position );
    }


    @Override
    //Esse metodo aqui eh quando eu precisar usar o metodo "onItemClick()"
    // que eh chamado da  ListView. Entao eu devolvo uma chave
    public long getItemId(int position) {

        return auxListDeAlunos.get(position).getId();
    }







}//AlunosAdapter
