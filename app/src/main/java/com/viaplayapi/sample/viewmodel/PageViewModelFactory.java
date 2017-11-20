package com.viaplayapi.sample.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.viaplayapi.sample.data.source.local.LocalRepository;
import com.viaplayapi.sample.data.source.remote.RemoteRepository;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class PageViewModelFactory implements ViewModelProvider.Factory {

    private static PageViewModelFactory INSTANCE;

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    public static void initialize(RemoteRepository remoteRepository, LocalRepository localRepository) {
        INSTANCE = new PageViewModelFactory(remoteRepository, localRepository);
    }

    public static synchronized PageViewModelFactory getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("PageViewModelFactory not initialized");
        }
        return INSTANCE;
    }

    private PageViewModelFactory(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PageViewModel.class)) {
            //noinspection unchecked
            return (T) new PageViewModel(remoteRepository, localRepository);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
