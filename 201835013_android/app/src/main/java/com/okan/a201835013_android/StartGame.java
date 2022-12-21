package com.okan.a201835013_android;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {

   static MediaPlayer player;


    TextView tvTimer;
    TextView tvResult;
    ImageView ivShowImage;



    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<String> techlist = new ArrayList<>();
    ArrayList<Integer> songs = new ArrayList<>();
    int index;
    Button btn1, btn2, btn3, btn4;
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;
    long millisUntilFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        tvTimer = findViewById(R.id.tvTimer);
        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);
        index = 0;
        techlist.add("The Weeknd");
        techlist.add("Tom Odell");
        techlist.add("Aerosmith");
        techlist.add("Imagine Dragons");
        techlist.add("Arctic Monkeys");
        techlist.add("Glass Animals");
        techlist.add("Linkin Park");
        techlist.add("Halsey");
        techlist.add("Eminem");
        techlist.add("Dua Lipa");
        techlist.add("Zara Larsson");

        map.put(techlist.get(0), R.drawable.muzik);
        map.put(techlist.get(1), R.drawable.muzik);
        map.put(techlist.get(2), R.drawable.muzik);
        map.put(techlist.get(3), R.drawable.muzik);
        map.put(techlist.get(4), R.drawable.muzik);
        map.put(techlist.get(5), R.drawable.muzik);
        map.put(techlist.get(6), R.drawable.muzik);
        map.put(techlist.get(7), R.drawable.muzik);
        map.put(techlist.get(8), R.drawable.muzik);
        map.put(techlist.get(9), R.drawable.muzik);
        map.put(techlist.get(10), R.drawable.muzik);

        songs.add(0, R.raw.deneme1);
        songs.add(1, R.raw.alove);
        songs.add(2, R.raw.aero);
        songs.add(3, R.raw.imagi);
        songs.add(4, R.raw.doiw);
        songs.add(5, R.raw.glass);
        songs.add(6, R.raw.linkin);
        songs.add(7, R.raw.halsey);
        songs.add(8, R.raw.emnem);
        songs.add(9, R.raw.lipa);
        songs.add(10, R.raw.zara);


     //   Collections.shuffle(techlist);
     //   Collections.shuffle(songs);
        millisUntilFinished = 30000;
        points = 0;
        startGame();

        

    }

    private void startGame() {
        millisUntilFinished = 30000;
        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
        tvPoints.setText(points + " / " + techlist.size());
        generateQuestions(index);
        if(player != null){
            player.release();
            player = null;
        }
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }


            @Override
            public void onFinish() {
                index++;
                if (index > techlist.size() - 1){
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    Intent intent = new Intent(StartGame.this, GameOver.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    finish();
                } else {
                    startGame();
                }
            }
        }.start();
    }

    private void generateQuestions(int index) {
        ArrayList<String> techListTemp = (ArrayList<String>) techlist.clone();
        String correctAnswer = techlist.get(index);
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        ArrayList<String> newList = new ArrayList<>();
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        newList.add(correctAnswer);
        Collections.shuffle(newList);
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        ivShowImage.setImageResource(map.get(techlist.get(index)));
    }

    public void nextQuestion(View view) {
        if(player != null){
            player.release();
            player = null;
        }
        countDownTimer.cancel();
        index++;
        if (index > techlist.size() - 1){
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("points", points);
            startActivity(intent);
            finish();
        } else {
            startGame();
            tvResult.setText(" ");
        }
    }

    public void answerSelected(View view) {
        countDownTimer.cancel();
        String answer = ((Button) view).getText().toString().trim();
        String correctAnswer = techlist.get(index);
        if(answer.equals(correctAnswer)){
           points++;
           tvPoints.setText(points + "/" + techlist.size());
           tvResult.setText("Correct");
        } else {
            tvResult.setText("Wrong");
        }
    }

    public void play(View view) {
        if(player == null){
            player = MediaPlayer.create(getApplicationContext(),
                    songs.get(index));
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
        }
    }
    public void stop(View view){
        //player.stop();
        stopPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

}
