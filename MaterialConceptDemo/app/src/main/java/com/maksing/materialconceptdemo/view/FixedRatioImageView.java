package com.maksing.materialconceptdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by maksing on 23/9/14.
 */
public class FixedRatioImageView extends ImageView {
    private float mRatioWidth = 0;
    private float mRatioHeight = 0;

    public FixedRatioImageView(Context context) {
        super(context);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRatio(float ratioWidth, float ratioHeight) {
        mRatioHeight = ratioHeight;
        mRatioWidth = ratioWidth;
    }

    public float getRatioWidth() {
        return mRatioWidth;
    }

    public float getRatioHeight() {
        return mRatioHeight;
    }

    public void setWrapContent() {
        setRatio(0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            try {
                float imageSideRatio;
                if (mRatioHeight != 0 && mRatioWidth != 0) {
                    imageSideRatio = mRatioWidth / mRatioHeight;
                } else {
                    imageSideRatio = (float)getDrawable().getIntrinsicWidth() / getDrawable().getIntrinsicHeight();
                }

                // Image is wider than the display (ratio)
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = (int) (width / imageSideRatio);
                setMeasuredDimension(width, height);

            } catch (Exception e) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }

    }
}
