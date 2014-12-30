package com.maksing.moviedbdomain.entity;

/**
 * Created by maksing on 26/12/14.
 */
public class NavItem {

    private final String mTitle;
    private final Page mPage;
    private boolean mIsHome = false;

    public NavItem(String title, Page page) {
        if (title == null) {
            title = "";
        }

        if (page == null) {
            page = Page.EMPTY;
        }

        mTitle = title;
        mPage = page;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isHome() {
        return mIsHome;
    }

    public Page getPage() {
        return mPage;
    }

    public void setHome(boolean isHome) {
        mIsHome = isHome;
    }
}
