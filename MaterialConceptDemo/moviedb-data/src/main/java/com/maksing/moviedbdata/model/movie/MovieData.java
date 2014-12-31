package com.maksing.moviedbdata.model.movie;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class MovieData{

    private static final String FIELD_POSTER_PATH = "poster_path";
    private static final String FIELD_RUNTIME = "runtime";
    private static final String FIELD_VIDEO = "video";
    private static final String FIELD_ORIGINAL_TITLE = "original_title";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_BELONGS_TO_COLLECTION = "belongs_to_collection";
    private static final String FIELD_REVENUE = "revenue";
    private static final String FIELD_ID = "id";
    private static final String FIELD_IMDB_ID = "imdb_id";
    private static final String FIELD_VOTE_COUNT = "vote_count";
    private static final String FIELD_PRODUCTION_COMPANIES = "production_companies";
    private static final String FIELD_TAGLINE = "tagline";
    private static final String FIELD_ADULT = "adult";
    private static final String FIELD_GENRES = "genres";
    private static final String FIELD_OVERVIEW = "overview";
    private static final String FIELD_BUDGET = "budget";
    private static final String FIELD_HOMEPAGE = "homepage";
    private static final String FIELD_RELEASE_DATE = "release_date";
    private static final String FIELD_POPULARITY = "popularity";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_VOTE_AVERAGE = "vote_average";
    private static final String FIELD_BACKDROP_PATH = "backdrop_path";
    private static final String FIELD_SPOKEN_LANGUAGES = "spoken_languages";
    private static final String FIELD_PRODUCTION_COUNTRIES = "production_countries";


    @SerializedName(FIELD_POSTER_PATH)
    private String mPosterPath;
    @SerializedName(FIELD_RUNTIME)
    private int mRuntime;
    @SerializedName(FIELD_VIDEO)
    private boolean mVideo;
    @SerializedName(FIELD_ORIGINAL_TITLE)
    private String mOriginalTitle;
    @SerializedName(FIELD_STATUS)
    private String mStatus;
    @SerializedName(FIELD_BELONGS_TO_COLLECTION)
    private BelongsToCollection mBelongsToCollection;
    @SerializedName(FIELD_REVENUE)
    private int mRevenue;
    @SerializedName(FIELD_ID)
    private int mId;
    @SerializedName(FIELD_IMDB_ID)
    private String mImdbId;
    @SerializedName(FIELD_VOTE_COUNT)
    private int mVoteCount;
    @SerializedName(FIELD_PRODUCTION_COMPANIES)
    private List<ProductionCompany> mProductionCompanies;
    @SerializedName(FIELD_TAGLINE)
    private String mTagline;
    @SerializedName(FIELD_ADULT)
    private boolean mAdult;
    @SerializedName(FIELD_GENRES)
    private List<Genre> mGenres;
    @SerializedName(FIELD_OVERVIEW)
    private String mOverview;
    @SerializedName(FIELD_BUDGET)
    private int mBudget;
    @SerializedName(FIELD_HOMEPAGE)
    private String mHomepage;
    @SerializedName(FIELD_RELEASE_DATE)
    private String mReleaseDate;
    @SerializedName(FIELD_POPULARITY)
    private double mPopularity;
    @SerializedName(FIELD_TITLE)
    private String mTitle;
    @SerializedName(FIELD_VOTE_AVERAGE)
    private double mVoteAverage;
    @SerializedName(FIELD_BACKDROP_PATH)
    private String mBackdropPath;
    @SerializedName(FIELD_SPOKEN_LANGUAGES)
    private List<SpokenLanguage> mSpokenLanguages;
    @SerializedName(FIELD_PRODUCTION_COUNTRIES)
    private List<ProductionCountry> mProductionCountries;


    public MovieData(){

    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public void setVideo(boolean video) {
        mVideo = video;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        mBelongsToCollection = belongsToCollection;
    }

    public BelongsToCollection getBelongsToCollection() {
        return mBelongsToCollection;
    }

    public void setRevenue(int revenue) {
        mRevenue = revenue;
    }

    public int getRevenue() {
        return mRevenue;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setBudget(int budget) {
        mBudget = budget;
    }

    public int getBudget() {
        return mBudget;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        mSpokenLanguages = spokenLanguages;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return mSpokenLanguages;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        mProductionCountries = productionCountries;
    }

    public List<ProductionCountry> getProductionCountries() {
        return mProductionCountries;
    }

}