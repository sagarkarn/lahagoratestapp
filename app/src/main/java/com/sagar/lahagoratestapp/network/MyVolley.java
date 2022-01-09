package com.sagar.lahagoratestapp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sagar.lahagoratestapp.BuildConfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MyVolley {
    private int timeOut;
    private int retry;
    RequestQueue queue ;
    public MyVolley(Context context){
        queue = Volley.newRequestQueue(context);
    }
    public void requestParams(HashMap<String,String> params, OnSuccessListener successListener,OnErrorListener errorListener){
        StringRequest request = new StringRequest(Request.Method.POST,BuildConfig.BASE_URL, successListener::onSuccess, errorListener::onError){
            @NonNull
            @Override
            protected Map<String, String> getParams(){
                params.put("username","jum");
                params.put("password","quin");
                Log.d("TAG", "getParams: " + params);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("Accept","*/*");
                return headers;
            }
        };

//        start global setting on string request

//        end global setting on string request

        queue.add(request);
    }
    public interface OnSuccessListener{
        void onSuccess(String response);
    }
    public interface OnErrorListener{
        void onError(VolleyError error);
    }
}
