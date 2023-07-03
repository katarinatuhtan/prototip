package com.example.prototip;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototip.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView playerOneScore;
    private TextView playerTwoScore;
    private TextView playerStatus;
    private Button[] buttons=new Button[9];
    private Button newGame;
    ActivityMainBinding binding;

    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;

    int[] gameState={2,2,2,2,2,2,2,2,2};
     int[][] winningPositions={
             {0,1,2}, {3,4,5}, {6,7,8},
             {0,3,6}, {1,4,7}, {2,5,8},
             {0,4,8}, {2,4,6}
     };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        binding.playerOne.setText(getPlayerOneName);
        binding.playerTwo.setText(getPlayerTwoName);

        playerOneScore=(TextView) findViewById(R.id.playerOneScore);
        playerTwoScore=(TextView) findViewById(R.id.playerTwoScore);
        playerStatus=(TextView) findViewById(R.id.playerStatus);

        newGame=(Button) findViewById(R.id.newGame);

        for(int i=0; i< buttons.length;i++){
            String buttonId="btn_" +i;
            int resourceId=getResources().getIdentifier(buttonId,"id",getPackageName());
            buttons[i]=(Button) findViewById(resourceId);
            buttons[i].setOnClickListener(this);
        }
        rountCount=0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;
    }

    @Override
    public void onClick(View v) {
        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        binding.playerOne.setText(getPlayerOneName);
        binding.playerTwo.setText(getPlayerTwoName);

        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonId=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer= Integer.parseInt(buttonId.substring(buttonId.length()-1,buttonId.length()));

        if(activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#4e2a59"));
            gameState[gameStatePointer]=0;
        }
        else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#c10c76"));
            gameState[gameStatePointer]=1;
        }
        rountCount++;

        if(checkWinner()){
            if(activePlayer) {
            playerOneScoreCount++;
            updatePlayerScore();
            Toast.makeText(this, "Player one won!", Toast.LENGTH_SHORT).show();
            playAgain();
            }
            else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player two won!", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        } else if (rountCount==9) {
            playAgain();
            Toast.makeText(this,"No one won :(", Toast.LENGTH_SHORT).show();
        }else {
            activePlayer=!activePlayer;
        }

        if(playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player one is winning!");
        } else if (playerTwoScoreCount>playerOneScoreCount) {
            playerStatus.setText("Player two is winning!");
        }
        else {
            playerStatus.setText("");
        }
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }
    public boolean checkWinner(){
        boolean winnerResult=false;
        for(int[] winningPosition: winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=2){
                winnerResult=true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
    public void playAgain(){
        rountCount=0;
        activePlayer=true;

        for(int i=0;i< buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }
}