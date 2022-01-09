package com.sagar.lahagoratestapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sagar.lahagoratestapp.R;
import com.sagar.lahagoratestapp.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

    }
}