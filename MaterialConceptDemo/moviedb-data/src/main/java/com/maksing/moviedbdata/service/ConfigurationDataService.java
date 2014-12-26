package com.maksing.moviedbdata.service;

import android.content.Context;

import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.data.ImageConfigData;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.service.ConfigurationService;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class ConfigurationDataService implements ConfigurationService {
    private final MovieDbConfigDataStoreFactory mMovieDbConfigDataStoreFactory;
    private final Context mContext;
    private final String mApiKey;

    private static volatile ConfigurationDataService sInstance;

    public static ConfigurationDataService getInstance(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (sInstance == null) {
            synchronized (ConfigurationDataService.class) {
                if (sInstance == null) {
                    sInstance = new ConfigurationDataService(context, factory, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private ConfigurationDataService(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (context == null || factory == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbConfigDataStoreFactory = factory;
        mContext = context;
        mApiKey = apiKey;
    }

    @Override
    public Observable<MovieDbConfig> getMovieDbConfiguration() {
        return mMovieDbConfigDataStoreFactory.create().getConfiguration(mApiKey).map(new Func1<ConfigurationData, MovieDbConfig>() {
            @Override
            public MovieDbConfig call(ConfigurationData configurationData) {
                ImageConfigData imageConfigData = configurationData.getImage();
                String baseUrl = "";
                List<String> posterSizesList = null;
                List<String> backdropSizesList = null;

                if (imageConfigData != null) {
                    baseUrl = imageConfigData.getBaseUrl();
                    posterSizesList = imageConfigData.getPosterSizes();
                    backdropSizesList = imageConfigData.getBackdropSizes();
                }

                return new MovieDbConfig(baseUrl, posterSizesList, backdropSizesList);
            }
        }).subscribeOn(Schedulers.io());
    }
}
