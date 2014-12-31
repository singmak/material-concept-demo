package com.maksing.moviedbdata.retrofit;

import retrofit.RestAdapter;

/**
 * Created by maksing on 1/1/15.
 */
public class RetrofitServiceStore<T> {
    private final String mEndpoint;
    private final RestAdapter mRestAdapter;
    private final T mResApiService;

    public RetrofitServiceStore(String endpoint, Class<T> service) {
        mEndpoint = endpoint;
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(mEndpoint)
                .build();
        mResApiService = mRestAdapter.create(service);
    }

    public T getService() {
        return mResApiService;
    }
}
