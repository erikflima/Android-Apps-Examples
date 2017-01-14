package com.example.android.onlongclickexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class LongPress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_press);


        TextView txtView = (TextView) findViewById(R.id.txtView);

        //Se ocorrer um onLong click faca...
        txtView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "You have pressed it long :)", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //Se ocorrer um normal click faca...
        txtView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not Long Enough :(", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
