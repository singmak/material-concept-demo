package com.maksing.moviedbdata.data.movie;

import com.google.gson.annotations.SerializedName;


public class ProductionCountry{

    private static final String FIELD_NAME = "name";
    private static final String FIELD_ISO_3166_1 = "iso_3166_1";


    @SerializedName(FIELD_NAME)
    private String mName;
    @SerializedName(FIELD_ISO_3166_1)
    private String mIso31661;


    public ProductionCountry(){

    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public String getIso31661() {
        return mIso31661;
    }


}