package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.model.NavMenu;
import com.maksing.materialconceptdemo.presentation.view.MainView;
import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.User;
import com.maksing.moviedbdomain.usecase.GetNavItemsUseCase;
import com.maksing.moviedbdomain.usecase.GetUserDataUseCase;
import com.maksing.moviedbdomain.usecase.UseCase;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maksing on 30/12/14.
 */
public class MainPresenter extends Presenter<MainView> {

    private List<NavItem> mNavItems;
    private NavItem mCurrentNavItem;

    private User mCurrentUser;

    private final GetNavItemsUseCase mGetNavItemsUseCase;
    private final GetUserDataUseCase mGetUserDataUseCase;

    private Observable<NavMenu> mGetNavigationRequest;

    public MainPresenter(GetNavItemsUseCase getNavItemsUseCase, GetUserDataUseCase getUserDataUseCase) {
        mGetNavItemsUseCase = getNavItemsUseCase;
        mGetUserDataUseCase = getUserDataUseCase;
    }

    @Override
    protected void restoreView() {
        updateData();
    }

    @Override
    protected boolean shouldRestore() {
        return mNavItems != null && mCurrentUser != null;
    }

    @Override
    protected void initializeView() {
        if (mGetNavigationRequest == null) {
            mGetNavigationRequest = Observable.zip(mGetNavItemsUseCase.getObservable(), mGetUserDataUseCase.getObservable(), new Func2<List<NavItem>, User, NavMenu>() {
                @Override
                public NavMenu call(List<NavItem> navItems, User user) {
                    return new NavMenu(navItems, user);
                }
            }).cache().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        addSubscription(mGetNavigationRequest.subscribe(new Subscriber<NavMenu>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NavMenu navMenu) {
                mNavItems = navMenu.getNavItems();
                mCurrentNavItem = mNavItems.get(0);
                mCurrentUser = navMenu.getUser();
                getView().gotoPage(mCurrentNavItem.getPage());
                updateData();
            }
        }));
    }

    public void selectedNavItem(NavItem navItem) {
        getView().gotoPage(navItem.getPage());
    }

    private void updateData() {
        getView().updateNavigationMenu(mNavItems);
        getView().updateUser(mCurrentUser);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
