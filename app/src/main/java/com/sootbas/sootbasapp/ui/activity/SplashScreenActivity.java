package com.sootbas.sootbasapp.ui.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;


public class SplashScreenActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                MainActivity.launch(SplashScreenActivity.this);
                TrendingNewsActivity.launch(SplashScreenActivity.this);


                finish();
            }
        }, 2000);


    }


}
