package com.viaplayapi.sample.network;

import com.viaplayapi.sample.data.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Tushar_temp on 19/11/17
 */

public interface APIInterface {

    @GET("/androidv2-se")
    Call<Page> doGetRootPage();


    @GET
    Call<Page> doGetSectionPage(@Url String sectionUrl);

}
