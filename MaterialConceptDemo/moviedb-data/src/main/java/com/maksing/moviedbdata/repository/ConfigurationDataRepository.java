package com.maksing.moviedbdata.repository;

import android.content.Context;

import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStore;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.repository.ConfigurationRepository;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class ConfigurationDataRepository implements ConfigurationRepository {
    MovieDbConfigDataStore mMovieDbConfigDataStore;
    Context mContext;
    String mApiKey;
    MovieDbConfig mCachedMovieDbConfig;

    private static volatile ConfigurationDataRepository sInstance;

    public static ConfigurationDataRepository getInstance(Context context, MovieDbConfigDataStoreFactory builder, String apiKey) {
        if (sInstance == null) {
            synchronized (ConfigurationDataRepository.class) {
                if (sInstance == null) {
                    sInstance = new ConfigurationDataRepository(context, builder, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private ConfigurationDataRepository(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (context == null || factory == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbConfigDataStore = factory.create();
        mContext = context;
        mApiKey = apiKey;
    }

    @Override
    public Observable<MovieDbConfig> getMovieDbConfiguration() {
        if (mCachedMovieDbConfig != null) {
            return Observable.just(mCachedMovieDbConfig);
        } else {
            return mMovieDbConfigDataStore.getConfiguration(mApiKey).map(new Func1<ConfigurationData, MovieDbConfig>() {
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
