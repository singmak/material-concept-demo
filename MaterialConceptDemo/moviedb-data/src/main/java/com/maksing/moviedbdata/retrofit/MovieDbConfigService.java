package com.maksing.moviedbdata.retrofit;

import com.maksing.moviedbdata.model.ConfigurationData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface MovieDbConfigService {
    @GET("/configuration")
    Observable<ConfigurationData> getConfiguration(@Query("api_key") String apiKey);
}
