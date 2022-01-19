package com.myself.psychic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int number ;
    int choice;
    int score=0;
    int session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        number = new Random().nextInt(4-1+1)+1; //nombre généré aléatoirement entre 1 et 4
        session=getIntent().getIntExtra("session",0);
    }
    public void first_choice(View view){
        choice=1;
        scoring();
    }
    public void second_choice(View view){
        choice=2;
        scoring();
    }
    public void third_choice(View view){
        choice=3;
        scoring();
    }
    public void fourth_choice(View view){
        choice=4;
        scoring();
    }
    public void scoring(){
        if(number==choice){
            score+=3;
        }
        else{
            score-=1;
        }
        session+=score;
        Intent i = new Intent(this, EndgameActivity.class);
        i.putExtra("score",score);
        i.putExtra("number",number);
        i.putExtra("choice",choice);
        i.putExtra("session",session);
        startActivity(i);
    }
}
