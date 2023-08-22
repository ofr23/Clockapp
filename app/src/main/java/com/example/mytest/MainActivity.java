package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        if (!isRunning) {
            isRunning = true;
            runnable = new Runnable() {
                @Override
                public void run() {
                    seconds++;
                    int hours = seconds / 3600;
                    int minutes = (seconds % 3600) / 60;
                    int secs = seconds % 60;
                    String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
                    timerTextView.setText(time);
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private void stopTimer() {
        if (isRunning) {
            isRunning = false;
            handler.removeCallbacks(runnable);
        }
    }

    private void resetTimer() {
        stopTimer();
        seconds = 0;
        timerTextView.setText("00:00:00");
    }
}
