package com.maksing.moviedbdata.retrofit;

import com.maksing.moviedbdata.model.movie.MovieData;
import com.maksing.moviedbdata.model.movie.MovieListData;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by maksing on 23/12/14.
 */
public interface MovieDbMovieService {
    @GET("/movie/{id}")
    Observable<MovieData> getMovieById(@Query("api_key") String apiKey, @Path("id") int id);
}
