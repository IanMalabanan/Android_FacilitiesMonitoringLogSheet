package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    private Timer timer;
    ProgressBar progressBar;
    int i = 0;
    TextView tViewLoading;

    ClsUtils clsUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.pbLoading);


        progressBar.setProgress(0);

        tViewLoading = (TextView) findViewById(R.id.tViewLoading);

        tViewLoading.setText("");

        clsUtils = new ClsUtils();

        if (!clsUtils.HasConnection(this)) {
            clsUtils.NoInternetBuilDialog(this).show();

            progressBar.setVisibility(View.GONE);

        } else {

            progressBar.setVisibility(View.VISIBLE);

            final long period = 30;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //this repeats every 100 ms
                    if (i < 100) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tViewLoading.setText("Loading... " + String.valueOf(i) + "%");
                            }
                        });
                        progressBar.setProgress(i);
                        i++;
                    } else {
                        //closing the timer
                        timer.cancel();
                        Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                        startActivity(intent);
                        // close this activity
                        finish();
                    }
                }
            }, 0, period);
        }

    }
}
