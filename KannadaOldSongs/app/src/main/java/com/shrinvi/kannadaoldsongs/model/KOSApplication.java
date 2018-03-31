package com.shrinvi.kannadaoldsongs.model;

import android.app.Application;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.shrinvi.kannadaoldsongs.analytics.KOSGoogleAnalytics;

/**
 * Created by shrinvigroup on 25/03/2018.
 */

public class KOSApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KOSUtils.configLocale(this);
        KOSGoogleAnalytics.init(this);
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.InAppAlert).setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .unsubscribeWhenNotificationsAreDisabled(false)
                .init();
    }

    // This fires when a notification is opened by tapping on it or one is received while the app is running.
    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {

            try {
                if (result != null) {
                    //TODO: handle the push click
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

}
