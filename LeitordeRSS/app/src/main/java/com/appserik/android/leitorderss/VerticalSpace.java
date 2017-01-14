package com.appserik.android.leitorderss;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class VerticalSpace extends RecyclerView.ItemDecoration{

    int Space;



    //Constructor
    public VerticalSpace( int space ) {

        //Passando o valor para a variavel local
        this.Space = space;

    }//Constructor





    @Override
    public void getItemOffsets( Rect outRect, View view, RecyclerView parent, RecyclerView.State state ) {


        outRect.left   = Space;
        outRect.bottom = Space;
        outRect.right  = Space;


        if( parent.getChildLayoutPosition( view ) == 0 ) {

            outRect.top  = Space;

        }//if


    }//getItemOffsets




}//class
