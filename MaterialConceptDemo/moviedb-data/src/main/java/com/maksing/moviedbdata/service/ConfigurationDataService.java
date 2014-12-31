package com.maksing.moviedbdata.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.maksing.moviedbdata.R;
import com.maksing.moviedbdata.model.ConfigurationData;
import com.maksing.moviedbdata.model.ImageConfigData;
import com.maksing.moviedbdata.retrofit.MovieDbConfigService;
import com.maksing.moviedbdata.retrofit.RetrofitServiceStore;
import com.maksing.moviedbdomain.entity.DeviceConfig;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.entity.User;
import com.maksing.moviedbdomain.manager.AuthenticationManager;
import com.maksing.moviedbdomain.service.ConfigurationService;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class ConfigurationDataService extends HttpService implements ConfigurationService {
    private final RetrofitServiceStore<MovieDbConfigService> mMovieDbConfigServiceStore;
    private final Context mContext;
    private final String mApiKey;
    private static final String PREF_SETTINGS = "PREF_SETTINGS";
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_ID = "KEY_ID";
    private static final String KEY_SESSION_ID = "KEY_SESSION_ID";

    private static volatile ConfigurationDataService sInstance;

    public static ConfigurationDataService getInstance(Context context, String endPoint, String apiKey) {
        if (sInstance == null) {
            synchronized (ConfigurationDataService.class) {
                if (sInstance == null) {
                    sInstance = new ConfigurationDataService(context, endPoint, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private ConfigurationDataService(Context context, String endPoint, String apiKey) {
        if (context == null || endPoint == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbConfigServiceStore = new RetrofitServiceStore<>(endPoint, MovieDbConfigService.class);
        mContext = context;
        mApiKey = apiKey;
    }

    @Override
    public Observable<MovieDbConfig> getMovieDbConfiguration() {
        return mMovieDbConfigServiceStore.getService().getConfiguration(mApiKey).map(new Func1<ConfigurationData, MovieDbConfig>() {
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

    @Override
    public Observable<DeviceConfig> getDeviceConfiguration() {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE);

        String username = sharedPreferences.getString(KEY_USERNAME, "");
        String id = sharedPreferences.getString(KEY_ID, "");
        String sessionId = sharedPreferences.getString(KEY_SESSION_ID, "");

        if (!TextUtils.isEmpty(sessionId)) {
            AuthenticationManager.getInstance().setCurrentSession(new Session(new User(id, username), sessionId));
        }

        return Observable.just(new DeviceConfig(mContext.getResources().getBoolean(R.bool.isTablet)));
    }
}
