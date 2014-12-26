package com.maksing.moviedbdata.datastore;

import retrofit.RestAdapter;

/**
 * Created by maksing on 23/12/14.
 */
public class MovieDbMovieDataStoreFactory {
    private final String mEndpoint;
    private final RestAdapter mRestAdapter;
    private final MovieDbMovieDataStore mResApiDataStore;

    public MovieDbMovieDataStoreFactory(String endpoint) {
        mEndpoint = endpoint;
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(mEndpoint)
                .build();
        mResApiDataStore = mRestAdapter.create(MovieDbMovieDataStore.class);
    }

    public MovieDbMovieDataStore create() {
        return mResApiDataStore;
    }
}
