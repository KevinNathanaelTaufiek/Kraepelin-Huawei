package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kevinnt.kraepelinmobile.GameActivity;
import com.kevinnt.kraepelinmobile.MainActivity;
import com.kevinnt.kraepelinmobile.R;
import com.kevinnt.kraepelinmobile.TutorialActivity;

import java.io.IOException;

public class LevelFragment extends Fragment {

    public Context context;
    public Button btn_level_easy, btn_level_normal, btn_level_hard, btn_level_einstein;

    public LevelFragment(Context context) {
        this.context = context;
    }
    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_level_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        btn_level_easy = getView().findViewById(R.id.btn_level_easy);
        btn_level_normal = getView().findViewById(R.id.btn_level_normal);
        btn_level_hard = getView().findViewById(R.id.btn_level_hard);
        btn_level_einstein = getView().findViewById(R.id.btn_level_einstein);

        btn_level_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setLevel("easy");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });
        btn_level_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setLevel("normal");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });
        btn_level_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setLevel("hard");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });
        btn_level_einstein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setLevel("einstein");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });

    }

    private void clickBtn(){
        mediaPlayer = MediaPlayer.create(context, R.raw.click);

        try{
            mediaPlayer.prepare();
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }catch (IOException ex1){
            ex1.printStackTrace();
        }
        mediaPlayer.start();
    }

    private void info(){
        mediaPlayer = MediaPlayer.create(context, R.raw.info);

        try{
            mediaPlayer.prepare();
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }catch (IOException ex1){
            ex1.printStackTrace();
        }
        mediaPlayer.start();
    }

    private void startGame() {
        info();
        Intent intent = new Intent(getActivity(), TutorialActivity.class);
        intent.putExtra("gameSets", ((MainActivity)context).getGame_setting());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)context).getBtn_high_score().setText("High Score");
        ((MainActivity)context).getBtn_high_score().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fl_container, new HighScoreFragment(context)).addToBackStack(null).commit();
            }
        });
    }
}
