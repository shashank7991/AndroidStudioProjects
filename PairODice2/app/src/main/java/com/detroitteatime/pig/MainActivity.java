package com.detroitteatime.pig;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;

    int player1Score, player2Score, round;
    int currentPlayerNumber;
    Context context;

    TextView txtCurrentPlayer, txtPlayer1Score, txtPlayer2Score, txtRound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePlayer();
            }
        });


        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

        txtCurrentPlayer = (TextView) findViewById(R.id.player);
        txtPlayer1Score = (TextView) findViewById(R.id.p1);
        txtPlayer2Score = (TextView) findViewById(R.id.p2);
        txtRound = (TextView) findViewById(R.id.round);
        txtCurrentPlayer.setText("Player 1");

        Intent intent = getIntent();
        player1Score = intent.getIntExtra("player1score", 0);
        player2Score = intent.getIntExtra("player2score", 0);
        round = intent.getIntExtra("round", 0);

        txtRound.setText("Round : 0");
        txtPlayer1Score.setText("P1 : " + player1Score);
        txtPlayer2Score.setText("P2 : " + player2Score);


    }


    void resetValues(){

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());

        if(val1 == 1 || val2 == 1){
            changePlayer();
        } else{
            round += val1 + val2;
            txtRound.setText("Round : " + round);
            player1Score += val1 + val2;
            txtPlayer1Score.setText("P1 : " + player1Score);
            if(player1Score >= 100){
                winningAlert();
            }

        }
        setDie(val1, die1);
        setDie(val2, die2);
    }

    void winningAlert(){

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Player 1 Won!");
        alertDialog.setMessage("Yipeeaieahhh!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        resetValues();
                    }
                });

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
    void changePlayer(){

        Intent intent = new Intent(context , Player2.class);
        intent.putExtra("player1score", player1Score);
        intent.putExtra("player2score", player2Score);
        startActivity(intent);
    }
    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}