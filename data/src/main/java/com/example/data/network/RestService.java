package com.example.data.network;

import android.util.Log;
import com.example.data.entity.HttpError;
import com.example.data.entity.UserRequest;
import com.example.data.entity.UserResponse;
import com.example.domain.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestService {

    private static final String TAG = "AAA RestService";
    private RestApi restApi;
    private Gson gson;
    private static final String REQUEST_URL =
            "https://api.backendless.com/65C0BC34-5824-47F9-FFC1-5D54CD46B300/C25E3327-4A96-9950-FF5A-77771B629B00/";
    private ErrorParserTransformer errorParserTransformer;

    @Inject
    public RestService() {
        Log.d(TAG, "RestService: constructor");

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(REQUEST_URL)
                .client(okHttpClient)
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer(gson);
    }

    public Observable<List<UserResponse>> getAllUsers() {
        Log.d(TAG, "getAllUsers: ");
        return restApi
                .getAllUsers()
                .compose(errorParserTransformer.<List<UserResponse>, HttpError>parseHttpError());
    }

    public Observable<UserResponse> getUser(String id) {
        Log.d(TAG, "getUser: ");
        return restApi
                .getUser(id)
                .compose(errorParserTransformer.<UserResponse, HttpError>parseHttpError());
    }

    public Completable updateUser(UserRequest user) {
        Log.d(TAG, "updateUser: ");
        return restApi
                .updateUser(user, user.getObjectId());
                //.compose(errorParserTransformer.<CompletableTransformer, HttpError>parseHttpError());
    }

    public Completable deleteUser(String id) {
        Log.d(TAG, "deleteUser: ");
        return restApi
                .deleteUser(id);
                //.compose(errorParserTransformer.<DeleteResponse, HttpError>parseHttpError());
    }

    public Completable addUser(UserRequest user){
        return restApi
                .addUser(user);
    }
}