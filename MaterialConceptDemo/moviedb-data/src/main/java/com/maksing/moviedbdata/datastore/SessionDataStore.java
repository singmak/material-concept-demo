package com.maksing.moviedbdata.datastore;

import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdomain.entity.Session;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface SessionDataStore {
    Observable<Session> getConfiguration(@Query("api_key") String apiKey);
}
