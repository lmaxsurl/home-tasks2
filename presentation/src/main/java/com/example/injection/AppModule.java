package com.example.injection;

import android.content.Context;
import android.support.annotation.UiThread;
import android.util.Log;

import com.example.data.network.RestService;
import com.example.data.repositories.UserRepositoryImpl;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.repositories.UserRepository;
import com.example.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    public static UserRepository provideUserRepository() {
        return new UserRepositoryImpl(new RestService());
    }

    @Provides
    @Singleton
    public static PostExecutionThread provideUIThread(UIThread uiThread){
        return uiThread;
    }
}
