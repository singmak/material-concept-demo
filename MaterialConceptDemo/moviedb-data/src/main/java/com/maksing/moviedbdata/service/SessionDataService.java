package com.maksing.moviedbdata.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.maksing.moviedbdata.model.AccountData;
import com.maksing.moviedbdata.model.GuestSessionData;
import com.maksing.moviedbdata.model.RequestTokenData;
import com.maksing.moviedbdata.model.SessionData;
import com.maksing.moviedbdata.retrofit.MovieDbAuthenticateService;
import com.maksing.moviedbdata.retrofit.RetrofitServiceStore;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.entity.User;
import com.maksing.moviedbdomain.service.SessionService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 24/12/14.
 */
public class SessionDataService extends HttpService implements SessionService {
    private final RetrofitServiceStore<MovieDbAuthenticateService> mServiceStore;
    private final String mApiKey;

    private static volatile SessionDataService sInstance;
    private SharedPreferences mPrefs;

    private static final String KEY_GUEST_SESSION = "KEY_GUEST_SESSION";

    public static SessionDataService getInstance(SharedPreferences prefs, String endPoint, String apiKey) {
        if (sInstance == null) {
            synchronized (SessionDataService.class) {
                if (sInstance == null) {
                    sInstance = new SessionDataService(prefs, endPoint, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private SessionDataService(SharedPreferences prefs, String endPoint, String apiKey) {
        if (endPoint == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mServiceStore = new RetrofitServiceStore<MovieDbAuthenticateService>(endPoint, MovieDbAuthenticateService.class);
        mApiKey = apiKey;
        mPrefs = prefs;
    }

    @Override
    public Observable<String> getRequestToken() {
        return mServiceStore.getService().createRequestToken(mApiKey).map(new Func1<RequestTokenData, String>() {
            @Override
            public String call(RequestTokenData requestToken) {
                return requestToken.getRequestToken();
            }
        });
    }

    @Override
    public Observable<Session> getAuthenticatedSession(String requestToken) {
        final MovieDbAuthenticateService store = mServiceStore.getService();
        return store.createSession(mApiKey, requestToken).flatMap(new Func1<SessionData, Observable<Session>>() {
            @Override
            public Observable<Session> call(final SessionData sessionData) {
                return store.getAccount(mApiKey, sessionData.getSessionId()).map(new Func1<AccountData, Session>() {
                    @Override
                    public Session call(AccountData accountData) {
                        User user = new User(Integer.toString(accountData.getId()), accountData.getUsername());
                        user.setIncludeAdult(accountData.isIncludeAdult());
                        return new Session(user, sessionData.getSessionId());
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> authenticateRequestToken(String requestToken, String userName, String password) {
        return mServiceStore.getService().login(mApiKey, requestToken, userName, password).map(new Func1<RequestTokenData, String>() {
            @Override
            public String call(RequestTokenData requestTokenData) {
                return requestTokenData.getRequestToken();
            }
        });
    }

    private Session getSavedGuestSession() {
        return (Session)jsonToObject(mPrefs.getString(KEY_GUEST_SESSION, ""), Session.class);
    }

    private void saveGuestSession(Session session) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_GUEST_SESSION, objectToJson(session));
        editor.apply();
    }

    @Override
    public Observable<Session> getGuestSession() {

        final Session session = getSavedGuestSession();

        if (session != null && !TextUtils.isEmpty(session.getSessionId())) {
            return Observable.just(session);
        } else {
            return mServiceStore.getService().createGuestSession(mApiKey).map(new Func1<GuestSessionData, Session>() {
                @Override
                public Session call(GuestSessionData guestSessionData) {
                    Session newSession = new Session(User.GUEST, guestSessionData.getGuestSessionId());
                    saveGuestSession(newSession);
                    return newSession;
                }
            });
        }
    }
}
