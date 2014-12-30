package com.maksing.materialconceptdemo.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.fragment.MultiListsFragment;
import com.maksing.materialconceptdemo.manager.ServiceManager;
import com.maksing.materialconceptdemo.presentation.presenter.MainPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.MultiListsPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.MainView;
import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.usecase.GetNavItemsUseCase;

import java.util.List;

/**
 * Created by maksing on 22/12/14.
 */
public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter mPresenter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.initialize(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.title_home);

        // Now retrieve the DrawerLayout so that we can set the status bar color.
        // This only takes effect on Lollipop, or when using translucentStatusBar
        // on KitKat.
        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);

        TypedArray a = getTheme().obtainStyledAttributes(R.style.AppTheme, new int[] {R.attr.colorPrimaryDark});
        int colorResId = a.getResourceId(0, 0);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(colorResId));

        mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected Presenter onCreatePresenter(Presenter presenter) {
        if (presenter == null) {
            presenter = new MainPresenter(new GetNavItemsUseCase(getServiceHolder()));
        }
        mPresenter = (MainPresenter)presenter;
        return presenter;
    }

    @Override
    public void gotoPage(Page page) {
        switchPresenterFragment(R.id.page_container, MultiListsFragment.createInstance(), page.getPath());
    }

    @Override
    public void updateNavigationMenu(List<NavItem> navItems) {

    }
}
