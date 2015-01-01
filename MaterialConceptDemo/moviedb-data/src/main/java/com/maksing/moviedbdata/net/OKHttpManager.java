package com.maksing.moviedbdata.net;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by maksing on 1/1/15.
 */
public class OKHttpManager {
    private final OkHttpClient client = new OkHttpClient();

    private static class LazyHolder {
        private static final OKHttpManager INSTANCE = new OKHttpManager();
    }

    public static OKHttpManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public OkHttpClient getClient() {
        return client;
    }
}
