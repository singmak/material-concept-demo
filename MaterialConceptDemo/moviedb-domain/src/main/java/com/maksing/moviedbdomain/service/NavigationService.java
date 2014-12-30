package com.maksing.moviedbdomain.service;

import com.maksing.moviedbdomain.entity.NavItem;

import java.util.List;

import rx.Observable;

/**
 * Created by maksing on 31/12/14.
 */
public interface NavigationService {
    Observable<List<NavItem>> getNavItems();
}
