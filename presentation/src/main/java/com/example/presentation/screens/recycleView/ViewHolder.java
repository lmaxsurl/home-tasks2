package com.example.presentation.screens.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.hometasks2.databinding.ItemUserBinding;
import com.example.domain.entity.User;

public class ViewHolder extends RecyclerView.ViewHolder {
    private ItemUserBinding dataBinding;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public ViewHolder(final ItemUserBinding dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
        dataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                        listener.onItemClick(dataBinding.getUser().getObjectId());
                }
            }
        });
    }

    public void bind(User user){
        dataBinding.setUser(user);
    }

    public ItemUserBinding getDataBinding() {
        return dataBinding;
    }
}
