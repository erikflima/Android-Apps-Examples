package com.example.android.androidlunchtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int buttonFunction = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Called when the cookie should be eaten.
     */
    public void eatCookie(View view) {

        // Linking the variables with objects in XML

        ImageView imageView = (ImageView) findViewById(R.id.android_cookie_image_view);
        TextView  textView  = (TextView)  findViewById(R.id.status_text_view);
        Button    button    = (Button)    findViewById(R.id.appButton_button);

        if (buttonFunction == 0) {

            buttonFunction ++;

            imageView.setImageResource(R.drawable.after_cookie);
            textView.setText("I'm so full");
            button.setText("Reset");
        }
        else {

            buttonFunction = 0;

            imageView.setImageResource(R.drawable.before_cookie);
            textView.setText("I'm so hungry");
            button.setText("Eat cookie");
        }//else


    }//eatCookie

}//main
