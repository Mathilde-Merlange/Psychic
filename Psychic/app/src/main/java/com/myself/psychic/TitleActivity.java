package com.myself.psychic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TitleActivity extends AppCompatActivity {
    int session=0; //score cumulé des parties jouées
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

    }
    public void gotoplay(View view){
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
    public void gotostats(View view){
        Intent i = new Intent(this, StatsActivity.class);
        i.putExtra("session",session);
        startActivity(i);
    }
}
