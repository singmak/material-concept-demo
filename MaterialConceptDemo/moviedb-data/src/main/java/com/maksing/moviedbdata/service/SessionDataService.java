package com.maksing.moviedbdata.service;

import android.content.Context;

import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.service.SessionService;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class SessionDataService implements SessionService {
    private final MovieDbConfigDataStoreFactory mMovieDbConfigDataStoreFactory;
    private final Context mContext;
    private final String mApiKey;

    private static volatile SessionDataService sInstance;

    public static SessionDataService getInstance(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (sInstance == null) {
            synchronized (SessionDataService.class) {
                if (sInstance == null) {
                    sInstance = new SessionDataService(context, factory, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private SessionDataService(Context context, MovieDbConfigDataStoreFactory factory, String apiKey) {
        if (context == null || factory == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbConfigDataStoreFactory = factory;
        mContext = context;
        mApiKey = apiKey;
    }

    @Override
    public Observable<Session> getAuthenticatedSession() {
        if (mCurrentSession != null) {
            return Observable.just(mCurrentSession);
        } else {
            return mSess.create().getConfiguration(mApiKey).map(new Func1<ConfigurationData, MovieDbConfig>() {
                @Override
                public MovieDbConfig call(ConfigurationData configurationData) {
                    //TODO: do the mapping
                    mCachedMovieDbConfig = new MovieDbConfig();
                    return mCachedMovieDbConfig;
                }
            }).subscribeOn(Schedulers.io());
        }
    }

    @Override
    public Observable<Session> getGuestSession() {
        return null;
    }
}
