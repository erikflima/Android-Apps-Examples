package com.example.android.draganddropexample;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.View.DragShadowBuilder;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ativando o ouvinte de touch do objeto do textView que esta na tela
        findViewById(R.id.textView1)   .setOnTouchListener(this);

        //Ativando o ouvinte de Drag.
        findViewById(R.id.pinkLayout)  .setOnDragListener (this);
        findViewById(R.id.yellowLayout).setOnDragListener (this);

    }


    //Executar quando o objeto da tela for tocado
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                view.startDrag(null, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE); //Tira a sombra do item que esta sendo arrastado pela tela
                return true;
        }
        else {
                return false;
        }
    }

    //Executar quando ocorrer o drag
    public boolean onDrag(View layoutview, DragEvent dragevent) {

        int action = dragevent.getAction();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.v("MainActivity", "Drag event started");
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.v("MainActivity","Drag event entered into "+layoutview.toString());
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.v("MainActivity", "Drag event exited from "+layoutview.toString());
                break;

            case DragEvent.ACTION_DROP:
                Log.v("MainActivity", "Dropped");

                View view       = (View)      dragevent.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();

                owner.removeView(view);
                LinearLayout container = (LinearLayout) layoutview;
                container.addView(view);
                view.setVisibility(View.VISIBLE); //Deixa o item que esta sendo arrastado pela tela visivel
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.v("MainActivity", "Drag ended");
                break;

            default:
                break;
        }
        return true;
    }//onDrag
}//MainActivity