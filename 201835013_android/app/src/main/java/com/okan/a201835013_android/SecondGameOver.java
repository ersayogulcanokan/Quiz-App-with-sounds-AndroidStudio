package com.okan.a201835013_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondGameOver extends AppCompatActivity {

    TextView tvPoints2, tvPersonalBest2;
    SharedPreferences sharedPreferences2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_game_over);
        int points2 = getIntent().getExtras().getInt("points");
        tvPoints2 = findViewById(R.id.tvPoints2);
        tvPersonalBest2 = findViewById(R.id.tvPersonalBest2);
        tvPoints2.setText("" + points2);
        sharedPreferences2 = getSharedPreferences("pref", 0);
        int pointsSP = sharedPreferences2.getInt("pointsSP", 0);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        if(points2 > pointsSP){
            pointsSP = points2;
            editor.putInt("pointsSP", pointsSP);
            editor.commit();
        }
        tvPersonalBest2.setText("" + pointsSP);
    }

    public void restart2(View view) {
        Intent intent = new Intent(SecondGameOver.this, secondstartGame.class);
        startActivity(intent);
        finish();
    }

    public void exit2(View view) {
        finish();
    }
}
