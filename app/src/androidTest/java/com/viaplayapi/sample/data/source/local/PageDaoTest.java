package com.viaplayapi.sample.data.source.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.viaplayapi.sample.data.Links;
import com.viaplayapi.sample.data.Page;
import com.viaplayapi.sample.data.Section;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Tushar_temp on 20/11/17
 */
@RunWith(AndroidJUnit4.class)
public class PageDaoTest {

    private static final Page PAGE = new Page("SectionId", "page", "root", "title", "description", new Links(new ArrayList<Section>()), "");

    private ViaplayDatabase mDatabase;

    @Before
    public void setUp() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ViaplayDatabase.class).build();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void insertPageAndGetById() {
        // When inserting a page
        mDatabase.pageDao().insertPage(PAGE);

        // When getting the page by id from the database
        Page dbPage = mDatabase.pageDao().getPageById(PAGE.getSectionId());

        // The dbPage data contains the expected values
        assertPage(dbPage, PAGE);
    }

    @Test
    public void insertPageAndGetByType() throws Exception {
        // When inserting a page
        mDatabase.pageDao().insertPage(PAGE);

        // When getting the page by id from the database
        Page dbPage = mDatabase.pageDao().getPageByType(PAGE.getPageType());

        // The dbPage data contains the expected values
        assertPage(dbPage, PAGE);
    }

    @Test
    public void updatePageAndGetById() {
        // When inserting a page
        mDatabase.pageDao().insertPage(PAGE);

        // When the page is updated
        Page updatedPage = new Page("SectionId", "page", "root", "title", "description", new Links(new ArrayList<Section>()), "");
        mDatabase.pageDao().updatePage(updatedPage);

        // When getting the page by id from the database
        Page dbPage = mDatabase.pageDao().getPageById("SectionId");

        // The dbPage data contains the expected values
        assertPage(dbPage, updatedPage);
    }

    private void assertPage(Page dbPage, Page tempPage) {
        assertThat(dbPage, notNullValue());
        assertThat(dbPage.getPageType(), is(tempPage.getPageType()));
        assertThat(dbPage.getType(), is(tempPage.getType()));
        assertThat(dbPage.getDescription(), is(tempPage.getDescription()));
        assertThat(dbPage.getTitle(), is(tempPage.getTitle()));
        assertThat(dbPage.getSectionId(), is(tempPage.getSectionId()));
        assertThat(dbPage.getSections(), is(tempPage.getSections()));

    }
}