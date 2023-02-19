package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button button;
    RadioButton radioButton2, radioButton3,radioButton4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonStart);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        radioButton4 = findViewById(R.id.radio4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,GameActivity.class);

                if(!radioButton2.isChecked() && !radioButton3.isChecked() && !radioButton4.isChecked() ){
                    Snackbar.make(v,"Please select the number of digits.",Snackbar.LENGTH_SHORT).show();
                }else{
                    if(radioButton2.isChecked()){
                        i.putExtra("two",true);
                    }
                    if(radioButton3.isChecked()){
                        i.putExtra("three",true);
                    }
                    if(radioButton4.isChecked()){
                        i.putExtra("four",true);
                    }

                    startActivity(i);
                }
            }
        });

    }
}