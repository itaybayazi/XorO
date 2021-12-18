package com.example.xoro;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button [][] buttons = new Button[3][3];
    private boolean player1turn = true;
    private int roundCount;

    private int player1Points;
    private int player2Points;
    private TextView tvplayer1;
    private TextView tvplayer2;
    private TextView turnTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvplayer1=findViewById(R.id.main_tv_p1);
        tvplayer2=findViewById(R.id.main_tv_p2);
        turnTv=findViewById(R.id.main_bt_turn);


        for (int i=0; i<3;i++) {
            for (int j=0; j<3; j++) {
                String buttonID = "main_bt"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }

       Button resetbtn=findViewById(R.id.main_button_reset);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }});

        Button resetGame = findViewById(R.id.main_bt_resetGame);
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvplayer1.setText("Player 1: "+ 0);
                tvplayer2.setText("Player 2: "+ 0);
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals(""))
            return;

        if (player1turn){
            turnTv.setText("player 2 turn");
            ((Button) v).setText("X");

        }else {
            turnTv.setText("player 1 turn");
            ((Button) v).setText("O");
        }
        roundCount++;

        if(checkForWin()){
            closeBoard();

            if(player1turn){
                player1Wins();
            }else {
                player2Wins();
            }
        }else if(roundCount==9){
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        }else {
            player1turn=!player1turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }}

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                buttons[i][0].setBackgroundColor(0XFFFFE715);
                buttons[i][1].setBackgroundColor(0XFFFFE715);
                buttons[i][2].setBackgroundColor(0XFFFFE715);
                buttons[i][0].setTextColor(0xFF070707);
                buttons[i][1].setTextColor(0xFF070707);
                buttons[i][2].setTextColor(0xFF070707);

                return true; }}

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                buttons[0][i].setBackgroundColor(0XFFFFE715);
                buttons[1][i].setBackgroundColor(0XFFFFE715);
                buttons[2][i].setBackgroundColor(0XFFFFE715);
                buttons[0][i].setTextColor(0xFF070707);
                buttons[1][i].setTextColor(0xFF070707);
                buttons[2][i].setTextColor(0xFF070707);
                return true; }}

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            buttons[0][0].setBackgroundColor(0XFFFFE715);
            buttons[1][1].setBackgroundColor(0XFFFFE715);
            buttons[2][2].setBackgroundColor(0XFFFFE715);
            buttons[0][0].setTextColor(0xFF070707);
            buttons[1][1].setTextColor(0xFF070707);
            buttons[2][2].setTextColor(0xFF070707);
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            buttons[0][2].setBackgroundColor(0XFFFFE715);
            buttons[1][1].setBackgroundColor(0XFFFFE715);
            buttons[2][0].setBackgroundColor(0XFFFFE715);
            buttons[0][2].setTextColor(0xFF070707);
            buttons[1][1].setTextColor(0xFF070707);
            buttons[2][0].setTextColor(0xFF070707);
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 Win!", Toast.LENGTH_SHORT).show();
        tvplayer1.setText("Player 1:"+ player1Points);

    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 Win!", Toast.LENGTH_SHORT).show();
        tvplayer2.setText("Player 2:"+ player2Points);

        //resetBoard();
    }

    private void resetBoard() {
        turnTv.setText("player 1 turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundColor(0XFFFF0000);
                buttons[i][j].setTextColor(0xFFFFFFFF);
            }
        }
        roundCount=0;
        player1turn=true;
    }

    private void closeBoard(){
        for (int i=0; i<3;i++) {
            for (int j=0; j<3; j++) {
                if(buttons[i][j].getText().toString().equals("")){
                    buttons[i][j].setText(" ");
                }
            }
        }
    }



}