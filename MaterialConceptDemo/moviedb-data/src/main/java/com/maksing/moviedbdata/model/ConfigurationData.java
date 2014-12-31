package com.maksing.moviedbdata.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ConfigurationData {

    private static final String FIELD_IMAGES = "images";
    private static final String FIELD_CHANGE_KEYS = "change_keys";


    @SerializedName(FIELD_IMAGES)
    private ImageConfigData mImage;
    @SerializedName(FIELD_CHANGE_KEYS)
    private List<String> mChangeKeys;


    public ConfigurationData(){

    }

    public void setImage(ImageConfigData image) {
        mImage = image;
    }

    public ImageConfigData getImage() {
        return mImage;
    }

    public void setChangeKeys(List<String> changeKeys) {
        mChangeKeys = changeKeys;
    }

    public List<String> getChangeKeys() {
        return mChangeKeys;
    }


}