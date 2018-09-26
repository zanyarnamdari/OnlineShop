package com.modavagostar.order.app;

/**
 * Created by HP on 2018/02/02.
 */

import android.app.Application;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.modavagostar.order.FontsOverride;
import com.onesignal.OneSignal;
//import com.onesignal.OneSignal;
//import com.onesignal.OneSignal;


public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)

                .init();
//        FontsOverride.setDefaultFont(this, "DEFAULT", "iran_sans_ultra_light_web.ttf");
//
//        FontsOverride.setDefaultFont(this, "MONOSPACE", "iran_sans_ultra_light_web.ttf");
//        FontsOverride.setDefaultFont(this, "SERIF", "iran_sans_ultra_light_web.ttf");
//        FontsOverride.setDefaultFont(this, "SANS_SERIF", "iran_sans_ultra_light_web.ttf");


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //setLocale();
    }


    public static synchronized AppController getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
