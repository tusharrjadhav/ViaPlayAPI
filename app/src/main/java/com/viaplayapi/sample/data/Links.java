package com.viaplayapi.sample.data;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class Links {
    @SerializedName("viaplay:sections")
    public List<Section> sections;

    public Links(){

    }

    @VisibleForTesting
    public Links(List<Section> sections) {
        this.sections = sections;
    }
}
