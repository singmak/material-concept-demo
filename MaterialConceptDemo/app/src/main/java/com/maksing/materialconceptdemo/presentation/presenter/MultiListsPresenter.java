package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.MultiListsView;
import com.maksing.moviedbdomain.entity.NavItem;

/**
 * Created by maksing on 23/12/14.
 */
public class MultiListsPresenter implements Presenter {
    private MultiListsView mMultiListsView;
    private NavItem mNavItem;

    public void initialize(MultiListsView view) {
        mMultiListsView = view;
    }

    public void destroy() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
