package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.MainView;
import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.usecase.GetNavItemsUseCase;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 30/12/14.
 */
public class MainPresenter implements Presenter {

    private MainView mMainView;
    private List<NavItem> mNavItems;
    private NavItem mCurrentNavItem;

    private final GetNavItemsUseCase mGetNavItemsUseCase;

    private CompositeSubscription mSubscription;

    private Observable<List<NavItem>> mGetNavigationRequest;

    public MainPresenter(GetNavItemsUseCase getNavItemsUseCase) {
        mGetNavItemsUseCase = getNavItemsUseCase;
    }

    private void restore(MainView mainView) {
        mMainView.updateNavigationMenu(mNavItems);
    }

    public void initialize(MainView mainView) {

        mMainView = mainView;

        if (mNavItems != null) {
            restore(mainView);
        }

        mSubscription = new CompositeSubscription();

        if (mGetNavigationRequest == null) {
            mGetNavigationRequest = mGetNavItemsUseCase.getObservable().cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        mSubscription.add(mGetNavigationRequest.subscribe(new Subscriber<List<NavItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<NavItem> navItems) {
                mNavItems = navItems;
                mCurrentNavItem = mNavItems.get(0);
                mMainView.gotoPage(mCurrentNavItem.getPage());
                mMainView.updateNavigationMenu(mNavItems);
            }
        }));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void destroy() {
        mSubscription.unsubscribe();
    }
}
