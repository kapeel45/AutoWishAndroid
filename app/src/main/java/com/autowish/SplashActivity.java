package com.autowish;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.autowish.CommonUtils.UserSessionManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new UserSessionManager(SplashActivity.this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(i);

                sessionManager.checkLogin();
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}
