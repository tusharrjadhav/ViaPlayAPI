package com.viaplayapi.sample.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.StringDef;

import com.viaplayapi.sample.data.Page;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Tushar_temp on 18/11/17
 */

@Dao
public interface PageDao {

    String ROOT = "root";
    String SECTION = "section";


    @StringDef({ROOT, SECTION})
    @Retention(RetentionPolicy.SOURCE)
    @interface PageType {}

    @Query("SELECT * FROM Page WHERE id = :id")
    Page getPageById(String id);

    @Query("SELECT * FROM Page WHERE pageType = :type")
    Page getPageByType(@PageType String type);

    @Update
    int updatePage(Page page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPage(Page page);
}
