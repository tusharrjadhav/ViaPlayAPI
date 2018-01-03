package com.viaplayapi.sample.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.viaplayapi.sample.callback.GetPageCallback;
import com.viaplayapi.sample.data.Page;
import com.viaplayapi.sample.data.Section;
import com.viaplayapi.sample.data.source.local.LocalRepository;
import com.viaplayapi.sample.data.source.remote.RemoteRepository;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class PageViewModel extends ViewModel {

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;
    private String TAG = PageViewModel.class.getSimpleName();
    private MutableLiveData<Page> pageMutableLiveData = new MutableLiveData<>();

    public PageViewModel(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @VisibleForTesting
    public void getRootPage() {
        localRepository.getRootPage(new GetPageCallback() {
            @Override
            public void onPageLoaded(Page page) {
                pageMutableLiveData.postValue(page);
            }

            @Override
            public void onDataNotAvailable() {
                pageMutableLiveData.postValue(null);
            }
        });
    }

    public void getRootFromService() {

        remoteRepository.getRootPage(new GetPageCallback() {
            @Override
            public void onPageLoaded(Page page) {
                localRepository.insertPage(page);
            }

            @Override
            public void onDataNotAvailable() {
                getRootPage();
            }
        });

    }

    @VisibleForTesting
    public void getSectionPage(String sectionId) {
        localRepository.getPageById(sectionId, new GetPageCallback() {
            @Override
            public void onPageLoaded(Page page) {
                pageMutableLiveData.postValue(page);
            }

            @Override
            public void onDataNotAvailable() {
                pageMutableLiveData.postValue(null);
            }
        });
    }

    public void getSectionPageFromService(final Section section) {
        /**
         GET Page Resources
         **/
        String url = section.getHref().split("\\{")[0];
        remoteRepository.getSectionPage(url, new GetPageCallback() {
            @Override
            public void onPageLoaded(Page page) {
                pageMutableLiveData.postValue(page);
            }

            @Override
            public void onDataNotAvailable() {
                getSectionPage(section.getId());
            }
        });

    }

    public MutableLiveData<Page> getPageMutableLiveData() {
        return pageMutableLiveData;
    }
}
