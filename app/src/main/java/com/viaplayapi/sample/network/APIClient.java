package com.viaplayapi.sample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://content.viaplay.se")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        return retrofit;
    }

}
