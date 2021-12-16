package com.kevinnt.kraepelinmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kevinnt.kraepelinmobile.models.GameSets;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView tv_score, tv_life, tv_first_operand, tv_operator, tv_second_operand;
    public Button btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_zero;

    public GameSets gameSets;
    public int score, life, scoreIncrementalValue, operandBound;
    public int firstOperandValue, secondOperandValue;
    public Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        setAnswerButton();

    }

    private void generateQuestion(){
        firstOperandValue = rand.nextInt(operandBound)+1;
        secondOperandValue = rand.nextInt(operandBound)+1;

        if(gameSets.getOperator() == '-' || gameSets.getOperator() == '/'){
          while(firstOperandValue<secondOperandValue){
              firstOperandValue = rand.nextInt(operandBound)+1;
              secondOperandValue = rand.nextInt(operandBound)+1;
          }
        }

        tv_first_operand.setText(""+firstOperandValue);
        tv_second_operand.setText(""+secondOperandValue);

        tv_operator.setText(gameSets.getOperator() + "");
    }

    private void answer(int answer){
        int result;
        switch (gameSets.getOperator()){
            case '+':
                result = firstOperandValue + secondOperandValue;
                break;
            case '-':
                result = firstOperandValue - secondOperandValue;
                break;
            case '*':
                result = firstOperandValue * secondOperandValue;
                break;
            case '/':
                result = firstOperandValue / secondOperandValue;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameSets.getOperator());
        }

        result = result%10;

        if(answer == result){
            score += scoreIncrementalValue;
            tv_score.setText("Score : "+ score);
        }
        else{
            life--;
            tv_life.setText("Life : "+ life);
        }

        if(life > 0){
            generateQuestion();
        }
        else{
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
            
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }


    private void setAnswerButton(){
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_seven.setOnClickListener(this);
        btn_eight.setOnClickListener(this);
        btn_nine.setOnClickListener(this);
        btn_zero.setOnClickListener(this);
    }

    private void init(){
        tv_score = findViewById(R.id.tv_score);
        tv_life = findViewById(R.id.tv_life);
        tv_first_operand = findViewById(R.id.tv_first_operand);
        tv_operator = findViewById(R.id.tv_operator);
        tv_second_operand = findViewById(R.id.tv_second_operand);

        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        btn_zero = findViewById(R.id.btn_zero);

        gameSets = getIntent().getParcelableExtra("gameSets");

        score = gameSets.getScore();
        life = gameSets.getLife();

        tv_score.setText("Score : "+ score);
        tv_life.setText("Life : "+ life);


        switch (gameSets.getLevel()){
            case "easy":
                scoreIncrementalValue = 10;
                operandBound = 10;
                break;
            case "normal":
                scoreIncrementalValue = 15;
                operandBound = 100;
                break;
            case "hard":
                scoreIncrementalValue = 20;
                operandBound = 1000;
                break;
            case "einstein":
                scoreIncrementalValue = 30;
                operandBound = 10000;
                break;
        }
        generateQuestion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                answer(1);
                break;
            case R.id.btn_two:
                answer(2);
                break;
            case R.id.btn_three:
                answer(3);
                break;
            case R.id.btn_four:
                answer(4);
                break;
            case R.id.btn_five:
                answer(5);
                break;
            case R.id.btn_six:
                answer(6);
                break;
            case R.id.btn_seven:
                answer(7);
                break;
            case R.id.btn_eight:
                answer(8);
                break;
            case R.id.btn_nine:
                answer(9);
                break;
            case R.id.btn_zero:
                answer(0);
                break;
        }
    }
}