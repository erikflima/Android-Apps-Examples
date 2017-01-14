package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    //Esse metodo eh realizado no momento que a app funciona
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Add 1 point more to team A.
     */
    public void add1(View v) {
        score = score + 1;
        displayForTeamA(score);
    }

    /**
     * Add 2 points more to team A.
     */
    public void add2(View v) {
        score = score + 2;
        displayForTeamA(score);
    }

    /**
     * Add 3 points more to team A.
     */
    public void add3(View v) {
        score = score + 3;
        displayForTeamA(score);
    }


    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));

    }




}
