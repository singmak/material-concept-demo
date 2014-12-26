package com.maksing.moviedbdata.data.movie;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class MovieListData{

    private static final String FIELD_PAGE = "page";
    private static final String FIELD_TOTAL_PAGES = "total_pages";
    private static final String FIELD_RESULTS = "results";
    private static final String FIELD_TOTAL_RESULTS = "total_results";


    @SerializedName(FIELD_PAGE)
    private int mPage;
    @SerializedName(FIELD_TOTAL_PAGES)
    private int mTotalPage;
    @SerializedName(FIELD_RESULTS)
    private List<Result> mResults;
    @SerializedName(FIELD_TOTAL_RESULTS)
    private int mTotalResult;


    public MovieListData(){

    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getPage() {
        return mPage;
    }

    public void setTotalPage(int totalPage) {
        mTotalPage = totalPage;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setTotalResult(int totalResult) {
        mTotalResult = totalResult;
    }

    public int getTotalResult() {
        return mTotalResult;
    }


}