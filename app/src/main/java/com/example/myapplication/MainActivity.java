package com.example.myapplication;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gravityOnClick(View v){
        System.out.println("gravity");
        SeekBar gravitySeekBar = (SeekBar)findViewById(R.id.seekBar1);
        SeekBar MukSeekBar = (SeekBar)findViewById(R.id.seekBar2);
        SeekBar MusSeekBar = (SeekBar)findViewById(R.id.seekBar3);
        double gravityValue = gravitySeekBar.getProgress();
        double MukValue = (new Double (MukSeekBar.getProgress())) / MukSeekBar.getMax();
        double MusValue = (new Double (MusSeekBar.getProgress())) / MusSeekBar.getMax();
        System.out.println(gravityValue);
        System.out.println(MukValue);
        System.out.println(MusValue);
        Intent newActivity1 = new Intent(this, BaseActivity.class);
        newActivity1.putExtra("type","gravity");
        newActivity1.putExtra("gravity",gravityValue);
        newActivity1.putExtra("Muk",MukValue);
        newActivity1.putExtra("Mus",MusValue);
        startActivity(newActivity1);
    }


    public void geoOnClick(View v){
        System.out.println("geo");
        SeekBar gravitySeekBar = (SeekBar)findViewById(R.id.seekBar1);
        SeekBar MukSeekBar = (SeekBar)findViewById(R.id.seekBar2);
        SeekBar MusSeekBar = (SeekBar)findViewById(R.id.seekBar3);
        double gravityValue = gravitySeekBar.getProgress();
        double MukValue = (new Double (MukSeekBar.getProgress())) / MukSeekBar.getMax();
        double MusValue = (new Double (MusSeekBar.getProgress())) / MusSeekBar.getMax();
        System.out.println(gravityValue);
        System.out.println(MukValue);
        System.out.println(MusValue);
        Intent newActivity1 = new Intent(this, BaseActivity.class);
        newActivity1.putExtra("type","geo");
        newActivity1.putExtra("gravity",gravityValue);
        newActivity1.putExtra("Muk",MukValue);
        newActivity1.putExtra("Mus",MusValue);
        startActivity(newActivity1);
    }
}
