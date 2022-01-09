package com.sagar.lahagoratestapp.network;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sagar.lahagoratestapp.model.Category;
import com.sagar.lahagoratestapp.model.Response;
import com.sagar.lahagoratestapp.model.Movie;

import java.util.HashMap;
import java.util.List;

public class MyRepo {

    private static final String TAG = "MyRepo";

    MutableLiveData<Response<List<Category>>> categories = new MutableLiveData<>();;
    MutableLiveData<Response<List<Movie>>> streams = new MutableLiveData<>();
    private final MyVolley volley;
    MyRepo(Application application){
        Context context = application.getApplicationContext();
        volley = new MyVolley(context);
    }

    MutableLiveData<Response<List<Category>>> getCategories(){

        HashMap<String,String> params = new HashMap<>();
        params.put("action","get_vod_categories");
        volley.requestParams(params,response -> {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            List<Category> categoryList = gson.fromJson(response,new TypeToken<List<Category>>(){}.getType());
            Log.d(TAG, "getCategories: " + categoryList);
            categories.setValue(new Response<>(categoryList,null));
        }, error -> {
            categories.setValue(new Response<>(null,error));
            Log.d(TAG, "getCategories: error" + error.networkResponse.statusCode);
        });
        return  categories;
    }

    public MutableLiveData<Response<List<Movie>>> getMovies() {


        HashMap<String,String> params = new HashMap<>();
        params.put("action","get_vod_streams");
        volley.requestParams(params,response -> {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            List<Movie> movieList = gson.fromJson(response,new TypeToken<List<Movie>>(){}.getType());
            streams.setValue(new Response<>(movieList,null));

        }, error -> streams.setValue(new Response<>(null,error)));
        return  streams;
    }
}
