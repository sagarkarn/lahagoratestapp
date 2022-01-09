package com.sagar.lahagoratestapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.lahagoratestapp.R;
import com.sagar.lahagoratestapp.databinding.LayoutHomeLatestMovieBinding;
import com.sagar.lahagoratestapp.databinding.LayoutHomeMovieBinding;
import com.sagar.lahagoratestapp.model.Category;
import com.sagar.lahagoratestapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.StreamViewHolder> {

    List<Movie> movies = new ArrayList<>();
    boolean isLatest;
    public MovieAdapter(boolean isLatest) {
        this.isLatest = isLatest;
    }


    @NonNull
    @Override
    public StreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(isLatest){
            LayoutHomeLatestMovieBinding binding = LayoutHomeLatestMovieBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            return new StreamViewHolder(binding.getRoot());
        }
        else {
            LayoutHomeMovieBinding binding = LayoutHomeMovieBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            return new StreamViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull StreamViewHolder holder, int position) {
//        Log.d("TAG", "onBindViewHolder: " + movies.get(position));
        Movie movie = movies.get(position);
        try{
                Picasso.get().load(movie.getStreamIcon()).centerCrop().fit().into(holder.image);
            }
            catch (Exception e){
                Log.e("TAG", "onBindViewHolder: " + movie );
            }

        String name = movie.getName().replaceAll("\\(\\d\\d\\d\\d\\)","");
        holder.title.setText(name.trim());

        holder.year.setText(movie.getName().replace(name,"").trim());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setData(List<Movie> collectMovies) {
        this.movies = collectMovies;
        notifyDataSetChanged();
    }

    public static class StreamViewHolder extends RecyclerView.ViewHolder {
         ImageView image;
        TextView year,title;
        public StreamViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            year = itemView.findViewById(R.id.year);
            title = itemView.findViewById(R.id.title);
        }
    }

}
