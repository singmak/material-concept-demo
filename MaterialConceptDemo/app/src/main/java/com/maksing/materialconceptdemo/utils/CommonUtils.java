package com.maksing.materialconceptdemo.utils;

import com.maksing.materialconceptdemo.manager.ApplicationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maksing on 5/1/15.
 */
public class CommonUtils {
    public static int getHeightWithFixedAspectRatio(float width, float aspectRatioWidth, float aspectRatioHeight) {
        float imageSideRatio = aspectRatioWidth / aspectRatioHeight;
        return (int) (width / imageSideRatio);
    }

    /**
     * Create a color integer value with specified alpha.
     * This may be useful to change alpha value of background color.
     *
     * @param alpha     alpha value from 0.0f to 1.0f.
     * @param baseColor base color. alpha value will be ignored.
     * @return a color with alpha made from base color
     */
    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    public static int dpToPx(int dp)
    {
        float density = ApplicationManager.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }
}
