package com.maksing.materialconceptdemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by maksing on 4/1/15.
 */
public class FixedRatioViewPager extends ViewPager {
    private float mRatioWidth = 0;
    private float mRatioHeight = 0;

    public FixedRatioViewPager(Context context) {
        super(context);
    }

    public FixedRatioViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAspectRatio(float width, float height) {
        mRatioHeight = height;
        mRatioWidth = width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        float imageSideRatio = 0;

        if (mRatioHeight != 0 && mRatioWidth != 0) {
            imageSideRatio = mRatioWidth / mRatioHeight;
            height = (int) (width / imageSideRatio); //only modify height. the width of the view is always preserved.
        }

        setMeasuredDimension(width, height);
    }
}
