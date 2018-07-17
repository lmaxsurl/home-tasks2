package com.example.presentation.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.databinding.library.baseAdapters.BR;


public abstract class BaseMvvmActivity<VM extends BaseViewModel, B extends ViewDataBinding>
        extends BaseActivity {

    protected VM viewModel;
    protected B binding;

    /**
     * Use ViewModelProviders.of(this).get(ViewModel.class);
     */
    protected abstract VM provideViewModel();

    protected abstract int provideLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = provideViewModel();

        binding = DataBindingUtil.setContentView(this, provideLayoutId());
        binding.setVariable(BR.viewModel, viewModel);
    }
}
