package com.shrinvi.kannadaoldsongs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.ads.MobileAds;
import com.shrinvi.kannadaoldsongs.analytics.KOSGoogleAnalytics;
import com.shrinvi.kannadaoldsongs.model.KOSUtils;
import com.shrinvi.kannadaoldsongs.R;

public class KOSSplashActivity extends KOSSuperActivity {
    public static final long LOAD_ANIMATION_DELAY_IN_MILLIS = 2 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        KOSUtils.configLocale(this);
        setContentView(R.layout.activity_splash);
        ImageView loadingIv = findViewById(R.id.splash_image_view);
        loadingIv.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchHomeScreen();
            }
        }, LOAD_ANIMATION_DELAY_IN_MILLIS);
        MobileAds.initialize(this, getString(R.string.admob_app_id));
    }

    @Override
    protected void onStart() {
        super.onStart();
        KOSGoogleAnalytics.sendScreenViewEvent("Splash Screen");
    }

    private void launchHomeScreen() {
        Intent homeIntent = new Intent(this, KOSHomeActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
