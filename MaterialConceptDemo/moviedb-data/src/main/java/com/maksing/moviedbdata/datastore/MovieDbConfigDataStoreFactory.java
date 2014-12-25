package com.maksing.moviedbdata.datastore;

import retrofit.RestAdapter;

/**
 * Created by maksing on 23/12/14.
 */
public class MovieDbConfigDataStoreFactory {
    private final String mEndpoint;

    public MovieDbConfigDataStoreFactory(String endpoint) {
        mEndpoint = endpoint;
    }

    public MovieDbConfigDataStore create() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(mEndpoint)
                .build();

        return restAdapter.create(MovieDbConfigDataStore.class);
    }
}
