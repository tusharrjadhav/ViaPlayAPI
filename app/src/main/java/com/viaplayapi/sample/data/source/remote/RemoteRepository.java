package com.viaplayapi.sample.data.source.remote;

import android.util.Log;

import com.viaplayapi.sample.callback.GetPageCallback;
import com.viaplayapi.sample.data.Page;
import com.viaplayapi.sample.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class RemoteRepository {

    private static final String TAG = RemoteRepository.class.getSimpleName();
    private APIInterface apiInterface;

    public RemoteRepository(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public void getRootPage(final GetPageCallback callback) {
        apiInterface.doGetRootPage().enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                Log.d(TAG, "Root Page:" + response.toString());
                if (response.isSuccessful()) {
                    Page page = (Page) response.body();
                    callback.onPageLoaded(page);
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                callback.onDataNotAvailable();
            }
        });
    }

    public void getSectionPage(String url, final GetPageCallback callback) {
        apiInterface.doGetSectionPage(url).enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                Log.d(TAG, "Root Page:" + response.toString());
                if (response.isSuccessful()) {
                    Page page = response.body();
                    callback.onPageLoaded(page);
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                callback.onDataNotAvailable();
            }
        });
        ;
    }
}
