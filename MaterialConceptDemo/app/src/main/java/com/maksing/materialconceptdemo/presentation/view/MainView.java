package com.maksing.materialconceptdemo.presentation.view;

import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;

import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public interface MainView extends View {
    public void gotoPage(Page page);
    public void updateNavigationMenu(List<NavItem> navItems);
}
