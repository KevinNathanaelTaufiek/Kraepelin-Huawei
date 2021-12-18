package com.kevinnt.kraepelinmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class TutorialActivity extends AppCompatActivity {


    private androidx.constraintlayout.widget.ConstraintLayout screen;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        screen = findViewById(R.id.screen);
        mediaPlayer = MainActivity.getMediaPlayer();
        mediaPlayer.start();

        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn();
                Intent intent = new Intent(TutorialActivity.this, GameActivity.class);
                intent.putExtra("gameSets", MainActivity.getGame_setting());
                startActivity(intent);
            }
        });
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

        try{
            mediaPlayer.prepare();
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }catch (IOException ex1){
            ex1.printStackTrace();
        }
        mediaPlayer.start();
    }
}