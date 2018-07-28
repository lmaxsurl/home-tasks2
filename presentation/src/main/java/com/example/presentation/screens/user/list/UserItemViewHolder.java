package com.example.presentation.screens.user.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.hometasks2.databinding.ItemUserBinding;
import com.example.domain.entity.User;
import com.example.presentation.recycler.BaseItemViewHolder;

public class UserItemViewHolder extends BaseItemViewHolder<
        User,
        UserItemViewModel,
        ItemUserBinding> {

    public UserItemViewHolder(UserItemViewModel viewModel, ItemUserBinding binding) {
        super(viewModel, binding);
    }

    public static UserItemViewHolder create(ViewGroup parent, UserItemViewModel viewModel) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new UserItemViewHolder(viewModel, binding);
    }
}
