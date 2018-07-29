package com.example.presentation.utils;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class BindingAdapters {
    private static final String KEY_MEN = "men";
    private static final String KEY_WOMEN = "women";

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.get()
                .load(url)
                .resize(512, 512)
                .centerCrop()
                .transform(new CropCircleTransformation())
                .into(view);
    }

    @BindingAdapter("android:background")
    public static void setBackground(View view, String gender) {
        if (gender != null)
            if (gender.equalsIgnoreCase(KEY_MEN))
                view.setBackgroundColor(Color.BLUE);
            else if (gender.equalsIgnoreCase(KEY_WOMEN))
                view.setBackgroundColor(Color.MAGENTA);
            else view.setBackgroundColor(Color.RED);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean isVisible){
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
