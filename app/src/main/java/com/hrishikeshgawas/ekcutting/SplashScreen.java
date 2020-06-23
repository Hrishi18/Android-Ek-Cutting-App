package com.hrishikeshgawas.ekcutting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    TextView name, slogan;
    ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

        name = findViewById(R.id.splash_screen_name);
        slogan = findViewById(R.id.splash_screen_tagline);
        logo = findViewById(R.id.splash_screen_logo);

        name.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
        logo.setAnimation(topAnim);

        int SPLASH_SCREEN = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_SCREEN);

        }
    }

