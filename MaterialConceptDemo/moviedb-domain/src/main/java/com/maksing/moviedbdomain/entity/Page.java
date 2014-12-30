package com.maksing.moviedbdomain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class Page {
    public static Page EMPTY = new Page("", "", "");

    private final String mTitle;
    private final List<String> mDiscoverQueries;
    private final String mPath;

    public Page(String title, List<String> discoveryQueries, String path) {
        mPath = path;
        if (title == null) {
            title = "";
        }

        if (discoveryQueries == null) {
            discoveryQueries = new ArrayList<>();
        }

        mTitle = title;
        mDiscoverQueries = discoveryQueries;
    }

    public Page(String title, String discoveryQuery, String path) {
        mPath = path;
        mTitle = title;
        mDiscoverQueries = new ArrayList<>();
        mDiscoverQueries.add(discoveryQuery);
    }

    public String getPath() {
        return mPath;
    }
}
