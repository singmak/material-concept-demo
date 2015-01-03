package com.maksing.materialconceptdemo.view;

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

    public FixedRatioImageView(Context context) {
        super(context);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        float imageSideRatio = 0;

        if (mWrapDrawable) {
            imageSideRatio = (float)getDrawable().getIntrinsicWidth() / getDrawable().getIntrinsicHeight();
        } else if (mRatioHeight != 0 && mRatioWidth != 0) {
            imageSideRatio = mRatioWidth / mRatioHeight;
        }

        if (imageSideRatio > 0) {
            height = (int) (width / imageSideRatio); //only modify height. the width of the view is always preserved.
        }

        setMeasuredDimension(width, height);
    }
}
