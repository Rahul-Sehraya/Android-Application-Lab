package com.example.linearlayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer, tvScore, tvQuestion;
    private EditText etAnswer;
    private ImageView imgCorrect, imgWrong;

    private int score = 0;
    private int correctAnswer = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        tvScore = findViewById(R.id.tvScore);
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        imgCorrect = findViewById(R.id.imgCorrect);
        imgWrong = findViewById(R.id.imgWrong);

        generateQuestion();
        startTimer();

        etAnswer.setOnEditorActionListener((v, actionId, event) -> {
            checkAnswer();
            return true;
        });
    }

    private void startTimer() {
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                tvTimer.setText("Time: 0");
                etAnswer.setEnabled(false);
            }
        }.start();
    }

    private void generateQuestion() {
        int a = random.nextInt(90) + 10; // 2-digit
        int b = random.nextInt(90) + 10;
        boolean isAddition = random.nextBoolean();

        if (isAddition) {
            tvQuestion.setText(a + " + " + b);
            correctAnswer = a + b;
        } else {
            tvQuestion.setText(a + " - " + b);
            correctAnswer = a - b;
        }

        etAnswer.setText("");
        imgCorrect.setAlpha(0.3f);
        imgWrong.setAlpha(0.3f);
    }

    private void checkAnswer() {
        String input = etAnswer.getText().toString();
        if (input.isEmpty()) return;

        int userAnswer = Integer.parseInt(input);

        if (userAnswer == correctAnswer) {
            score++;
            imgCorrect.setAlpha(1.0f);
            imgWrong.setAlpha(0.3f);
        } else {
            imgWrong.setAlpha(1.0f);
            imgCorrect.setAlpha(0.3f);
        }

        tvScore.setText("Score: " + score);
        generateQuestion();
    }
}
