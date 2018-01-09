package com.example.root.ngabensin.Services;

import com.example.root.ngabensin.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.root.ngabensin.Services.MapsAPI.keys;

/**
 * Created by root on 19/12/17.
 */

public interface MapsAPI {
    String keys = "key=AIzaSyC24ixUxbAPPauZT5TJyo_LgK6NSxXfFig";

    @GET("api/directions/json?"+keys)
    Call<Example> getDirection(@Query("origin") String origin, @Query("destination") String destination);

}
