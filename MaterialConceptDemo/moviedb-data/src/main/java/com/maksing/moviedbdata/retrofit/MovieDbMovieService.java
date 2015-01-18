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
    @GET("/movie/{id}/reviews")
    Observable<MovieData> getMovieReviewsById(@Query("api_key") String apiKey, @Path("id") int id);
    @GET("/movie/{id}/similar")
    Observable<MovieData> getSimilarMovieById(@Query("api_key") String apiKey, @Path("id") int id);
    @GET("/movie/{id}/credits")
    Observable<MovieData> getMovieCreditsById(@Query("api_key") String apiKey, @Path("id") int id);
    @GET("/movie/{id}/videos")
    Observable<MovieData> getMovieVideosById(@Query("api_key") String apiKey, @Path("id") int id);
    @GET("/movie/now_playing")
    Observable<MovieData> getNowPlayingMovies(@Query("api_key") String apiKey);
    @GET("/movie/popular")
    Observable<MovieData> getPopularMovies(@Query("api_key") String apiKey);
    @GET("/movie/top_rated")
    Observable<MovieData> getTopRatedMovies(@Query("api_key") String apiKey);
}
