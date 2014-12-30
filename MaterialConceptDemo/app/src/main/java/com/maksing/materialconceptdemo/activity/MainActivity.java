package com.maksing.materialconceptdemo.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.presenter.MovieListPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;

/**
 * Created by maksing on 22/12/14.
 */
public class MainActivity extends BaseActivity {
    private MovieListPresenter mPresenter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Presenter onCreatePresenter(Presenter presenter) {
        return null;
    }

}
