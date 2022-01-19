package com.myself.psychic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

public class EndgameActivity extends AppCompatActivity {
    int score,number,choice,session;
    TextView guess,choose,show;
    String data, saves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        ImageView wl = findViewById(R.id.wl);
        score=getIntent().getIntExtra("score",0);
        number=getIntent().getIntExtra("number",0);
        choice=getIntent().getIntExtra("choice",0);
        session=getIntent().getIntExtra("session",0);
        guess=findViewById(R.id.tv_guess);
        choose=findViewById(R.id.tv_choice);
        show=findViewById(R.id.tv_myscore);

        data=getFromFile();
        if(data==""){
            Date d = new Date();
            String day          = (String) DateFormat.format("dd",   d); // 20
            String monthNumber  = (String) DateFormat.format("MM",   d); // 06
            String year         = (String) DateFormat.format("yyyy", d); // 2013
            String date = year+"/"+monthNumber+"/"+day;
            data="0;0;"+date+";0;"+date+";0;0";
        }
        String[] m = data.split(";");
        m[0]= String.valueOf(session);

        if(score>0){
            m[5]=String.valueOf(Integer.parseInt(m[5])+1);
        }
        if(score<0){
            m[6]=String.valueOf(Integer.parseInt(m[6])+1);
        }
        saves=m[0]+";"+m[1]+";"+m[2]+";"+m[3]+";"+m[4]+";"+m[5]+";"+m[6];
        saveInFile(saves);

        guess.setText(getText(R.string.game_guess)+" "+number);
        choose.setText(getText(R.string.game_choice)+" "+choice);
        show.setText(getText(R.string.game_score)+" "+Integer.parseInt(m[0]));
        if(score>0){
            wl.setImageResource(R.drawable.gagne);
        }else{
            wl.setImageResource(R.drawable.perdu);
        }


    }
    public void playagain(View view){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("session",session);
        startActivity(i);
    }
    public void gototitle(View view){
        Date d = new Date();
        String day          = (String) DateFormat.format("dd",   d); // 20
        String monthNumber  = (String) DateFormat.format("MM",   d); // 06
        String year         = (String) DateFormat.format("yyyy", d); // 2013
        String date = year+"/"+monthNumber+"/"+day;
        data=getFromFile();
        String[] m = data.split(";");
        if(session>Integer.parseInt(m[1])){
            m[1]=String.valueOf(session);
            m[2]=date;
        }
        if(session<Integer.parseInt(m[3])){
            m[3]=String.valueOf(session);
            m[4]=date;
        }
        saves=m[0]+";"+m[1]+";"+m[2]+";"+m[3]+";"+m[4]+";"+m[5]+";"+m[6];
        saveInFile(saves);

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

    private void saveInFile(String data) {
        /* sauvegarde des informations dans le fichier "psychic.txt"
           les infos sont séparées par ;
           * score de la session
           * meilleur score obtenu
           * date du meilleur score (annee/mois/jour)
           * pire score obtenu
           * date du pire score (annee-mois-jour)
           * nombre de parties gagnées
           * nombre de parties perdues
         */
        File file = new File(getApplicationContext().getFilesDir(), "psychic.txt");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            File gpxfile = new File(file, "psychic.txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(data);
            writer.flush();
            writer.close();
        } catch (Exception e) { }
    }
}
