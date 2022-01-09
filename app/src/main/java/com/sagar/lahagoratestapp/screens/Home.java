package com.sagar.lahagoratestapp.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sagar.lahagoratestapp.R;
import com.sagar.lahagoratestapp.adapters.CategoryAdapter;
import com.sagar.lahagoratestapp.databinding.FragmentHomeBinding;
import com.sagar.lahagoratestapp.network.MyViewModel;
import com.sagar.lahagoratestapp.util.Loader;

import java.util.ArrayList;
import java.util.Objects;


public class Home extends Fragment {

    private static final String TAG = "Home";


    public Home() {
        // Required empty public constructor
    }

    private FragmentHomeBinding binding;
    private MyViewModel myViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        title
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        Loader loader = new Loader(requireContext());

        myViewModel = new ViewModelProvider(getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).get(MyViewModel.class);

        CategoryAdapter adapter = new CategoryAdapter((category, movies) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("movies",new ArrayList<>(movies));
            bundle.putString("name",category.getCategory_name());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home2_to_movieList,bundle);
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);


        //load data

        loader.show();
        myViewModel.geMovies().observe(getViewLifecycleOwner(), listResponse -> {

            if(listResponse.error != null){
                loader.hide();
                Toast.makeText(requireContext(), listResponse.error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            myViewModel.getCategories().observe(getViewLifecycleOwner(),listResponse1 -> {

                Log.d(TAG, "onViewCreated: " + listResponse1);
                if(listResponse1.error != null){
                    loader.hide();
                    Toast.makeText(requireContext(), listResponse1.error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter.setData(listResponse1.success, listResponse.success);
                loader.hide();
            });


        });

    }
}