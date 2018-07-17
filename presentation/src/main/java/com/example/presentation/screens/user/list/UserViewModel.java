package com.example.presentation.screens.user.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import com.example.presentation.base.BaseViewModel;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class UserViewModel extends BaseViewModel {

    private final int MALE_COLOR = Color.rgb(70, 130, 180);
    private final int FEMALE_COLOR = Color.rgb(255, 20, 147);
    public ObservableField<String> firstName = new ObservableField<>("Mark");
    public ObservableField<String> surname = new ObservableField<>("Fender");
    public ObservableField<String> gender = new ObservableField<>("Male");
    public ObservableField<String> age = new ObservableField<>("23");
    public ObservableField<String> imageUrl = new ObservableField<>("http://images.hellokids.com/_uploads/_tiny_galerie/20130520/dhh_how-to-draw-mike-wazowski-easy-tutorial-drawing.png");
    public ObservableField<Integer> backgroundColor = new ObservableField<>(MALE_COLOR);

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.get()
                .load(url)
                .transform(new CropCircleTransformation())
                .into(view);
    }

    @BindingAdapter("android:background")
    public static void setBackground(View view, int color) {
        view.setBackgroundColor(color);
    }
}

