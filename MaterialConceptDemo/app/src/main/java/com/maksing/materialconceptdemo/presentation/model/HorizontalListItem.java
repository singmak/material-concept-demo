package com.maksing.materialconceptdemo.presentation.model;

import com.maksing.moviedbdomain.entity.ListItem;

/**
 * Created by maksing on 7/1/15.
 */
public class HorizontalListItem extends ListItem {
    private boolean mIsLoading = false;
    private boolean mHasError = false;

    public HorizontalListItem(String title, String query) {
        super(title, query);
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public boolean isHasError() {
        return mHasError;
    }

    public void setLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public void setHasError(boolean hasError) {
        mHasError = hasError;
    }
}
