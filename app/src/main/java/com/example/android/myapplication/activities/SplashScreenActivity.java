package com.example.android.myapplication.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.myapplication.R;

/**
 * Created by Jonatas on 01/07/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                showVisitFormActivity();
            }
        }, 2000);
    }

    public void showVisitFormActivity(){
        Intent intent = new Intent(SplashScreenActivity.this,VisitListActivity.class);
        startActivity(intent);
        finish();
    }




}
