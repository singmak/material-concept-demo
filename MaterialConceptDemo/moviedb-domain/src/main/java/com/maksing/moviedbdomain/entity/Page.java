package com.maksing.moviedbdomain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public class Page implements Serializable{
    public static Page EMPTY = new Page("", "", "");

    private final String mTitle;
    private final List<ListItem> mListItems;
    private final String mPath;

    public Page(String title, List<ListItem> listItems, String path) {
        mPath = path;

        if (title == null) {
            title = "";
        }

        if (listItems == null) {
            listItems = new ArrayList<>();
        }

        mTitle = title;
        mListItems = listItems;
    }

    public Page(String title, String discoveryQuery, String path) {
        mPath = path;
        mTitle = title;
        mListItems = new ArrayList<>();
        mListItems.add(new ListItem(discoveryQuery, title));
    }

    public String getPath() {
        return mPath;
    }

    public String getDiscoverQueryAt(int pos) {
        if (mListItems.size() == 0) return "";
        return mListItems.get(pos).getQuery();
    }

    public int getListsCount() {
        return mListItems.size();
    }

    public List<ListItem> getListItems() {
        return mListItems;
    }
}
