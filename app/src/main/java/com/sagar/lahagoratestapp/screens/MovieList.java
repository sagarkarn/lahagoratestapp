package com.sagar.lahagoratestapp.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sagar.lahagoratestapp.adapters.MovieListAdapter;
import com.sagar.lahagoratestapp.databinding.FragmentMovieListBinding;
import com.sagar.lahagoratestapp.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MovieList extends Fragment {

    public MovieList() {
        // Required empty public constructor
    }

    private FragmentMovieListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        toolbar enable back button
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getArguments();
        List<Movie> movies = null;
        String name = null;
        if (bundle != null) {
            //noinspection unchecked
            movies = (List<Movie>) bundle.getSerializable("movies");
            name = bundle.getString("name");
        }

        MovieListAdapter adapter = new MovieListAdapter((ArrayList<Movie>) movies);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);

        binding.categoryName.setText(name);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Navigation.findNavController(binding.getRoot()).popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }
}