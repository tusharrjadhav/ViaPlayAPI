package com.viaplayapi.sample.data.source.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viaplayapi.sample.callback.GetPageCallback;
import com.viaplayapi.sample.data.Links;
import com.viaplayapi.sample.data.Page;
import com.viaplayapi.sample.data.Section;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Tushar_temp on 19/11/17
 */

public class LocalRepository {
    private PageDao pageDao;
    private Executor executor;

    public LocalRepository(PageDao couponDAO, Executor executor) {
        this.pageDao = couponDAO;
        this.executor = executor;
    }

    public void getRootPage(final GetPageCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Page page = pageDao.getPageByType(PageDao.ROOT);
                page.setLinks(getSectionList(page.getSections()));
                callback.onPageLoaded(page);
            }
        };
        executor.execute(runnable);
    }

    public void insertPage(final Page body) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                body.setSections(getSectionsStr(body));
                pageDao.insertPage(body);
            }
        };
        executor.execute(runnable);
    }

    public void getPageById(final String sectionId, final GetPageCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Page page = pageDao.getPageById(sectionId);
                page.setLinks(getSectionList(page.getSections()));
                callback.onPageLoaded(page);
            }
        };
        executor.execute(runnable);
    }

    private String getSectionsStr(Page body) {
        Gson gson = new Gson();
        return gson.toJson(body.getLinks().sections);
    }

    private Links getSectionList(String sections) {
        Links links = new Links();
        Gson gson = new Gson();
        links.sections = gson.fromJson(sections, new TypeToken<List<Section>>(){}.getType());
        return links;
    }

}
