package com.javaya.pruebawposs.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.javaya.pruebawposs.R;

import java.util.Timer;
import java.util.TimerTask;

public class FailedActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(FailedActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);

    }

    public void exit(View view) {

        Intent intent = new Intent(FailedActivity.this, MainActivity.class);
        startActivity(intent);
        timer.cancel();

    }
}