package com.demo.transparantwallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class SplashScreen extends AppCompatActivity {
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() { // from class: com.demo.example.SplashScreen.1
            @Override // java.lang.Runnable
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this, StartActivity.class));
                SplashScreen.this.finish();
            }
        }, 2000L);
    }
}
