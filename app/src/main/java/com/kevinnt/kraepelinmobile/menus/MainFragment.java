package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kevinnt.kraepelinmobile.MainActivity;
import com.kevinnt.kraepelinmobile.R;

import java.io.IOException;

public class MainFragment extends Fragment {

    public Context context;
    public Button btn_play, btn_high_score;
    private MediaPlayer mediaPlayer;
    public MainFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_main_menu, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        btn_high_score = ((MainActivity)context).findViewById(R.id.btn_high_score);
        btn_high_score.setText("High Score");

        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        if(((MainActivity)context).getTvName().getText().equals("Hi, Guest!")){
            ((MainActivity)context).getBtnLogin().setVisibility(View.VISIBLE);
            ((MainActivity)context).getBtnLogout().setVisibility(View.GONE);
        }
        else{
            ((MainActivity)context).getBtnLogout().setVisibility(View.VISIBLE);
            ((MainActivity)context).getBtnLogin().setVisibility(View.GONE);
        }

        btn_play = getView().findViewById(R.id.btn_play);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                OperatorFragment operatorFragment = new OperatorFragment(context);
                getParentFragmentManager().beginTransaction().replace(R.id.fl_container, operatorFragment).addToBackStack(null).commit();
            }
        });

        btn_high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBtn();
                getParentFragmentManager().beginTransaction().replace(((MainActivity)context).getFl_container().getId(), new HighScoreFragment(context)).addToBackStack(null).commit();
            }
        });

    }

    private void clickBtn(){
        MediaPlayer cb =  MediaPlayer.create(context, R.raw.click);
        cb.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        btn_high_score.setText("High Score");
        btn_high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fl_container, new HighScoreFragment(context)).addToBackStack(null).commit();
            }
        });
    }
}
