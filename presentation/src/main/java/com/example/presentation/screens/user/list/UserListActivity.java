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
import com.example.presentation.screens.user.view.UserInfoActivity;
import com.example.presentation.screens.user.view.UserInfoViewModel;
import com.example.presentation.utils.Extras;

public class UserListActivity extends BaseMvvmActivity<UserListViewModel,
        ActivityUserListBinding,
        UserListRouter> {

    public static final int REQUEST_ADD_TYPE = 0;
    public static final int REQUEST_INFO_TYPE = 1;

    public static Intent getIntent(Activity activity) {
        return new Intent(activity, UserListActivity.class);
    }

    @Override
    protected UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.userListRv.setLayoutManager(new LinearLayoutManager(this));
        binding.userListRv.setAdapter(viewModel.adapter);
        binding.userListRv.setHasFixedSize(true);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String userId;
        if (data != null && data.getStringExtra(Extras.EXTRA_OBJECT_ID) != null)
            userId = data.getStringExtra(Extras.EXTRA_OBJECT_ID);
        else {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == REQUEST_ADD_TYPE) {
            if (resultCode == RESULT_OK) {
                viewModel.onAddUser(userId);
            }
        } else if (requestCode == REQUEST_INFO_TYPE) {
            if (resultCode == Extras.RESULT_EDIT) {
                viewModel.onEditUser(userId);
            }
            if (resultCode == Extras.RESULT_DELETE) {
                viewModel.onDeleteUser(userId);
            }
        }
    }
}
