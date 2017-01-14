package com.appserik.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BottomPictureFragment extends Fragment {



    private static TextView topMemeText;
    private static TextView bottomMemeText;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Dizendo com vai ser o layout que o Fragment vai usar
        View view = inflater.inflate( R.layout.bottom_picture_fragment, container, false);


        topMemeText     = (TextView)view.findViewById(R.id.textView1);
        bottomMemeText  = (TextView)view.findViewById(R.id.textView2);


        return view;

    }//onCreateView



    //Metodo que muda o texto dos TextViews do Fragment da tela
    public void setMemeText ( String top, String bottom){

        topMemeText.setText( top );
        bottomMemeText.setText( bottom );

    }//setMemeText





}//class
