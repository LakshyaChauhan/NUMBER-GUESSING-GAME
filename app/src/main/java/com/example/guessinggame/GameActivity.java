package com.example.guessinggame;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textLast,textRight, textHint;
    private EditText editTextGuess;
    private Button buttonConfirm;
    boolean twoDigits, threeDigits,FourDigits;
    Random r = new Random();
    int random, remainingRight = 10, userAttempts= 0;
    ArrayList<Integer> guessList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textLast = findViewById(R.id.textLast);
        textRight = findViewById(R.id.textRight);
        textHint = findViewById(R.id.textHint);
        editTextGuess = findViewById(R.id.editextGuess);

        buttonConfirm = findViewById(R.id.bConfirm);

        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        FourDigits = getIntent().getBooleanExtra("four",false);

        if(twoDigits){
            random = r.nextInt(90)+10;
        }
        if(threeDigits){
            random = r.nextInt(900)+100;
        }
        if(FourDigits){
            random = r.nextInt(9000)+1000;
        }
        
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String guess = editTextGuess.getText().toString();
                if(guess.equals("")){
                    Toast.makeText(GameActivity.this, "Please enter your guess.", Toast.LENGTH_SHORT).show();
                }
                else{
                    textLast.setVisibility(VISIBLE);
                    textRight.setVisibility(VISIBLE);
                    textHint.setVisibility(VISIBLE);


                    remainingRight--;
                    userAttempts++;

                    int guessInt = Integer.parseInt(guess);
                    guessList.add(guessInt);

                    textLast.setText("Your last guess : "+guessInt);
                    if(remainingRight== 1)
                        textRight.setText("Your remaining right "+remainingRight);
                    textRight.setText("Your remaining rights "+remainingRight);




                    if(random == guessInt){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameActivity.this);
                        alertDialog.setTitle("Number Guessing Game")
                                .setCancelable(false)
                                .setMessage("Congratulations . My guess was "+ random+
                                        "\n\n You know my number in "+userAttempts+" attempts. \n\n " +
                                        "Your guesses : "+guessList+"\n\n Would you like to play again?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(GameActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                    }
                                });

                        alertDialog.create().show();
                    }
                    if (random< guessInt) {
                            textHint.setText("Decrease your guess.");
                    }
                    if(random > guessInt){
                        textHint.setText("Increase your guess.");
                    }

                    if(remainingRight == 0){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameActivity.this);
                        alertDialog.setTitle("Number Guessing Game")
                                .setCancelable(false)
                                .setMessage("Sorry your right to guess is over "+
                                        "\n\n My guess was "+random +
                                        "\n\n " + "Your guesses : "+guessList+
                                        "\n\n Would you like to play again?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(GameActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                    }
                                });

                        alertDialog.create().show();
                    }

                    editTextGuess.setText("");
                }
            }
        });
    }
}