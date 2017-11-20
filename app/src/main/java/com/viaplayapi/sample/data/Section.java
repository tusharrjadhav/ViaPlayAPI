package com.viaplayapi.sample.data;

import android.support.annotation.VisibleForTesting;

import java.io.Serializable;

/**
 * Created by Tushar_temp on 18/11/17
 */

public class Section implements Serializable{
    private String id;
    private String title;
    private String href;
    private String type;
    private String name;
    private String templated;
    private String active;

    @VisibleForTesting
    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplated() {
        return templated;
    }

    public void setTemplated(String templated) {
        this.templated = templated;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
