package com.maksing.moviedbdata.datastore;

import com.maksing.moviedbdata.data.AccountData;
import com.maksing.moviedbdata.data.GuestSessionData;
import com.maksing.moviedbdata.data.RequestTokenData;
import com.maksing.moviedbdata.data.SessionData;
import com.maksing.moviedbdata.data.movie.MovieData;
import com.maksing.moviedbdata.data.movie.MovieListData;
import com.maksing.moviedbdomain.entity.MovieList;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface MovieDbMovieDataStore {
    @GET("/discover/movie?{query}")
    Observable<MovieListData> getTopMovies(@Query("api_key") String apiKey, @Path("query") String query, @Query("page") int page);

    @GET("/movie/{id}")
    Observable<MovieData> getMovieById(@Query("api_key") String apiKey, @Path("id") int id);
}
