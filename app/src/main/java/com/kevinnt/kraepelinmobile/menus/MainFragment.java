package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kevinnt.kraepelinmobile.MainActivity;
import com.kevinnt.kraepelinmobile.R;

public class MainFragment extends Fragment {

    public Context context;
    public Button btn_play;

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

        if(((MainActivity)context).tvName.getText().equals("Hi, Guest!")){
            ((MainActivity)context).btnLogin.setVisibility(View.VISIBLE);
            ((MainActivity)context).btnLogout.setVisibility(View.GONE);
        }
        else{
            ((MainActivity)context).btnLogout.setVisibility(View.VISIBLE);
            ((MainActivity)context).btnLogin.setVisibility(View.GONE);
        }

        btn_play = getView().findViewById(R.id.btn_play);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperatorFragment operatorFragment = new OperatorFragment(context);
                getParentFragmentManager().beginTransaction().replace(R.id.fl_container, operatorFragment).addToBackStack(null).commit();
            }
        });

    }
}
