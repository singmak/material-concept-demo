package com.maksing.moviedbdata.datastore;

import retrofit.RestAdapter;

/**
 * Created by maksing on 23/12/14.
 */
public class MovieDbAuthenticateDataStoreFactory {
    private final String mEndpoint;
    private final RestAdapter mRestAdapter;
    private final MovieDbAuthenticateDataStore mResApiDataStore;

    public MovieDbAuthenticateDataStoreFactory(String endpoint) {
        mEndpoint = endpoint;
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(mEndpoint)
                .build();
        mResApiDataStore = mRestAdapter.create(MovieDbAuthenticateDataStore.class);
    }

    public MovieDbAuthenticateDataStore create() {
        return mResApiDataStore;
    }
}
