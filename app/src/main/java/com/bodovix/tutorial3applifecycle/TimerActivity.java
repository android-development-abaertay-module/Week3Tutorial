package com.bodovix.tutorial3applifecycle;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    TextView timerTV;
    //set when explicit intent called
    private long timerStart;
    private  int durationSeconds;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timerTV = findViewById(R.id.timerTextView);

        //get extras from intent source
        Intent i = getIntent();
        timerStart = i.getExtras().getLong("timerStart", 0);
        durationSeconds = i.getExtras().getInt("durationSeconds",60);

        handler = new Handler()
        {
            public void handleMessage (Message msg){
                timerTV.setText(msg.getData().getString("Time"));
            }
        };
        runnable = new Runnable(){
            public void run(){
                updateTime();
            }
        };

        //start timer thread
        handler.postDelayed(runnable,1000);
    }

    private void updateTime() {
        // Calculate seconds elapsed since the start of the timer
        long mills = System.currentTimeMillis() - timerStart;
        int seconds = (int) (mills/1000);
        // Calculate the remaining seconds
        int secondsLeft = durationSeconds - seconds;
        // Convert seconds to minutes
        int minutesLeft = secondsLeft/60;
        // Calculate the leftover to be used as seconds
        secondsLeft = secondsLeft % 60;
        String timeString = minutesLeft + ":" + secondsLeft;
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("Time", timeString);
        message.setData(bundle);
        handler.sendMessage(message);
        handler.postDelayed(runnable, 1000);
    }
}
