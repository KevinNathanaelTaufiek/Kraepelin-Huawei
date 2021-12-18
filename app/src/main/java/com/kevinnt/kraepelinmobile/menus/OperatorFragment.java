package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kevinnt.kraepelinmobile.MainActivity;
import com.kevinnt.kraepelinmobile.R;

import java.io.IOException;

public class OperatorFragment extends Fragment {

    public Context context;
    public Button btn_addition_operator, btn_subtraction_operator, btn_multiplication_operator, btn_division_operator;
    private MediaPlayer mediaPlayer;
    public OperatorFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_operator_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)context).getBtnLogin().setVisibility(View.GONE);
        btn_addition_operator = getView().findViewById(R.id.tv_operator);
        btn_subtraction_operator = getView().findViewById(R.id.btn_subtraction_operator);
        btn_multiplication_operator = getView().findViewById(R.id.btn_multiplication_operator);
        btn_division_operator = getView().findViewById(R.id.btn_division_operator);

        btn_addition_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setOperator('+');
                goMenuLevel();
            }
        });

        btn_subtraction_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setOperator('-');
                goMenuLevel();
            }
        });

        btn_multiplication_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setOperator('*');
                goMenuLevel();
            }
        });

        btn_division_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBtn();
                ((MainActivity)context).getGame_setting().setOperator('/');
                goMenuLevel();
            }
        });

    }

    private void goMenuLevel(){
        LevelFragment levelFragment = new LevelFragment(context);
        getParentFragmentManager().beginTransaction().replace(R.id.fl_container, levelFragment).addToBackStack(null).commit();
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

}
