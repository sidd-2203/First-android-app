package com.siddharthch22.siddgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class startGame extends AppCompatActivity {

    int op1, op2, CorrectAnswer,inCorrectAnswer;
    TextView tvTimer, tvSum, tvPoints, tvResult;
    Button btn0,btn1,btn2,btn3;
    CountDownTimer countDownTimer;
    long millisUntilFinished;
    int points;
    int numberOfQuestions;
    Random random;
    int []btnId;
    int CorrectAnswerPosition;
    ArrayList<Integer> incorrectAnswers;
    String[] operatorArray;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        getSupportActionBar().hide();
        op1 = 0;
        op2 = 0;
        CorrectAnswer = 0;
        points = 0;
        numberOfQuestions = 0;
        inCorrectAnswer=0;
        tvTimer = findViewById(R.id.tvTimer);
        tvPoints = findViewById(R.id.tvPoints);
        tvSum = findViewById(R.id.tvSum);
        tvResult = findViewById(R.id.tvResult);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        CorrectAnswerPosition=0;
        millisUntilFinished = 30100;
        random =new Random();
        btnId=new int[]{R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3};
        incorrectAnswers=new ArrayList<>();
        operatorArray=new String[]{"+","-","*","/"};
        startGame();
    }

    private void startGame() {

        tvTimer.setText("" + (millisUntilFinished / 1000 + "s"));
        tvPoints.setText("" + points + "/" + numberOfQuestions);
        generateQuestion();
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            tvTimer.setText(""+millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
            btn0.setClickable(false);
            btn1.setClickable(false);
            btn2.setClickable(false);
            btn3.setClickable(false);
            Intent intent=new Intent(startGame.this,GameOver.class);
            intent.putExtra("points",points);
            startActivity(intent);
            finish();
            }
        }.start();
    }

    private void generateQuestion() {
       numberOfQuestions++;
    op1=random.nextInt(10);
        op2=1+random.nextInt(9);
        String selectedOperator=operatorArray[random.nextInt(4)];
    CorrectAnswer=getAnswer(selectedOperator);
    tvSum.setText(op1+selectedOperator+op2+" = ");
    CorrectAnswerPosition=random.nextInt(4);
        ((Button)findViewById(btnId[CorrectAnswerPosition])).setText(""+CorrectAnswer);
        while(true){
            if(incorrectAnswers.size()>3)
                break;
            op1=random.nextInt(10);
            op2=1+random.nextInt(9);
            selectedOperator=operatorArray[random.nextInt(4)];
            inCorrectAnswer=getAnswer(selectedOperator);
            if(inCorrectAnswer==CorrectAnswer)
                continue;
            incorrectAnswers.add(inCorrectAnswer);
        }
        for (int i=0;i<3;i++){
            if(i==CorrectAnswerPosition)
                continue;
            ((Button)findViewById(btnId[i])).setText(""+incorrectAnswers.get(i));
        }
        incorrectAnswers.clear();
    }

    private int getAnswer(String selectedOperator) {
        int answer=0;
        switch(selectedOperator){
            case "+":
                answer=op1+op2;
                break;
            case "-":
                answer=op1-op2;
                break;
            case "*":
                answer=op1*op2;
                break;
            case "/":
                answer=op1/op2;
                break;

        }
        return answer;
    }

    public void chooseAnswer(View view) {
        int answer=Integer.parseInt(((Button) view).getText().toString());
        if(answer==CorrectAnswer){
            points++;
            tvPoints.setText(points +"/"+numberOfQuestions);
            tvResult.setText("Correct");
        }else{
            tvPoints.setText(points +"/"+numberOfQuestions);
            tvResult.setText("WRONG");
        }
    generateQuestion();
    }
}
