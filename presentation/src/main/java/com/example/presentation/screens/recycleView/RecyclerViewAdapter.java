package com.example.presentation.screens.recycleView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.hometasks2.databinding.ItemUserBinding;
import com.example.domain.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends Adapter<ViewHolder> {

    private Context context;
    private ArrayList<User> userList;
    private LayoutInflater layoutInflater;
    private ViewHolder.OnItemClickListener listener;

    public RecyclerViewAdapter(Context context, ViewHolder.OnItemClickListener listener){
        this.context = context;
        this.listener = listener;
    }

    public void setData(ArrayList<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemUserBinding binding = ItemUserBinding
                .inflate(layoutInflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        viewHolder.setOnClickListener(listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList == null? 0: userList.size();
    }
}
