package com.kevinnt.kraepelinmobile.menus;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kevinnt.kraepelinmobile.R;

public class LevelFragment extends Fragment {

    public Context context;
    public Button btn_addition_operator, btn_subtraction_operator, btn_multiplication_operator, btn_division_operator;

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

    }

}
