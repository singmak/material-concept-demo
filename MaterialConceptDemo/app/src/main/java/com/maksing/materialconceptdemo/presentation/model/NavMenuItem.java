package com.maksing.materialconceptdemo.presentation.model;

import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;

import java.io.Serializable;

/**
 * Created by maksing on 6/1/15.
 */
public class NavMenuItem extends NavItem implements Serializable{
    private boolean mSelected = false;

    public NavMenuItem(String title, Page page) {
        super(title, page);
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }
}
