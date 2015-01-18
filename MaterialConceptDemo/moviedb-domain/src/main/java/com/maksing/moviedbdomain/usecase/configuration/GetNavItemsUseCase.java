package com.maksing.moviedbdomain.usecase.configuration;

import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.service.NavigationService;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.usecase.session.SessionUseCase;

import java.util.List;

import rx.Observable;

/**
 * Created by maksing on 31/12/14.
 */
public class GetNavItemsUseCase extends SessionUseCase {
    private NavigationService mNavigationService;

    public GetNavItemsUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
        mNavigationService = serviceHolder.getNavigationService();
    }

    public Observable<List<NavItem>> getObservable() {
        return mNavigationService.getNavItems();
    }
}
