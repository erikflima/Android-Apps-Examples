package com.example.android.courtcounterv2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;


    //Esse metodo eh realizado no momento que a app funciona
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

//--------------------A--------------------------//
    /**
     * Add 1 point more to team A.
     */
    public void add1ToA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Add 2 points more to team A.
     */
    public void add2ToA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Add 3 points more to team A.
     */
    public void add3ToA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

//--------------------B--------------------------//

    /**
     * Add 1 point more to team B.
     */
    public void add1ToB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Add 2 points more to team B.
     */
    public void add2ToB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Add 3 points more to team B.
     */
    public void add3ToB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

//--------------------Display--------------------------//

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    //--------------------Reset--------------------------//

    public void resetScore(View v){
        int scoreZero = 0;

        displayForTeamA(scoreZero);
        displayForTeamB(scoreZero);

        scoreTeamA = 0;
        scoreTeamB = 0;
    }



}//MainActivity