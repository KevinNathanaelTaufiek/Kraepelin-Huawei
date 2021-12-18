package com.kevinnt.kraepelinmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.io.IOException;

public class TutorialActivity extends AppCompatActivity {


    private androidx.constraintlayout.widget.ConstraintLayout screen;
    private MediaPlayer mediaPlayer;
    private TextView tap;
    private Animation fadeIn,fadeOut;
    private Boolean fadeflag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        screen = findViewById(R.id.screen);
        mediaPlayer = MainActivity.getMediaPlayer();
        mediaPlayer.start();

        tap = findViewById(R.id.tv_tap);
        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);

        fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeOut.setDuration(1200);
        fadeIn.setFillAfter(true);

        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn();
                Intent intent = new Intent(TutorialActivity.this, GameActivity.class);
                intent.putExtra("gameSets", MainActivity.getGame_setting());
                startActivity(intent);
            }
        });

        if (fadeflag) {
            tap.startAnimation(fadeIn);
            fadeflag = false;
        } else {
            tap.startAnimation(fadeOut);
            fadeflag = true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
        intent.putExtra("MusicPlayed",false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    private void clickBtn(){
        mediaPlayer = MediaPlayer.create(this, R.raw.click);
        mediaPlayer.start();
    }
}