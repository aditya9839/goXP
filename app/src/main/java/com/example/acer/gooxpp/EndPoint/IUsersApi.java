package com.example.acer.gooxpp.EndPoint;

/**
 * Created by acer on 04-Aug-18.
 */

import com.example.acer.gooxpp.Adapter.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUsersApi {

    @POST("/api/v1/register")
    Call<User> createUser(@Body User user);
}