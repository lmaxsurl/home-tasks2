package com.example.presentation.screens.user.list;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.domain.entity.User;
import com.example.presentation.recycler.BaseItemViewHolder;
import com.example.presentation.recycler.BaseRecyclerViewAdapter;

public class UserListAdapter extends BaseRecyclerViewAdapter<User, UserItemViewModel> {
    @NonNull
    @Override
    public BaseItemViewHolder<User, UserItemViewModel, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserItemViewHolder.create(parent, new UserItemViewModel());
    }
}
