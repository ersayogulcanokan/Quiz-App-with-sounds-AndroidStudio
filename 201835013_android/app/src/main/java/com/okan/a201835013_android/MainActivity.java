package com.okan.a201835013_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, StartGame.class);
        startActivity(intent);
        finish();
    }

    public void secondstartGame(View view) {
        Intent intent = new Intent(MainActivity.this, secondstartGame.class);
        startActivity(intent);
        finish();
    }
}