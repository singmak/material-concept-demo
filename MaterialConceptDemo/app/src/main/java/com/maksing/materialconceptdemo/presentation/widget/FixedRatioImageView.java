package com.maksing.materialconceptdemo.presentation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by maksing
 */
public class FixedRatioImageView extends ImageView {
    private float mRatioWidth = 0;
    private float mRatioHeight = 0;

    private boolean mWrapDrawable = false;

    private FixedRatioViewMeasurer mMeasurer;

    public FixedRatioImageView(Context context) {
        super(context);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMeasurer = new FixedRatioViewMeasurer(context, attrs);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mMeasurer = new FixedRatioViewMeasurer(context, attrs);
    }

    public void setAspectRatio(float width, float height) {
        mRatioHeight = height;
        mRatioWidth = width;
    }

    public void setWrapDrawable(boolean wrapDrawable) {
        mWrapDrawable = wrapDrawable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mMeasurer.setMeasureSpec(widthMeasureSpec, heightMeasureSpec);

        int width = mMeasurer.getViewWidth();
        int height = mMeasurer.getViewHeight();

        if (mWrapDrawable) {
            height = (int)(width / (float)getDrawable().getIntrinsicWidth() / getDrawable().getIntrinsicHeight());
        }

        setMeasuredDimension(width, height);
    }
}
