package com.maksing.moviedbdata.data.movie;

import com.google.gson.annotations.SerializedName;


public class ProductionCompany{

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";


    @SerializedName(FIELD_ID)
    private int mId;
    @SerializedName(FIELD_NAME)
    private String mName;


    public ProductionCompany(){

    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }


}