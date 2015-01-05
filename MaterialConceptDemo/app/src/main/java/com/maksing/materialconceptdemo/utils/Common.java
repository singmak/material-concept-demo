package com.maksing.materialconceptdemo.utils;

/**
 * Created by maksing on 5/1/15.
 */
public class Common {
    public static int getHeightWithFixedAspectRatio(float width, float aspectRatioWidth, float aspectRatioHeight) {
        float imageSideRatio = aspectRatioWidth / aspectRatioHeight;
        return (int) (width / imageSideRatio);
    }
}
