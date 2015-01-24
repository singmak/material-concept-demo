package com.maksing.materialconceptdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by maksing on 5/1/15.
 */
public class FixedRatioView extends View {
    private FixedRatioViewMeasurer mMeasurer;

    public FixedRatioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMeasurer = new FixedRatioViewMeasurer(context, attrs);
    }

    public FixedRatioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMeasurer = new FixedRatioViewMeasurer(context, attrs);
    }

    public FixedRatioView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasurer.setMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mMeasurer.getViewWidth(), mMeasurer.getViewHeight());
    }
}
