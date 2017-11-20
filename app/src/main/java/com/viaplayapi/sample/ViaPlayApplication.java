package com.viaplayapi.sample;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentActivity;

import com.viaplayapi.sample.data.source.local.LocalRepository;
import com.viaplayapi.sample.data.source.local.ViaplayDatabase;
import com.viaplayapi.sample.data.source.remote.RemoteRepository;
import com.viaplayapi.sample.network.APIClient;
import com.viaplayapi.sample.network.APIInterface;
import com.viaplayapi.sample.viewmodel.PageViewModel;
import com.viaplayapi.sample.viewmodel.PageViewModelFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class ViaPlayApplication extends Application{

    private ViaplayDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = getDatabase();
        RemoteRepository remoteRepository = new RemoteRepository(APIClient.getClient().create(APIInterface.class));
        LocalRepository localRepository = new LocalRepository(database.pageDao(), getExecutor());
        PageViewModelFactory.initialize(remoteRepository, localRepository);
    }

    public ViaplayDatabase getDatabase(){
        return Room.databaseBuilder(this,
                ViaplayDatabase.class, "viaplay.db")
                .build();
    }

    public Executor getExecutor(){
        return  Executors.newFixedThreadPool(2);
    }

    public static PageViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        PageViewModelFactory factory = PageViewModelFactory.getInstance();

        return ViewModelProviders.of(activity, factory).get(PageViewModel.class);
    }


}
