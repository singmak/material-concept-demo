package com.maksing.materialconceptdemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by maksing on 4/1/15.
 */
public class FixedRatioViewPager extends ViewPager {
    private FixedRatioViewMeasurer mMeasurer;

    public FixedRatioViewPager(Context context) {
        super(context);
    }

    public FixedRatioViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMeasurer = new FixedRatioViewMeasurer(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasurer.setMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMeasurer.getViewHeight(), MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
