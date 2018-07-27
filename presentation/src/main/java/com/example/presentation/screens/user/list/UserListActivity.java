package com.example.presentation.screens.user.list;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.android.hometasks2.R;
import com.example.android.hometasks2.databinding.ActivityUserListBinding;
import com.example.presentation.base.BaseMvvmActivity;
import com.example.presentation.screens.recycleView.RecyclerViewAdapter;
import com.example.presentation.screens.recycleView.ViewHolder;

public class UserListActivity extends BaseMvvmActivity<UserListViewModel,
        ActivityUserListBinding,
        UserListRouter> {

    private static final String TAG = "AAA UserListActivity";

    public static Intent getIntent(Activity activity){
        return new Intent(activity, UserListActivity.class);
    }

    private ViewHolder.OnItemClickListener listener = new ViewHolder.OnItemClickListener() {
        @Override
        public void onItemClick(String id) {
            router.goToUserDetails(id);
        }
    };

    @Override
    protected UserListViewModel provideViewModel() {
        Log.d(TAG, "provideViewModel: ");
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        Log.d(TAG, "provideLayoutId: ");
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        binding.userListRv.setLayoutManager(new LinearLayoutManager(this));
        viewModel.setAdapter(new RecyclerViewAdapter(this, listener));
        binding.userListRv.setAdapter(viewModel.getAdapter());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(viewModel.isDownloading);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.listContainer.setVisibility(View.VISIBLE);
                        viewModel.getAdapter().setData(viewModel.getUserList());
                    }
                });
            }
        }).start();
        binding.findUserEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.onChangeText(binding.findUserEt.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAdapter().notifyDataSetChanged();
    }
}
