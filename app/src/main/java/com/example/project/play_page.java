package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class play_page extends AppCompatActivity {

    Button b_rock, b_scissor, b_paper, b_restart, b_back;
    TextView score, human_choice, computer_choice, player1, player2;
    ImageView iv_human_choice, iv_computer_choice;

    int turn_check = 0;
    int game_mode = 0;
    int HumanScore = 0;
    int ComputerScore = 0;
    String win, win_score, play1_choice;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);

        b_rock = (Button) findViewById(R.id.b_rock);
        b_paper = (Button) findViewById(R.id.b_paper);
        b_scissor = (Button) findViewById(R.id.b_scissor);
        b_restart = (Button) findViewById(R.id.b_restart);
        b_back = (Button) findViewById(R.id.b_back);

        iv_human_choice = (ImageView) findViewById(R.id.iv_human_choice);
        iv_computer_choice = (ImageView) findViewById(R.id.iv_computer_choice);

        //for updating score and others for switching between single/mutiple player
        human_choice = (TextView) findViewById(R.id.human_choice);
        computer_choice = (TextView) findViewById(R.id.computer_choice);
        score = (TextView) findViewById(R.id.score);
        player1 = (TextView) findViewById(R.id.you);
        player2 = (TextView) findViewById(R.id.comp);

        game_mode = getIntent().getIntExtra("mode", 0);   //acessing the mode value

        if (game_mode == 1) {
            //changing texts for multiplayer
            human_choice.setText("Player 1");
            computer_choice.setText("PLayer 2");
            player1.setText("Player 1");
            player2.setText("Player 2");
        }

        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //turn_check = 0 , when player1 is playing and turn_check = 1, when player2 is playing
                if (game_mode == 1 && turn_check == 0) {
                    play1_choice = "rock";
                    Toast.makeText(play_page.this, "player 1 played, player 2's turn", Toast.LENGTH_SHORT).show();
                    turn_check = 1;
                } else if (game_mode == 1 && turn_check == 1) {
                    //player_second function allows player2 to move and returns message of who won
                    String message = player_second("rock");
                   // Toast.makeText(play_page.this, message, Toast.LENGTH_SHORT).show();
                    score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                    turn_check = 0;
                    Toast.makeText(play_page.this, "player 2 played, player 1's turn", Toast.LENGTH_SHORT).show();
                    check_win();
                } else {
                    iv_human_choice.setImageResource(R.drawable.rock);
                    //play_turn function allows computer to move and returns message of who won
                    String message = play_turn("rock");
                    //Toast.makeText(play_page.this, message, Toast.LENGTH_SHORT).show();
                    // updating score
                    score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                    Toast.makeText(play_page.this, "Human played, Computer's turn", Toast.LENGTH_SHORT).show();
                    check_win();  // check if game is completed
                }
            }
        });

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (game_mode == 1 && turn_check == 0) {
                    play1_choice = "paper";
                    Toast.makeText(play_page.this, "player 1 played, player 2's turn", Toast.LENGTH_SHORT).show();
                    turn_check = 1;
                } else if (game_mode == 1 && turn_check == 1) {
                    String message = player_second("paper");
                    //Toast.makeText(play_page.this, message, Toast.LENGTH_SHORT).show();
                    score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                    Toast.makeText(play_page.this, "player 2 played, player 1's turn", Toast.LENGTH_SHORT).show();
                    turn_check = 0;
                    check_win();
                } else {
                    iv_human_choice.setImageResource(R.drawable.paper);
                    String message = play_turn("paper");
                    //Toast.makeText(play_page.this, message, Toast.LENGTH_SHORT).show();
                    score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                    check_win();
                }
            }
        });

        b_scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (game_mode == 1 && turn_check == 0) {
                    play1_choice = "scissor";
                    Toast.makeText(play_page.this, "player 1 played, player 2's turn", Toast.LENGTH_SHORT).show();
                    turn_check = 1;
                } else if (game_mode == 1 && turn_check == 1) {
                    String message = player_second("scissor");
                    //Toast.makeText(play_page.this, message, Toast.LENGTH_SHORT).show();
                    score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                    Toast.makeText(play_page.this, "player 2 played, player 1's turn", Toast.LENGTH_SHORT).show();
                    turn_check = 0;
                    check_win();
                } else {
                    iv_human_choice.setImageResource(R.drawable.scissors);
                    String message = play_turn("scissor");
                    //Toast.makeText(play_page.this, message, Toast.LENGTH_SHORT).show();
                    score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                    check_win();
                }
            }
        });

        b_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play1_choice = "";
                HumanScore = 0;
                ComputerScore = 0;
                score.setText(String.format(" %s:%s", Integer.toString(HumanScore), Integer.toString(ComputerScore)));
                iv_computer_choice.setImageResource(R.drawable.question);
                iv_human_choice.setImageResource(R.drawable.question);
            }
        });

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(play_page.this, MainActivity.class);
                startActivity(it);
            }
        });

    } // end of onCreate

    public String play_turn(String player_choice) {

        String computer_choice = "";
        Random r = new Random();

        // Choose 1 or 2 or 3
        int comp_choice_number = r.nextInt(3) + 1; //random choice of computer

        if (comp_choice_number == 1) {
            computer_choice = "rock";
            iv_computer_choice.setImageResource(R.drawable.rock);
        } else if (comp_choice_number == 2) {
            computer_choice = "paper";
            iv_computer_choice.setImageResource(R.drawable.paper);
        } else {
            computer_choice = "scissor";
            iv_computer_choice.setImageResource(R.drawable.scissors);
        }


            if (computer_choice == "rock" && player_choice == "paper") {
                HumanScore++;
                return "Paper Covers Rock!";
            } else if (computer_choice == "paper" && player_choice == "scissor") {
                HumanScore++;
                return "Scissor Cuts Paper!";
            } else if (computer_choice == "scissor" && player_choice == "rock") {
                HumanScore++;
                return "Rock Crushes Scissor!";
            } else if (computer_choice == "rock" && player_choice == "scissor") {
                ComputerScore++;
                return "Rock Crushes Scissor!";
            } else if (computer_choice == "paper" && player_choice == "rock") {
                ComputerScore++;
                return "Paper Covers Rock!";
            } else if (computer_choice == "scissor" && player_choice == "paper") {
                ComputerScore++;
                return "Scissor Cuts Paper!";
            } else if (computer_choice == player_choice) {
                return "Tie, No Score!";
            } else {
                return "Not Sure";
            }

    }

    public String player_second(String play2_choice){

        if (play2_choice == "rock" && play1_choice == "paper") {  //here player2 choice is computer choice
            HumanScore++;
            iv_human_choice.setImageResource(R.drawable.paper);
            iv_computer_choice.setImageResource(R.drawable.rock);
            return "Paper Covers Rock!";
        } else if (play2_choice == "paper" && play1_choice == "scissor") {
            HumanScore++;
            iv_human_choice.setImageResource(R.drawable.scissors);
            iv_computer_choice.setImageResource(R.drawable.paper);
            return "Scissor Cuts Paper!";
        } else if (play2_choice == "scissor" && play1_choice == "rock") {
            HumanScore++;
            iv_human_choice.setImageResource(R.drawable.rock);
            iv_computer_choice.setImageResource(R.drawable.scissors);
            return "Rock Crushes Scissor!";
        } else if (play2_choice == "rock" && play1_choice == "scissor") {
            ComputerScore++;
            iv_human_choice.setImageResource(R.drawable.scissors);
            iv_computer_choice.setImageResource(R.drawable.rock);
            return "Rock Crushes Scissor!";
        } else if (play2_choice == "paper" && play1_choice == "rock") {
            ComputerScore++;
            iv_human_choice.setImageResource(R.drawable.rock);
            iv_computer_choice.setImageResource(R.drawable.paper);
            return "Paper Covers Rock!";
        } else if (play2_choice == "scissor" && play1_choice == "paper") {
            ComputerScore++;
            iv_human_choice.setImageResource(R.drawable.paper);
            iv_computer_choice.setImageResource(R.drawable.scissors);
            return "Scissor Cuts Paper!";
        } else if (play2_choice == "scissor" && play1_choice == "scissor") {
            iv_human_choice.setImageResource(R.drawable.scissors);
            iv_computer_choice.setImageResource(R.drawable.scissors);
            return "Tie, No Score!";
        } else if (play2_choice == "paper" && play1_choice == "paper") {
            iv_human_choice.setImageResource(R.drawable.paper);
            iv_computer_choice.setImageResource(R.drawable.paper);
            return "Tie, No Score!";
        } else if (play2_choice == "rock" && play1_choice == "rock") {
            iv_human_choice.setImageResource(R.drawable.rock);
            iv_computer_choice.setImageResource(R.drawable.rock);
            return "Tie, No Score!";
        } else {
            return "Not Sure";
        }
    }

    public void check_win(){

        Bundle bundle = new Bundle();

        if(HumanScore == 3){
            if(game_mode == 1) {
                win = "PLayer 1 Won!";
                win_score = "Player 1  " + Integer.toString(HumanScore) + ":" + Integer.toString(ComputerScore) + "  PLayer 2";
            }
            else{
                win = "Congrats, You Won!";
                win_score = "You  " + Integer.toString(HumanScore) + ":" + Integer.toString(ComputerScore) + "  Comp";
            }
            bundle.putString("result", win);
            bundle.putString("win_score", win_score);

            Intent it = new Intent(this, result.class);
            it.putExtra("data", bundle);
            startActivity(it);
        }
        else if(ComputerScore == 3){
            if(game_mode == 1) {
                win = "PLayer 2 Won!";
                win_score = "Player 1  " + Integer.toString(HumanScore) + ":" + Integer.toString(ComputerScore) + "  PLayer 2";
            }
            else{
                win = "Sorry, Computer Won!";
                win_score = "You  " + Integer.toString(HumanScore) + ":" + Integer.toString(ComputerScore) + "  Comp";
            }
            bundle.putString("result", win);
            bundle.putString("win_score", win_score);

            Intent it = new Intent(this, result.class);
            it.putExtra("data", bundle);
            startActivity(it);
        }
    }

}

