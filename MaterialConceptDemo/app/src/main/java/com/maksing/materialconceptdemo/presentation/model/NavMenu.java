package com.maksing.materialconceptdemo.presentation.model;

import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.User;

import java.util.List;

/**
 * Created by maksing on 31/12/14.
 */
public class NavMenu {
    private final List<NavItem> mNavItems;
    private final User mUser;


    public NavMenu(List<NavItem> navItems, User user) {
        mNavItems = navItems;
        mUser = user;
    }

    public List<NavItem> getNavItems() {
        return mNavItems;
    }

    public User getUser() {
        return mUser;
    }
}
