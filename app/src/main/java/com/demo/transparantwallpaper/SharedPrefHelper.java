package com.demo.transparantwallpaper;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class SharedPrefHelper {
    public static final String PREFS_NAME = "fun_pref";
    Context a;

    public SharedPrefHelper(Context context) {
        this.a = context;
    }

    public void setCondi(String str, Boolean bool) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putBoolean(str, bool.booleanValue());
        edit.apply();
    }

    public Boolean getCondi(String str) {
        return Boolean.valueOf(this.a.getSharedPreferences(PREFS_NAME, 0).getBoolean(str, false));
    }

    public void setString(String str, String str2) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public String getString(String str) {
        return this.a.getSharedPreferences(PREFS_NAME, 0).getString(str, "");
    }

    public void setintValue(String str, int i) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public void setInt(String str, int i) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public int getInt(String str) {
        return this.a.getSharedPreferences(PREFS_NAME, 0).getInt(str, 0);
    }

    public int getintValue(String str) {
        return this.a.getSharedPreferences(PREFS_NAME, 0).getInt(str, 0);
    }
}
