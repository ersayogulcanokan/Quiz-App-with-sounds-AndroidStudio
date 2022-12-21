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

public class secondstartGame extends AppCompatActivity {

    static MediaPlayer player2;


    TextView tvTimer2;
    TextView tvResult2;
    ImageView ivShowImage2;



    HashMap<String, Integer> map2 = new HashMap<>();
    ArrayList<String> techlist2 = new ArrayList<>();
    ArrayList<Integer> songs2 = new ArrayList<>();
    int index2;
    Button btn1_2, btn2_2, btn3_2, btn4_2;
    TextView tvPoints2;
    int points2;
    CountDownTimer countDownTimer2;
    long millisUntilFinished2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_start_game);
        tvTimer2 = findViewById(R.id.tvTimer2);
        tvResult2 = findViewById(R.id.tvResult2);
        ivShowImage2 = findViewById(R.id.ivShowImage2);
        btn1_2 = findViewById(R.id.btn1_2);
        btn2_2 = findViewById(R.id.btn2_2);
        btn3_2 = findViewById(R.id.btn3_2);
        btn4_2 = findViewById(R.id.btn4_2);
        tvPoints2 = findViewById(R.id.tvPoints2);
        index2 = 0;
        techlist2.add("Mor ve Otesi");
        techlist2.add("Duman");
        techlist2.add("Teoman");
        techlist2.add("Madrigal");
        techlist2.add("Manga");
        techlist2.add("Mabel Matiz");
        techlist2.add("Kofn");
        techlist2.add("Hayko Cepkin");
        techlist2.add("Model");
        techlist2.add("Mavi Gri");
        techlist2.add("Yuzyuzeyken Konusuruz");

        map2.put(techlist2.get(0), R.drawable.muzik);
        map2.put(techlist2.get(1), R.drawable.muzik);
        map2.put(techlist2.get(2), R.drawable.muzik);
        map2.put(techlist2.get(3), R.drawable.muzik);
        map2.put(techlist2.get(4), R.drawable.muzik);
        map2.put(techlist2.get(5), R.drawable.muzik);
        map2.put(techlist2.get(6), R.drawable.muzik);
        map2.put(techlist2.get(7), R.drawable.muzik);
        map2.put(techlist2.get(8), R.drawable.muzik);
        map2.put(techlist2.get(9), R.drawable.muzik);
        map2.put(techlist2.get(10), R.drawable.muzik);

        songs2.add(0, R.raw.mvo);
        songs2.add(1, R.raw.duman);
        songs2.add(2, R.raw.teom);
        songs2.add(3, R.raw.madri);
        songs2.add(4, R.raw.manga);
        songs2.add(5, R.raw.mabel);
        songs2.add(6, R.raw.kofn);
        songs2.add(7, R.raw.hayko);
        songs2.add(8, R.raw.model);
        songs2.add(9, R.raw.mavig);
        songs2.add(10, R.raw.yuz);

        //   Collections.shuffle(techlist);
        //   Collections.shuffle(songs);
        millisUntilFinished2 = 30000;
        points2 = 0;
        secondstartGame();
    }

    private void secondstartGame() {
        millisUntilFinished2 = 30000;
        tvTimer2.setText("" + (millisUntilFinished2 / 1000) + "s");
        tvPoints2.setText(points2 + " / " + techlist2.size());
        generateQuestions2(index2);
        countDownTimer2 = new CountDownTimer(millisUntilFinished2, 1000) {
            @Override
            public void onTick(long millisUntilFinished2) {
                tvTimer2.setText("" + (millisUntilFinished2 / 1000) + "s");
            }

            @Override
            public void onFinish() {
                index2++;
                if (index2 > techlist2.size() - 1){
                    ivShowImage2.setVisibility(View.GONE);
                    btn1_2.setVisibility(View.GONE);
                    btn2_2.setVisibility(View.GONE);
                    btn3_2.setVisibility(View.GONE);
                    btn4_2.setVisibility(View.GONE);
                    Intent intent = new Intent(secondstartGame.this, GameOver.class);
                    intent.putExtra("points", points2);
                    startActivity(intent);
                    finish();
                } else {
                    secondstartGame();
                }
            }
        }.start();

            }

    private void generateQuestions2(int index2) {
        ArrayList<String> techListTemp = (ArrayList<String>) techlist2.clone();
        String correctAnswer = techlist2.get(index2);
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        ArrayList<String> newList = new ArrayList<>();
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        newList.add(correctAnswer);
        Collections.shuffle(newList);
        btn1_2.setText(newList.get(0));
        btn2_2.setText(newList.get(1));
        btn3_2.setText(newList.get(2));
        btn4_2.setText(newList.get(3));
        ivShowImage2.setImageResource(map2.get(techlist2.get(index2)));
    }


    public void nextQuestion2(View view) {
        if(player2 != null){
            player2.release();
            player2 = null;
        }
        countDownTimer2.cancel();
        index2++;
        if (index2 > techlist2.size() - 1){
            ivShowImage2.setVisibility(View.GONE);
            btn1_2.setVisibility(View.GONE);
            btn2_2.setVisibility(View.GONE);
            btn3_2.setVisibility(View.GONE);
            btn4_2.setVisibility(View.GONE);
            Intent intent = new Intent(secondstartGame.this, GameOver.class);
            intent.putExtra("points", points2);
            startActivity(intent);
            finish();
        } else {
            secondstartGame();
            tvResult2.setText(" ");
        }
    }

    public void answerSelected2(View view) {
        countDownTimer2.cancel();
        String answer = ((Button) view).getText().toString().trim();
        String correctAnswer = techlist2.get(index2);
        if(answer.equals(correctAnswer)){
            points2++;
            tvPoints2.setText(points2 + "/" + techlist2.size());
            tvResult2.setText("Correct");
        } else {
            tvResult2.setText("Wrong");
        }
    }

    public void play2(View view) {
        if(player2 == null){
            player2 = MediaPlayer.create(getApplicationContext(),
                    songs2.get(index2));
            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer2();
                }
            });
        }
        player2.start();
    }
    public void stopPlayer2(){
        if(player2 != null){
            player2.release();
            player2 = null;
        }
    }
    public void stop2(View view){
        //player.stop();
        stopPlayer2();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer2();
    }


}


    