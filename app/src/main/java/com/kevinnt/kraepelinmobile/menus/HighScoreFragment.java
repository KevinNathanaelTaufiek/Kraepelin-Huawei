package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kevinnt.kraepelinmobile.GameActivity;
import com.kevinnt.kraepelinmobile.MainActivity;
import com.kevinnt.kraepelinmobile.R;

import java.io.IOException;

public class HighScoreFragment extends Fragment {

    private Context context;
    private TextView tv_high_score_addition, tv_high_score_subtraction, tv_high_score_multiplication, tv_high_score_division;
    private Button btn_high_score;
    private MediaPlayer mediaPlayer;

    public HighScoreFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_high_scores, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)context).getBtnLogin().setVisibility(View.GONE);

        SharedPreferences sharedPreferences = context.getSharedPreferences(GameActivity.getSP(), Context.MODE_PRIVATE);

        tv_high_score_addition = getView().findViewById(R.id.tv_high_score_addition);
        tv_high_score_subtraction = getView().findViewById(R.id.tv_high_score_subtraction);
        tv_high_score_multiplication = getView().findViewById(R.id.tv_high_score_multiplication);
        tv_high_score_division = getView().findViewById(R.id.tv_high_score_division);
        btn_high_score = ((MainActivity)context).findViewById(R.id.btn_high_score);


        int highScore = sharedPreferences.getInt(GameActivity.getHighScore()+'+',0);
        tv_high_score_addition.setText(tv_high_score_addition.getText().toString() + highScore);

        highScore = sharedPreferences.getInt(GameActivity.getHighScore()+'-',0);
        tv_high_score_subtraction.setText(tv_high_score_subtraction.getText().toString() + highScore);

        highScore = sharedPreferences.getInt(GameActivity.getHighScore()+'*',0);
        tv_high_score_multiplication.setText(tv_high_score_multiplication.getText().toString() + highScore);

        highScore = sharedPreferences.getInt(GameActivity.getHighScore()+'/',0);
        tv_high_score_division.setText(tv_high_score_division.getText().toString() + highScore);

        btn_high_score.setText("X");

        btn_high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                btn_high_score.setText("High Score");
                getParentFragmentManager().beginTransaction().replace(((MainActivity)context).getFl_container().getId(), new MainFragment(context)).commit();

            }
        });
    }

    private void clickBtn(){
        MediaPlayer cb =  MediaPlayer.create(context, R.raw.click);
        cb.start();
    }

}
