package com.maksing.materialconceptdemo.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.adapter.NavigationMenuAdapter;
import com.maksing.materialconceptdemo.fragment.MultiListsFragment;
import com.maksing.materialconceptdemo.presentation.presenter.MainPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.view.MainView;
import com.maksing.moviedbdomain.entity.NavItem;
import com.maksing.moviedbdomain.entity.Page;
import com.maksing.moviedbdomain.entity.User;
import com.maksing.moviedbdomain.usecase.GetNavItemsUseCase;
import com.maksing.moviedbdomain.usecase.GetUserDataUseCase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by maksing on 22/12/14.
 */
public class MainActivity extends PresenterActivity<MainPresenter> implements MainView, View.OnClickListener {
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationMenuAdapter mNavigationMenuAdapter = new NavigationMenuAdapter(this);

    @InjectView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.navMenu)
    RecyclerView mRecyclerView;
    @InjectView(R.id.username)
    TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mNavigationMenuAdapter);

        getPresenter().initialize(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.title_home);

        // Now retrieve the DrawerLayout so that we can set the status bar color.
        // This only takes effect on Lollipop, or when using translucentStatusBar
        // on KitKat.


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
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(new GetNavItemsUseCase(getServiceHolder()), new GetUserDataUseCase(getServiceHolder()));
    }

    @Override
    public void gotoPage(Page page) {
        switchPresenterFragment(R.id.page_container, MultiListsFragment.createInstance(page), page.getPath());
    }

    @Override
    public void updateNavigationMenu(List<NavItem> navItems) {
        mNavigationMenuAdapter.setNavItems(navItems);
    }

    @Override
    public void updateUser(User user) {
        mUsername.setText(user.getUsername());
    }

    @Override
    public void onClick(View v) {
        getPresenter().selectedNavItem((NavItem)v.getTag());
    }
}
