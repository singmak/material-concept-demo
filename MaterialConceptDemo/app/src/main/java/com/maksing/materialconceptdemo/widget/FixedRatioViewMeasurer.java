package com.maksing.materialconceptdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.maksing.materialconceptdemo.R;

/**
 * Created by maksing on 5/1/15.
 */
public class FixedRatioViewMeasurer {
    private float mRatioWidth = 0;
    private float mRatioHeight = 0;

    private int mWidthMeasureSpec;
    private int mHeightMeasureSpec;

    public FixedRatioViewMeasurer() {

    }

    public FixedRatioViewMeasurer(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.fixedAspectRatio,
                0, 0);

        mRatioWidth = a.getFloat(R.styleable.fixedAspectRatio_widthRatio, 0);
        mRatioHeight = a.getFloat(R.styleable.fixedAspectRatio_heightRatio, 0);
    }

    public FixedRatioViewMeasurer(float ratioWidth, float ratioHeight) {
        mRatioWidth = ratioWidth;
        mRatioHeight = ratioHeight;
    }

    public void setAspectRatio(float width, float height) {
        mRatioHeight = height;
        mRatioWidth = width;
    }

    public void setMeasureSpec(int widthMeasureSpec, int heightMeasureSpec) {
        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;
    }

    public int getViewHeight() {
        int width = View.MeasureSpec.getSize(mWidthMeasureSpec);
        int height = View.MeasureSpec.getSize(mHeightMeasureSpec);

        float imageSideRatio = 0;

        if (mRatioHeight != 0 && mRatioWidth != 0) {
            imageSideRatio = mRatioWidth / mRatioHeight;
            height = (int) (width / imageSideRatio); //only modify height. the width of the view is always preserved.
        }

        return height;
    }

    public int getViewWidth() {
        return View.MeasureSpec.getSize(mWidthMeasureSpec);
    }
}
