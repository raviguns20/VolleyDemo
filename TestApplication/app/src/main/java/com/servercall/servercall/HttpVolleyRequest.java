package com.servercall.servercall;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.application.TestApplication;


public class HttpVolleyRequest {

    Context mContext;
    HashMap<String, String> map;
    MyListener listener;
    TestApplication app;

    //TestApplication app;
    JSONObject jsonRequest;
    String requestUrl;
    RequestQueue rQueue;
    ProgressDialog dialog;

    public HttpVolleyRequest(Context mContext, JSONObject jsonRequest, String requestUrl, MyListener listener) {
        //IOUtils.printLogError(jsonRequest.toString());
        this.mContext = mContext;
        this.requestUrl = requestUrl;
        this.jsonRequest = jsonRequest;
        this.listener = listener;
        app = (TestApplication)this.mContext.getApplicationContext();
        doPostOperation();
    }
    public HttpVolleyRequest(Context mContext, MyListener listener){

        this.mContext=mContext;
        dialog=new ProgressDialog(mContext);
        dialog.setMessage("Loading...");
        rQueue= Volley.newRequestQueue(mContext);
        this.listener = listener;
        app = (TestApplication)this.mContext.getApplicationContext();
        Log.d("Rex","Do string request");
        doStringRequest();
    }

    public HttpVolleyRequest(Context mContext, JSONObject jsonRequest, String requestUrl, MyListener listener, int msgVisible) {
        //IOUtils.printLogError(jsonRequest.toString());
        this.mContext = mContext;
        this.requestUrl = requestUrl;
        this.jsonRequest = jsonRequest;
        this.listener = listener;
        app = (TestApplication)this.mContext.getApplicationContext();
        doPostOperationWithoutLoadingMsg();
    }

    //---------------------------- POST JSON OBJECT REQUEST------------------------------
    public void doPostOperation() {
        //IOUtils.startLoadingPleaseWait(mContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, requestUrl, jsonRequest, listenerSuccess, listenerError);
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        //app.addToRequestQueue(request, "post");
    }

    public  void doStringRequest(){
        dialog.show();
        StringRequest strRequest=new StringRequest(Request.Method.GET,"http://globusstores.incomenterprise.com/mobileapp/home",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(dialog!=null)
                            dialog.cancel();
                        listener.success(s);
                        Log.d("Rex",s);
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(dialog!=null)
                    dialog.cancel();
                listener.failure(volleyError.toString());
                Log.d("Rex",volleyError.toString());
            }
        });

        rQueue.add(strRequest);
        //strRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
    }


    public void doPostOperationWithoutLoadingMsg() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, requestUrl, jsonRequest, listenerSuccess, listenerError);
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        //app.addToRequestQueue(request, "post");
    }


    //----------------------- LISTENER'S---------------------------------------------

    Listener<JSONObject> listenerSuccess = new Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject jsonObject) {
            //IOUtils.printLogError(jsonObject.toString());
            //IOUtils.stopLoading();
            listener.success(jsonObject);
        }

    };

    Response.ErrorListener listenerError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError e) {
            //IOUtils.printLogError(e.getMessage());
            //IOUtils.stopLoading();
            listener.failure(e.getMessage());
        }
    };

}
