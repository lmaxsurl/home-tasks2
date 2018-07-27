package com.example.presentation.screens.user;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class CustomSetters {
    private static final String KEY_MEN = "men";
    private static final String KEY_WOMEN = "women";
    private static final int MAN_COLOR = Color.rgb(70, 130, 180);
    private static final int WOMEN_COLOR = Color.rgb(255, 20, 147);

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
                view.setBackgroundColor(MAN_COLOR);
            else if (gender.equalsIgnoreCase(KEY_WOMEN))
                view.setBackgroundColor(WOMEN_COLOR);
            else view.setBackgroundColor(Color.RED);
    }
}
