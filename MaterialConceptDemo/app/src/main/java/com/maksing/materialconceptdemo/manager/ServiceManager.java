package com.maksing.materialconceptdemo.manager;

import android.content.Context;

import com.maksing.materialconceptdemo.R;
import com.maksing.moviedbdata.service.ConfigurationDataService;
import com.maksing.moviedbdata.service.MovieDataService;
import com.maksing.moviedbdata.service.NavigationDataService;
import com.maksing.moviedbdata.service.SessionDataService;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.service.MovieService;
import com.maksing.moviedbdomain.service.NavigationService;
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
                return ConfigurationDataService.getInstance(mContext, END_POINT, API_KEY);
            }

            @Override
            public MovieService getMovieService() {
                return MovieDataService.getInstance(END_POINT, API_KEY);
            }

            @Override
            public SessionService getSessionService() {
                return SessionDataService.getInstance(END_POINT, API_KEY);
            }

            @Override
            public NavigationService getNavigationService() {
                return NavigationDataService.getInstance();
            }
        };
    }

    public ServiceHolder getServiceHolder() {
        return mServiceHolder;
    }
}
