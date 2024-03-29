package com.example.outland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SystemClock.sleep(500);
        Intent loginIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
