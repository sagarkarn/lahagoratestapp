package com.sagar.lahagoratestapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.lahagoratestapp.databinding.LayoutItemCategoryBinding;
import com.sagar.lahagoratestapp.model.Category;
import com.sagar.lahagoratestapp.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories = new ArrayList<>();
    private List<Movie> movies;
    private final OnClickListener onClickListener;
    public CategoryAdapter(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCategoryBinding binding = LayoutItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        if(position == 0){
            holder.setIsRecyclable(false);
        }
        else {
            holder.setIsRecyclable(true);
        }
        Category category = categories.get(position);
        holder.binding.title.setText(category.getCategory_name());


        MovieAdapter movieAdapter = null;
        if(holder.getLayoutPosition() == 0 && holder.getAdapterPosition() == 0) {
            movieAdapter = new MovieAdapter(holder.getAdapterPosition() == 0);
        }
        else{
            movieAdapter = new MovieAdapter(false);
        }
        List<Movie> movieStream = movies.stream().filter(movie -> movie.getCategoryId().equals(category.getCategoryId())).collect(Collectors.toList());
        movieAdapter.setData(movieStream.subList(0, Math.min(movieStream.size() - 1, 10)));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.binding.getRoot().getContext(),LinearLayoutManager.HORIZONTAL,false);



        holder.binding.recyclerView.setLayoutManager(linearLayoutManager);

        holder.binding.recyclerView.setAdapter(movieAdapter);


//        see more clicked
        holder.binding.seeMore.setOnClickListener(view -> {
            onClickListener.seeMoreClicked(category,movieStream);
        });

    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public void setData(List<Category> categories, List<Movie> movies) {
        this.categories = categories;
        this.movies = movies;
        notifyDataSetChanged();
    }



    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        LayoutItemCategoryBinding binding;

        public CategoryViewHolder(@NonNull LayoutItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickListener{
        void seeMoreClicked(Category category, List<Movie> movies);
    }
}
