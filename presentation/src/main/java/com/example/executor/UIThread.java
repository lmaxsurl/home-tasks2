package com.example.executor;

import android.util.Log;

import com.example.domain.executors.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UIThread implements PostExecutionThread {

    private static final String TAG = "AAA UIThread";

    @Inject
    public UIThread() {
        Log.d(TAG, "UIThread: constructor");
    }

    @Override
    public Scheduler getScheduler() {
        Log.d(TAG, "getScheduler: ");
        return AndroidSchedulers.mainThread();
    }
}
