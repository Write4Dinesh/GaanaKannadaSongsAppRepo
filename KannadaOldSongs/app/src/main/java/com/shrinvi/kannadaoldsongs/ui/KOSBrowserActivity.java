package com.shrinvi.kannadaoldsongs.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.shrinvi.kannadaoldsongs.analytics.KOSGoogleAnalytics;
import com.shrinvi.kannadaoldsongs.model.KOSConstants;
import com.shrinvi.kannadaoldsongs.model.KOSUtils;
import com.shrinvi.kannadaoldsongs.model.KOSWebViewClient;
import com.shrinvi.kannadaoldsongs.storage.KOSDataStore;
import com.shrinvi.kannadaoldsongs.R;

public class KOSBrowserActivity extends KOSSuperActivity {
    private WebView mKNAWebView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        KOSUtils.configLocale(this);
        mKNAWebView = findViewById(R.id.kna_webview);
        mKNAWebView.getSettings().setAllowContentAccess(true);
        mKNAWebView.getSettings().setJavaScriptEnabled(true);
        mKNAWebView.getSettings().setAppCacheEnabled(true);
        mKNAWebView.getSettings().setSupportZoom(true);
        mKNAWebView.getSettings().setBuiltInZoomControls(true);
        mKNAWebView.getSettings().setDisplayZoomControls(false);
        mKNAWebView.getSettings().setDomStorageEnabled(true);
        mKNAWebView.getSettings().setSupportMultipleWindows(true);

        mKNAWebView.setWebViewClient(new KOSWebViewClient((ProgressBar) findViewById(R.id.progress_spinner)));

        String url = getIntent().getStringExtra(KOSConstants.INTENT_EXTRA_URL);
        String title = getIntent().getStringExtra(KOSConstants.INTENT_EXTRA_TITLE);
        if (title != null && !title.isEmpty()) {
            getSupportActionBar().setTitle(title);
        }
        if (url != null && !url.isEmpty()) {
            mKNAWebView.loadUrl(url);
        }
        initBannerAd();
        initInterstitialAd();
    }

    private void initInterstitialAd() {
        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id_for_browse_interstitial));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                finish();//close browser activity
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (mKNAWebView.canGoBack()) {
            mKNAWebView.goBack();
        } else {
            showInterstitialAd();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        KOSGoogleAnalytics.sendScreenViewEvent("Browser Screen");
    }

    private void initBannerAd() {
        AdView adView = findViewById(R.id.browser_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void showInterstitialAd() {
        long timeElapsedInMillis = System.currentTimeMillis() - KOSDataStore.getInstance(this).getLastAddShowedTime();
        if (interstitialAd != null && interstitialAd.isLoaded() && (timeElapsedInMillis > 60 * 1000)) {
            KOSGoogleAnalytics.sendCustomEvent("AdMob", "ShowInterstitial");
            interstitialAd.show();
            KOSDataStore.getInstance(this).storeLastAddShowedTime(System.currentTimeMillis());
        } else {
            KOSGoogleAnalytics.sendCustomEvent("AdMob", "ShowInterstitial:not loaded yet");
            finish();

        }
    }
}
