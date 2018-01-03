package com.viaplayapi.sample.callback;

import com.viaplayapi.sample.data.Page;

/**
 * Created by Tushar_temp on 16/12/17
 */

public interface GetPageCallback {

    void onPageLoaded(Page page);

    void onDataNotAvailable();
}
