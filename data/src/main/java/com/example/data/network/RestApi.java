package com.example.data.network;

import com.example.data.entity.UserRequest;
import com.example.data.entity.UserResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestApi {

    @GET("data/User")
    Observable<List<UserResponse>> getAllUsers();

    @GET("data/User/{id}")
    Observable<UserResponse> getUser(@Path("id") String id);

    @PUT("data/User/{id}")
    Completable updateUser(@Body UserRequest user, @Path("id") String id);

    @POST("data/User")
    Completable addUser(@Body UserRequest user);

    @DELETE("data/User/{id}")
    Completable deleteUser(@Path("id") String id);
}
