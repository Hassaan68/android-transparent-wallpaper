package com.demo.transparantwallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnSetWallpaper)
    TextView btnSetWallpaper;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AdAdmob adAdmob = new AdAdmob( this);
        adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        adAdmob.FullscreenAd_Counter(this);


        a();
        this.toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }

        this.btnSetWallpaper.setOnClickListener(new View.OnClickListener() { // from class: com.demo.example.MainActivity.1
            @Override // android.view.View.OnClickListener
            @SuppressLint({"WrongConstant"})
            public void onClick(View view) {
                Log.e("TAG", "Constant wall" + Constants.wallpaper);
                Constants.show = false;
                try {
                    WallpaperManager.getInstance(MainActivity.this.getApplicationContext()).clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
                intent.setFlags(2);
                intent.setFlags(1);
                intent.putExtra("SET_LOCKSCREEN_WALLPAPER", true);
                Constants.act = MainActivity.this;
                intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", new ComponentName(MainActivity.this, MyWallpaperService.class));
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private boolean a() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA");
        int checkSelfPermission2 = ContextCompat.checkSelfPermission(this, "android.permission.INTERNET");
        int checkSelfPermission3 = ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
        int checkSelfPermission4 = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        int checkSelfPermission5 = ContextCompat.checkSelfPermission(this, "android.permission.SET_WALLPAPER");
        int checkSelfPermission6 = ContextCompat.checkSelfPermission(this, "android.permission.SET_WALLPAPER_HINTS");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.CAMERA");
        }
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.INTERNET");
        }
        if (checkSelfPermission4 != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission3 != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission5 != 0) {
            arrayList.add("android.permission.SET_WALLPAPER");
        }
        if (checkSelfPermission6 != 0) {
            arrayList.add("android.permission.SET_WALLPAPER_HINTS");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        return false;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        startActivity(new Intent(this, StartActivity.class));
    }
}
