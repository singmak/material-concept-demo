package com.maksing.materialconceptdemo.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.presenter.MovieListPresenter;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;

/**
 * Created by maksing on 22/12/14.
 */
public class MainActivity extends BaseActivity {
    private ListView mList;
    private MovieListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView)findViewById(R.id.list);
    }

    @Override
    protected Presenter onCreatePresenter() {
        mPresenter = new MovieListPresenter();
        return mPresenter;
    }
}
