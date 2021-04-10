package com.siddharthch22.siddgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void startGame(View view) {
        Intent intent=new Intent(MainActivity.this,startGame.class);
        startActivity(intent);
        finish();
    }

}