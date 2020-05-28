package com.sootbas.sootbasapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import com.sootbas.sootbasapp.ui.activity.TrendingNewsActivity;



public class SplashScreenActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.launch(SplashScreenActivity.this);
//                TrendingNewsActivity.launch(SplashScreenActivity.this);


                finish();
            }
        }, 2000);


    }


}
