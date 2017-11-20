package com.viaplayapi.sample.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.viaplayapi.sample.data.Page;
import com.viaplayapi.sample.data.Section;
import com.viaplayapi.sample.data.source.local.LocalRepository;
import com.viaplayapi.sample.data.source.remote.RemoteRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class PageViewModel extends ViewModel {

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;
    private String TAG = PageViewModel.class.getSimpleName();

    public PageViewModel(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @VisibleForTesting
    public void getRootPage(GetPageCallback callback) {
        localRepository.getRootPage(callback);
    }

    public void getRootFromService(final GetPageCallback callback) {
        /**
         GET Page Resources
         **/
        Call call = remoteRepository.getRootPage();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "Root Page:" + response.toString());
                if (response.isSuccessful()) {
                    Page page = (Page) response.body();
                    localRepository.insertPage(page);
                    callback.onPageLoaded(page);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                getRootPage(callback);
            }
        });
    }

    @VisibleForTesting
    public void getSectionPage(String sectionId, GetPageCallback callback) {
        localRepository.getPageById(sectionId, callback);
    }

    public void getSectionPageFromService(final Section section, final GetPageCallback callback) {
        /**
         GET Page Resources
         **/
        String url = section.getHref().split("\\{")[0];
        Call call = remoteRepository.getSectionPage(url);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "Root Page:" + response.toString());
                if (response.isSuccessful()) {
                    Page page = (Page) response.body();
                    localRepository.insertPage(page);
                    callback.onPageLoaded(page);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure", t);
                getSectionPage(section.getId(), callback);
            }
        });
    }

    public interface GetPageCallback {

        void onPageLoaded(Page page);

        void onDataNotAvailable();

    }

}
