package com.example.root.ngabensin.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 19/12/17.
 */

public class MapsService {
    private MapsAPI mapsAPI;

    public MapsAPI initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapsAPI = retrofit.create(MapsAPI.class);
        return mapsAPI;
    }
}
