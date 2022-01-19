package com.myself.psychic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatsActivity extends AppCompatActivity {
    TextView score,best,worst,won,lost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        score=findViewById(R.id.stats_session);
        best=findViewById(R.id.stats_best);
        worst=findViewById(R.id.stats_worst);
        won=findViewById(R.id.stats_won);
        lost=findViewById(R.id.stats_lost);
        String data=getFromFile();
        String[] m=data.split(";");


        String dateBest = parseDate(m[2]);
        String dateWorst = parseDate(m[4]);


        score.setText(getText(R.string.stats_actual)+" "+m[0]);
        best.setText(getText(R.string.stats_best)+" "+m[1]+" "+ getText(R.string.ww)+" "+dateBest);
        worst.setText(getText(R.string.stats_worst)+" "+m[3]+" "+getText(R.string.ww)+" "+dateWorst);
        won.setText(getText(R.string.stats_won)+" "+m[5]);
        lost.setText(getText(R.string.stats_lost)+" "+m[6]);
    }

    public String parseDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            Log.d("psycho","date from file :  "+source);
            date = sdf.parse(source);
            Log.d("psycho","date from parse :  "+date);
        } catch (ParseException | java.text.ParseException e) {
            // handle exception here !
        }
        Locale current = getResources(). getConfiguration().locale;
        Log.d("psycho","locale :  "+current);
        String maDate = DateFormat.getDateInstance(DateFormat.LONG,current).format(date);
        Log.d("psycho","date from format :  "+maDate);
        return maDate;
    }

    public void gototitle(View view){
     Intent i = new Intent(this, TitleActivity.class);
        startActivity(i);
    }

    private String getFromFile(){
        String str="";
        File file = new File(getApplicationContext().getFilesDir(), "psychic.txt");
        try {
            File gpxfile = new File(file, "psychic.txt");
            FileReader reader = new FileReader(gpxfile);
            BufferedReader bf = new BufferedReader((reader));
            String line;

            while ((line = bf.readLine()) != null) {
                str+=line;
            }

        } catch (Exception e) { }
        return  str;
    }



}
