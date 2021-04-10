package com.siddharthch22.siddgame;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    TextView tvPoints,tvHighScore;
    SharedPreferences sharedPreferences;
    ImageView ivHighScore;
    Button bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        ivHighScore=findViewById(R.id.ivHighScore);
        tvHighScore=findViewById(R.id.tvHighScore);
        getSupportActionBar().hide();
        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoints);
        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent myintent=new Intent(Intent.ACTION_SEND);
                                      myintent.setType("text/plain");
                                      String shareBody ="Your body here";
                                      String shareSub="Your subject here";
                                      myintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                                      myintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                                      startActivity(Intent.createChooser(myintent,"Share Using"));
                                  }
                              });

                sharedPreferences = getSharedPreferences("pref", 0);
                int pointsSp=sharedPreferences.getInt("pointsSp",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(points>pointsSp) {
            pointsSp = points;
            editor.putInt("pointsSp", pointsSp);
            editor.commit();
            ivHighScore.setVisibility(View.VISIBLE);
        }
        tvPoints.setText(""+points);
        tvHighScore.setText(""+pointsSp);
    }

    public void restart(View view) {
        Intent intent=new Intent(GameOver.this,startGame.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
                finish();
    }
}
