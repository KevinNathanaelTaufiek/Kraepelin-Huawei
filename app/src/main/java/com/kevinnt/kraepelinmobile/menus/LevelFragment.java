package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.content.Intent;
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

public class LevelFragment extends Fragment {

    public Context context;
    public Button btn_level_easy, btn_level_normal, btn_level_hard, btn_level_einstein;

    public LevelFragment(Context context) {
        this.context = context;
    }

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
                ((MainActivity)context).getGame_setting().setLevel("easy");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });
        btn_level_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).getGame_setting().setLevel("normal");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });
        btn_level_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).getGame_setting().setLevel("hard");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });
        btn_level_einstein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).getGame_setting().setLevel("einstein");
                ((MainActivity)context).getGame_setting().setScore(0);
                ((MainActivity)context).getGame_setting().setLife(3);
                startGame();
            }
        });

    }

    private void startGame() {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        intent.putExtra("gameSets", ((MainActivity)context).getGame_setting());
        startActivity(intent);
    }

}
