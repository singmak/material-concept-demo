package com.maksing.materialconceptdemo.presentation.view;

import com.maksing.materialconceptdemo.presentation.model.NavMenu;
import com.maksing.materialconceptdemo.presentation.model.NavMenuItem;
import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.entity.User;

import java.util.List;

/**
 * Created by maksing on 25/12/14.
 */
public interface MainView extends PresenterView {
    public void gotoPage(Page page);
    public void updateNavigationMenu(List<NavMenuItem> navItems);
    public void updateUser(User user);
}
