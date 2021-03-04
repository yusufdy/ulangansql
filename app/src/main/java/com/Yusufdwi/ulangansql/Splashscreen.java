package com.yusufdwi.ulangansql;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;

import com.yusufdwi.a03_yusufulangansql.R;


public class Splashscreen extends Activity {
    int SPLASH_SCREEN_TIME = 7000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIME);
    }
}
