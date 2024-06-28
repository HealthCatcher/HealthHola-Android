package com.hsfa.hearur_android;

import android.app.Application;
import android.content.SharedPreferences;

import com.navercorp.nid.NaverIdLoginSDK;

public class App extends Application {

    public static final String TAG = "OAuthApplication";

    private static final String PREF_NAME = "my_app_pref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        NaverIdLoginSDK.INSTANCE.init(this);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
