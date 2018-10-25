package id.beneranindonesia.pusaka.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.OnBoard.OnBoardingActivity;
import id.beneranindonesia.pusaka.utils.Session;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Session.getInstance().initalizeSession(this);
        if (Session.getInstance().isLogin(getApplicationContext())) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
        }
    }
}
