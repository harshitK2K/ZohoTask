package com.example.android.zohotask;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private TextView printView;
    private int flag = 1;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        printView = (TextView) findViewById(R.id.print);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (flag == 1) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    if(progressStatus < 100) {
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                textView.setText(progressStatus + "%" + "COMPLETED");
                            }
                        });
                    }
                    else{
                        flag = 0;
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                textView.setVisibility(View.GONE);
                                printView.setText("Task Has Been Completed");
                            }
                        });
                    }
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
