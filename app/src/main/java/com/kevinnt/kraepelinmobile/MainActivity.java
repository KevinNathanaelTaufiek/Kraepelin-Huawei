package com.kevinnt.kraepelinmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.huawei.agconnect.api.AGConnectApi;
import com.kevinnt.kraepelinmobile.menus.MainFragment;
import com.kevinnt.kraepelinmobile.models.GameSets;

public class MainActivity extends AppCompatActivity {

    public FrameLayout fl_container;
    public Button btn_high_score;
    public GameSets game_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_container = findViewById(R.id.fl_container);
        btn_high_score = findViewById(R.id.btn_high_score);
        game_setting = new GameSets();

        getSupportFragmentManager().beginTransaction().replace(fl_container.getId(), new MainFragment(this)).addToBackStack(null).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AGConnectApi.getInstance().activityLifecycle().onActivityResult(requestCode, resultCode, data);
    }
}