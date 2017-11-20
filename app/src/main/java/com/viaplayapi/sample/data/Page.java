package com.viaplayapi.sample.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tushar_temp on 18/11/17
 */
@Entity(tableName = "page")
public class Page {

    @Ignore
    private final static String ROOT_SECTION_ID = "1";

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String sectionId = ROOT_SECTION_ID;

    @NonNull
    private String type;

    @NonNull
    private String pageType;

    @NonNull
    private String title;

    @Nullable
    private String description;

    @SerializedName("_links")
    @Ignore
    private Links links;

    @Nullable
    private String sections;

    @Ignore
    @VisibleForTesting
    public Page(@NonNull String sectionId, @NonNull String type, @NonNull String pageType, @NonNull String title, String description, Links links, String sections) {
        this.sectionId = sectionId;
        this.type = type;
        this.pageType = pageType;
        this.title = title;
        this.description = description;
        this.links = links;
        this.sections = sections;
    }

    @Ignore
    public Page() {
    }

    public Page(@NonNull String sectionId, @NonNull String type, @NonNull String pageType, @NonNull String title, String description, String sections) {
        this.sectionId = sectionId;
        this.type = type;
        this.pageType = pageType;
        this.title = title;
        this.description = description;
        this.sections = sections;
    }

    @NonNull
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(@NonNull String sectionId) {
        this.sectionId = sectionId;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getPageType() {
        return pageType;
    }

    public void setPageType(@NonNull String pageType) {
        this.pageType = pageType;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Nullable
    public String getSections() {
        return sections;
    }

    public void setSections(@Nullable String sections) {
        this.sections = sections;
    }
}
