package com.maksing.moviedbdata.datastore;

import com.maksing.moviedbdata.data.AccountData;
import com.maksing.moviedbdata.data.GuestSessionData;
import com.maksing.moviedbdata.data.RequestTokenData;
import com.maksing.moviedbdata.data.SessionData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface MovieDbAuthenticateDataStore {
    @GET("/authentication/token/new")
    Observable<RequestTokenData> createRequestToken(@Query("api_key") String apiKey);

    @GET("/authentication/token/validate_with_login")
    Observable<RequestTokenData> login(@Query("api_key") String apiKey, @Query("request_token") String requestToken, @Query("username") String userName, @Query("password") String password);

    @GET("/authentication/session/new")
    Observable<SessionData> createSession(@Query("api_key") String apiKey, @Query("request_token") String requestToken);

    @GET("/authentication/guest_session/new")
    Observable<GuestSessionData> createGuestSession(@Query("api_key") String apiKey);

    @GET("/account")
    Observable<AccountData> getAccount(@Query("api_key") String apiKey, @Query("session_id") String sessionId);
}
