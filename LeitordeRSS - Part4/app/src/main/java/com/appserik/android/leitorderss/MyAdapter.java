package com.appserik.android.leitorderss;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;



public class MyAdapter extends RecyclerView.Adapter< MyAdapter.MyViewHolder >{


    Context context;

    ArrayList<FeedItem> feedItems;




    //Construtor
    public MyAdapter( Context contextRecebido, ArrayList<FeedItem> feedItemsRecebido){

        //Passando o que foi recebido para os objetos locais
        this.feedItems = feedItemsRecebido;
        this.context   = contextRecebido;


    }//MyAdapter





    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }//MyViewHolder

    }//MyViewHolder




    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {


        //Dizendo qual layout vai ser usado pelo adapter para posicionar os itens
        View view = LayoutInflater.from( context ).inflate( R.layout.custum_row_news_item, parent, false);


        //
        MyViewHolder holder = new MyViewHolder( view );

        return holder;

    }//onCreateViewHolder





    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }//onBindViewHolder






    //Esse metodo diz quantos vai ser apresentados
    @Override
    public int getItemCount() {


        return feedItems.size();

    }//getItemCount





}//class
