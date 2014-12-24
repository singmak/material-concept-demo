package com.maksing.moviedbdata.datastore;

import com.maksing.moviedbdata.data.ConfigurationData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface MovieDbConfigDataStore {
    @GET("/configuration")
    Observable<ConfigurationData> getConfiguration(@Query("api_key") String apiKey);
}
