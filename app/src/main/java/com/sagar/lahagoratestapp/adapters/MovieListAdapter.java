package com.sagar.lahagoratestapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.lahagoratestapp.databinding.LayoutSeeMoreMovieBinding;
import com.sagar.lahagoratestapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {
    private final ArrayList<Movie> movies;

    public MovieListAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSeeMoreMovieBinding binding = LayoutSeeMoreMovieBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MovieListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Movie movie = movies.get(position);
        try{
            Picasso.get().load(movie.getStreamIcon()).centerCrop().fit().into(holder.binding.image);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String name = movie.getName().replaceAll("\\(\\d\\d\\d\\d\\)","");
        holder.binding.title.setText(name.trim());

        holder.binding.year.setText(movie.getName().replace(name,"").trim());
        holder.binding.rating.setText(movie.getRating5based());
        if(movie.getRating5based() != null){
            if(movie.getRating5based().trim().length() > 0){
                float rating = Float.parseFloat(movie.getRating5based());
                holder.binding.ratingStar.setRating(rating);
            }
        }
    }

    @Override
    public int getItemCount() {
        return movies != null? movies.size() : 0;
    }

    public static class MovieListViewHolder extends RecyclerView.ViewHolder {
        LayoutSeeMoreMovieBinding binding;
        public MovieListViewHolder(@NonNull LayoutSeeMoreMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
