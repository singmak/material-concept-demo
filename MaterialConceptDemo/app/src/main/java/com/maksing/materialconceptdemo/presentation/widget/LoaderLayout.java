package com.maksing.materialconceptdemo.presentation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by maksing on 7/1/15.
 */
public class LoaderLayout extends FrameLayout {
    private ProgressBar mProgressBar;
    private TextView mErrorText;
    private boolean mIsLoading = false;

    public LoaderLayout(Context context) {
        super(context);
    }

    public LoaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void load() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.INVISIBLE);
        }
        mIsLoading = true;

        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(getContext());
            FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            addView(mProgressBar, params);
        }
    }

    public void error(String message) {
        if (mIsLoading) {
            mIsLoading = false;
            removeView(mProgressBar);
            mProgressBar = null;
            if (mErrorText == null) {
                mErrorText = new TextView(getContext());
                FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
                addView(mErrorText, params);
            }
            mErrorText.setText(message);
        }
    }

    public void displayContent() {
        mIsLoading = false;
        if (mProgressBar != null) {
            removeView(mProgressBar);
            mProgressBar = null;
        }

        if (mErrorText != null) {
            removeView(mErrorText);
            mErrorText = null;
        }

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
}
