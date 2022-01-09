package com.sagar.lahagoratestapp.util;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.sagar.lahagoratestapp.databinding.LayoutLoaderBinding;

public class Loader {
    private AlertDialog dialog;
    private Context context;

    public Loader(Context context){
        this.context = context;
    }

    public void show(){
        if (dialog == null){
            LayoutLoaderBinding binding = LayoutLoaderBinding.inflate(LayoutInflater.from(context));
            dialog = new AlertDialog.Builder(context, com.sagar.lahagoratestapp.R.style.TransParentDialog)
                    .setView(binding.getRoot())
                    .setCancelable(false)
                    .create();
        }
        dialog.show();
    }
    public void hide(){
        if(dialog != null){
            if(dialog.isShowing()){
                dialog.hide();
            }
        }
    }
}
