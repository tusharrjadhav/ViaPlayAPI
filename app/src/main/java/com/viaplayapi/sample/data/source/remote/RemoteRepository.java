package com.viaplayapi.sample.data.source.remote;

import com.viaplayapi.sample.network.APIInterface;

import retrofit2.Call;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class RemoteRepository {

    private APIInterface apiInterface;

    public RemoteRepository(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Call getRootPage() {
        return apiInterface.doGetRootPage();
    }

    public Call getSectionPage(String url) {
        return apiInterface.doGetSectionPage(url);
    }
}
