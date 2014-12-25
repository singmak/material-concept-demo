package com.maksing.materialconceptdemo.manager;

import android.content.Context;

import com.maksing.materialconceptdemo.R;
import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdata.service.ConfigurationDataService;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.service.MovieService;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.service.SessionService;

/**
 * Created by maksing on 26/12/14.
 */
public class ServiceManager {
    private final ServiceHolder mServiceHolder;
    private final Context mContext = ApplicationManager.getInstance().getApplicationContext();
    private final String END_POINT = mContext.getString(R.string.api_endpoint);
    private final String API_KEY = mContext.getString(R.string.moviedb_apikey);

    private static class LazyLoader {
        private static final ServiceManager INSTANCE = new ServiceManager();
    }

    synchronized public static ServiceManager getInstance() {
        return LazyLoader.INSTANCE;
    }

    private ServiceManager() {
        mServiceHolder = new ServiceHolder() {
            @Override
            public ConfigurationService getConfigurationService() {
                return ConfigurationDataService.getInstance(mContext, new MovieDbConfigDataStoreFactory(END_POINT), API_KEY);
            }

            @Override
            public MovieService getMovieService() {
                return null;
            }

            @Override
            public SessionService getSessionService() {
                return null;
            }
        };
    }

    public ServiceHolder getServiceHolder() {
        return mServiceHolder;
    }
}
