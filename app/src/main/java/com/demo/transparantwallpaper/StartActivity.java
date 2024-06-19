package com.demo.transparantwallpaper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class StartActivity extends AppCompatActivity {
    private static ProgressDialog d;
    @BindView(R.id.RateApp)
    LinearLayout RateApp;
    @BindView(R.id.ShareApp)
    LinearLayout ShareApp;
    SharedPreferences a;

    SharedPrefHelper b;

    @BindView(R.id.startlayout)
    LinearLayout startlayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txtappname)
    TextView txtappname;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);


        AdAdmob adAdmob = new AdAdmob( this);
        adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        adAdmob.FullscreenAd_Counter(this);




        this.txtappname.setText(getResources().getString(R.string.app_name));
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        this.a = getSharedPreferences(SharedPrefHelper.PREFS_NAME, 0);
        this.b = new SharedPrefHelper(this);
        if (!this.a.contains("accepted") || !this.b.getCondi("accepted").booleanValue()) {
            a();
        }
        b();
        d = new ProgressDialog(this);
        d.setMessage("Please Wait......");
        d.setCancelable(false);

        this.startlayout.setOnClickListener(new View.OnClickListener() { // from class: com.demo.example.StartActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {

                    StartActivity.this.startActivity(new Intent(StartActivity.this, MainActivity.class));

            }
        });
        this.ShareApp.setOnClickListener(new View.OnClickListener() { // from class: com.demo.example.StartActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.TEXT", "I got My favorite Live Wallpapers Using" + StartActivity.this.getResources().getString(R.string.app_name) + " click here to install: \nhttp://play.google.com/store/apps/details?id=" + StartActivity.this.getPackageName());
                StartActivity.this.startActivity(Intent.createChooser(intent, "Share link using"));
            }
        });

        this.RateApp.setOnClickListener(new View.OnClickListener() { // from class: com.demo.example.StartActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + StartActivity.this.getPackageName()));
                StartActivity.this.startActivity(intent);
            }
        });
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        ExitDialog();
    }

    public void ExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Take Some Time To Rate Our App If You Like It...Thank You");
        builder.setCancelable(true);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() { // from class: com.demo.example.StartActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            @RequiresApi(api = 16)
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                StartActivity.this.finishAffinity();
            }
        });
        builder.setNeutralButton("Rate App", new DialogInterface.OnClickListener() { // from class: com.demo.example.StartActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + StartActivity.this.getPackageName()));
                StartActivity.this.startActivity(intent);
            }
        });
        builder.create().show();
    }

    private void a() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_term_of_services);
        dialog.setCancelable(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        ((WebView) dialog.findViewById(R.id.web_privacy)).loadUrl(getResources().getString(R.string.privacy_policy_link));
        ((Button) dialog.findViewById(R.id.bt_accept)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.example.StartActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(StartActivity.this.getApplicationContext(), "Terms And Conditions and Accepted", Toast.LENGTH_SHORT).show();
                StartActivity.this.b.setCondi("accepted", true);
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);
    }

    private boolean b() {
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
}
