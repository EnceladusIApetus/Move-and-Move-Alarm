package com.fatel.mamtv1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by oat90 on 3/11/2558.
 */
public class HttpConnector{

    private RequestQueue requestQueue;
    private static Context ctx;
    private boolean isDownloadedComplete;
    private String data;
    private String errorStatus;

    public HttpConnector(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
        isDownloadedComplete = false;
        data = new String("");
        errorStatus = new String("");
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

    public boolean isDownloadedComplete()
    {
        return isDownloadedComplete;
    }

    public String getData()
    {
        return data;
    }

    public String getErrorStatus()
    {
        return errorStatus;
    }

    public void getDataFromServer(String url,int method)
    {
        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        data = s;
                        isDownloadedComplete = true;
                        Intent intent = new Intent(ctx, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("complete", s);
                        intent.putExtras(bundle);
                        ctx.sendBroadcast(intent);
                    }
                },new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError volleyError) {
                         errorStatus = volleyError.toString();
                         isDownloadedComplete = false;
                        /* Intent intent = new Intent(ctx, MainActivity.class);
                         intent.putExtra("error", volleyError.toString());
                         ctx.startActivity(intent);*/
                    }
        });
        addToRequestQueue(stringRequest);
    }
}
