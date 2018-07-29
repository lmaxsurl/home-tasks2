package com.example.presentation.screens.user.homeTask9User;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.app.App;
import com.example.domain.usecases.GetListUserUseCase;
import com.example.presentation.base.BaseViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class UserHomeTask9ViewModel extends BaseViewModel {

    @Inject
    public GetListUserUseCase listUserUseCase;
    public ObservableField<String> firstName = new ObservableField<>("Mark");
    public ObservableField<String> surname = new ObservableField<>("Fender");
    public ObservableField<String> gender = new ObservableField<>("Men");
    public ObservableField<String> age = new ObservableField<>("23");
    public ObservableField<String> imageUrl = new ObservableField<>(
            "http://images.hellokids.com/_uploads/_tiny_galerie/20130520/dhh_how-to-draw-mike-wazowski-easy-tutorial-drawing.png");

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }
}

