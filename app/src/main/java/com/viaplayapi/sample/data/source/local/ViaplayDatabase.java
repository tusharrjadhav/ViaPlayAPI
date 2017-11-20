package com.viaplayapi.sample.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.viaplayapi.sample.data.Page;

/**
 * Created by Tushar_temp on 18/11/17
 */

@Database(entities = {Page.class}, version = 1)
public abstract class ViaplayDatabase extends RoomDatabase{
    private static ViaplayDatabase instance;

    public abstract PageDao pageDao();

    private static final Object sLock = new Object();

    public static ViaplayDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        ViaplayDatabase.class, "ViaPlay.db")
                        .build();
            }
            return instance;
        }
    }
}
