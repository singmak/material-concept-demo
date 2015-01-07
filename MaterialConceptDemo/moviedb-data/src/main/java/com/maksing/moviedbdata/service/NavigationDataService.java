package com.maksing.moviedbdata.service;

import com.maksing.moviedbdata.dataUtils;
import com.maksing.moviedbdomain.entity.ListItem;
import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.service.NavigationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by maksing on 31/12/14.
 */
public class NavigationDataService extends HttpService implements NavigationService {

    private static class LazyHolder {
        private static final NavigationDataService INSTANCE = new NavigationDataService();
    }

    public static NavigationDataService getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Observable<List<NavItem>> getNavItems() {
        List<NavItem> items = new ArrayList<>();

        List<ListItem> listItems = new ArrayList<>();


        String startDate = dataUtils.formatDate(new Date(System.currentTimeMillis() - 31*24*60*60*1000L), "yyyy-MM-dd");
        String endDate = dataUtils.formatDate(new Date(), "yyyy-MM-dd");

        listItems.add(new ListItem("What's new", "primary_release_date.gte=" + startDate + "&primary_release_date.lte=" + endDate + "&sort_by=popularity.desc"));
        listItems.add(new ListItem("All Movies", "sort_by=popularity.desc"));
        listItems.add(new ListItem("Science Fiction", "sort_by=popularity.desc&with_genres=878"));
        listItems.add(new ListItem("Action", "sort_by=popularity.desc&with_genres=28"));
        listItems.add(new ListItem("Adventure", "sort_by=popularity.desc&with_genres=12"));
        listItems.add(new ListItem("Animation", "sort_by=popularity.desc&with_genres=16"));
        listItems.add(new ListItem("Comedy", "sort_by=popularity.desc&with_genres=35"));
        listItems.add(new ListItem("Crime", "sort_by=popularity.desc&with_genres=80"));
        listItems.add(new ListItem("Thriller", "sort_by=popularity.desc&with_genres=53"));
        listItems.add(new ListItem("Drama", "sort_by=popularity.desc&with_genres=18"));
        listItems.add(new ListItem("Fantasy", "sort_by=popularity.desc&with_genres=14"));

        items.add(new NavItem("Home", new Page("Home", listItems, "home")));

        items.add(new NavItem("Kids", new Page("Kids movies", "certification_country=US&certification.lte=G&sort_by=popularity.desc", "kids")));
        items.add(new NavItem("The best of 2014", new Page("The best of 2014", "primary_release_year=2014&sort_by=vote_average.desc&vote_count.gte=100", "best2014")));
        return Observable.just(items);
    }
}
