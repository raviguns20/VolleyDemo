package com.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Admin on 19-11-2015.
 */
public class TestApplication extends Application {

    private static TestApplication mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        getRequestQueue();
        //mContext = getApplicationContext();
        //getImageLoader();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized TestApplication getInstance() {
        if (mInstance == null) {
            mInstance = new TestApplication();
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
