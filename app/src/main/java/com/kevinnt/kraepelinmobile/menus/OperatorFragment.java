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

public class OperatorFragment extends Fragment {

    public Context context;
    public Button btn_addition_operator, btn_subtraction_operator, btn_multiplication_operator, btn_division_operator;

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
        btn_addition_operator = getView().findViewById(R.id.btn_addition_operator);
        btn_subtraction_operator = getView().findViewById(R.id.btn_subtraction_operator);
        btn_multiplication_operator = getView().findViewById(R.id.btn_multiplication_operator);
        btn_division_operator = getView().findViewById(R.id.btn_division_operator);

    }

    private void goMenuLevel(char operator){
        LevelFragment levelFragment = new LevelFragment(context);
        getParentFragmentManager().beginTransaction().replace(R.id.fl_container, levelFragment).addToBackStack(null).commit();
    }

}
