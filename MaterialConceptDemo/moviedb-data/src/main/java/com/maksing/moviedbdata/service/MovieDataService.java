package com.maksing.moviedbdata.service;

import android.content.Context;

import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.service.ConfigurationService;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class MovieDataService implements ConfigurationService {
    private final MovieDbConfigDataStoreFactory mMovieDbConfigDataStoreFactory;
    private final Context mContext;
    private final String mApiKey;

    private MovieDbConfig mCachedMovieDbConfig;

    private static volatile MovieDataService sInstance;

    public static MovieDataService getInstance(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (sInstance == null) {
            synchronized (MovieDataService.class) {
                if (sInstance == null) {
                    sInstance = new MovieDataService(context, factory, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private MovieDataService(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (context == null || factory == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbConfigDataStoreFactory = factory;
        mContext = context;
        mApiKey = apiKey;
    }

    @Override
    public Observable<MovieDbConfig> getMovieDbConfiguration() {
        if (mCachedMovieDbConfig != null) {
            return Observable.just(mCachedMovieDbConfig);
        } else {
            return mMovieDbConfigDataStoreFactory.create().getConfiguration(mApiKey).map(new Func1<ConfigurationData, MovieDbConfig>() {
                @Override
                public MovieDbConfig call(ConfigurationData configurationData) {
                    //TODO: do the mapping
                    mCachedMovieDbConfig = new MovieDbConfig();
                    return mCachedMovieDbConfig;
                }
            }).subscribeOn(Schedulers.io());
        }
    }
}
