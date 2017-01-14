package com.appserik.android.leitorderss;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class MyAdapter extends RecyclerView.Adapter< MyAdapter.MyViewHolder >{

    //Context local
    Context context;

    //Array de feedItems que local
    ArrayList<FeedItem> feedItems;




    //Construtor
    public MyAdapter( Context contextRecebido, ArrayList<FeedItem> feedItemsRecebido){

        //Passando o que foi recebido para os objetos locais
        this.feedItems = feedItemsRecebido;
        this.context   = contextRecebido;


    }//MyAdapter



    //----------------Os metodos abaixo sao chamados pelo android quando usar o adapter------------//


    //1° - Android pergunta: Quantos itens tem para serem montados na lista?
    //   - Descricao do metodo: Esse metodo diz quantos itens vao ser colocados dentro da recyclerView na tela (ou seja, a lista que vai aparecer de itens que vai aparecer na tela)
    @Override
    public int getItemCount() {

        return feedItems.size();

    }//getItemCount







    //2° - Android pergunta: Qual layout vc quer usar para montar os itens da lista?
    //   - Descricao do metodo: Crio uma view qua vai ser os quadrados de cada item
    //   - Digo qual vai ser o layout xml do quadrado

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {

        //Crio uma view que eh na verdade um quadrado na tela
        //Ai digo qual vai ser o layout xml que esse quadrado vai usar
        View view = LayoutInflater.from( context ).inflate( R.layout.custum_row_news_item, parent, false );





        //---Complicado e entender, atencao--//
        //Na linha acima, eu disse que vou usar o layout custum_row_news_item.xml
        //Agora preciso dizer de onde vem os dados para preencher cada ViewChildrem que esta dentro do custum_row_news_item.xml
        //Ai eu usei eu criei um classe como nome 'MyViewHolder' que simplesmente faz 'findViewById" dos itens que do layout custum_row_news_item.xml
        //Ai sim depois de tudo isso, eu posso pegar o texto que esta no array de "feedItems" e passar isso para o campo para mostrar na tela
        MyViewHolder holder = new MyViewHolder( view );



        return holder;

    }//onCreateViewHolder





    //Criando uma nova classe para auxilio
    //Esse obj o serve para fazer os findViewById de uma forma mais organizada
    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView  Title;
        TextView  Description;
        TextView  Date;
        ImageView Thumbnail;

        CardView  cardView;


        //Construtor
        public MyViewHolder( View itemView ) {
            super( itemView );



            Title       = ( TextView ) itemView.findViewById( R.id.title_text      );
            Description = ( TextView ) itemView.findViewById( R.id.decription_test );
            Date        = ( TextView ) itemView.findViewById( R.id.date_text       );
            Thumbnail   = ( ImageView) itemView.findViewById( R.id.thumb_img       );

            cardView    = (CardView)   itemView.findViewById( R.id.cardview        );


        }//MyViewHolder

    }//MyViewHolder








    //3° - Android pergunta: Qual o conteudo que vc quer exibir nas viewChild do layout  de cada item da lista
    //   - Descricao do metodo: Aqui aponto qual qual o conteudo que vai ser exibido nas views childs de cada item da lista
    @Override
    public void onBindViewHolder( MyViewHolder holder, int position ) {


        //Adicionando uma animacao do cardView que veio da dependencia que peguei no no link "https://github.com/daimajia/AndroidViewAnimations"
       //------->( Tecniques.nome do efeito ) Lembrando que da pra trocar o efeito
        YoYo.with( Techniques.RubberBand ).playOn( holder.cardView );



        //O android me deu a posicao que ele vai montar, entao eu digo qual posicao do vetor que eu vou devolver os dados dos atributos
        final FeedItem current = feedItems.get( position );


        //Aqui eu passo o conteudo de cada item da lista
        holder.Title      .setText( current.getTitle()       );
        holder.Description.setText( current.getDescription() );
        holder.Date       .setText( current.getPubDate()     );

        //
        Picasso.with( context ).load( current.getThumbnailUrl() ).into( holder.Thumbnail );


        //Descrevendo o evento do clique em cima de um item
        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {


                //Vai para a tela de Details (que eh uma webView) do item clicado na lista e passa o link da pagina que vai ser aberta na webView
                Intent intent = new Intent ( context, NewsDetails.class );
                intent.putExtra( "Link", current.getLink() );
                context.startActivity( intent );


            }
          }
        );


    }//onBindViewHolder







}//class
