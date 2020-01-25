package com.example.nav_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView welcome=findViewById(R.id.welcome);
        ImageView splashimg=findViewById(R.id.splashimg);

        Animation animation= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.myanimation);
        welcome.startAnimation(animation);
        splashimg.startAnimation(animation);
        final Intent intent=new Intent(SplashScreenActivity.this,SignIn.class);
        Thread timer=new Thread()
        {
            public void run()
            {
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {
                    startActivity(intent);
                    finish();
                }
            }


        };
        timer.start();
    }
}
