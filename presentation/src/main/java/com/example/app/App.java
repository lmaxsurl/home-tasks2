package com.example.app;

import android.app.Application;
import android.util.Log;

import com.example.injection.AppComponent;
import com.example.injection.AppModule;
import com.example.injection.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static final String TAG = "AAA App";
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        Log.d(TAG, "getAppComponent: ");
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        appComponent = DaggerAppComponent
                .builder()
                .build();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
