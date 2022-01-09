package com.sagar.lahagoratestapp.network;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sagar.lahagoratestapp.model.Category;
import com.sagar.lahagoratestapp.model.Response;
import com.sagar.lahagoratestapp.model.Movie;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private final MyRepo repo;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repo = new MyRepo(application);
    }

    public MutableLiveData<Response<List<Category>>> getCategories(){
        return repo.getCategories();
    }
    public MutableLiveData<Response<List<Movie>>> geMovies(){
        return repo.getMovies();
    }

}
