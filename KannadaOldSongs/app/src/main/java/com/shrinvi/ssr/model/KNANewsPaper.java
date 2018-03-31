package com.shrinvi.ssr.model;

import java.io.Serializable;

/**
 * Created by dinesh.k.masthaiah on 23-03-2016.
 */
public class KNANewsPaper implements Serializable {
    private String mName;
    private String mUrl;
    private int mIconId;

    public KNANewsPaper(String name, int iconId, String url) {
        mName = name;
        mUrl = url;
        mIconId = iconId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getIconId() {
        return mIconId;
    }

}

