package com.myapp.vinz.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class splashActivity extends Activity {
    private static int SPLASH_TIME = 6000;
    ImageView image;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void startAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        anim.reset();
        //set animation to the background
        Object object = (RelativeLayout) findViewById(R.id.relative);
        ((RelativeLayout)object).clearAnimation();
        ((RelativeLayout)object).startAnimation(anim);
        //set animation for w3school.com
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        anim.reset();
        object = (ImageView) findViewById(R.id.imageView2);
        ((ImageView)object).clearAnimation();
        ((ImageView)object).startAnimation(anim);
        //set animation for logo
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_second);
        anim.reset();
        object = (ImageView) findViewById(R.id.imageView1);
        ((ImageView)object).clearAnimation();
        ((ImageView)object).startAnimation(anim);
        //setting animation for text:
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        anim.reset();
        object = (TextView) findViewById(R.id.text1);
        ((TextView)object).clearAnimation();
        ((TextView)object).startAnimation(anim);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_activity);

        this.image = (ImageView) findViewById(R.id.imageView2);
        getWindow().setFormat(1);

        startAnimation();

        //code for splash screen timeout:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this, MainActivity.class);
                splashActivity.this.startActivity(intent);
                splashActivity.this.finish();
            }
        }, 6000L);
    }
}