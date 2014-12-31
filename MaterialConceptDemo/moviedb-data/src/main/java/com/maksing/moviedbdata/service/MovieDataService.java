package com.maksing.moviedbdata.service;

import com.maksing.moviedbdata.model.movie.Genre;
import com.maksing.moviedbdata.model.movie.MovieData;
import com.maksing.moviedbdata.model.movie.MovieListData;
import com.maksing.moviedbdata.model.movie.Result;
import com.maksing.moviedbdata.retrofit.MovieDbMovieService;
import com.maksing.moviedbdata.retrofit.RetrofitServiceStore;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 24/12/14.
 */
public class MovieDataService implements MovieService {
    private final String mApiKey;

    private static volatile MovieDataService sInstance;
    private final RetrofitServiceStore<MovieDbMovieService> mMovieDbMovieServiceStore;

    public static MovieDataService getInstance(String endPoint, String apiKey) {
        if (sInstance == null) {
            synchronized (MovieDataService.class) {
                if (sInstance == null) {
                    sInstance = new MovieDataService(endPoint, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private MovieDataService(String endPoint, String apiKey) {
        if (endPoint == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbMovieServiceStore = new RetrofitServiceStore<>(endPoint, MovieDbMovieService.class);
        mApiKey = apiKey;
    }

    @Override
    public Observable<MovieList> getDiscoverMovieList(String query, int page, final String posterBasePath, final String backdropBasePath) {
        return mMovieDbMovieServiceStore.getService().getDiscoverMovies(mApiKey, query, page).map(new Func1<MovieListData, MovieList>() {
            @Override
            public MovieList call(MovieListData movieListData) {
                List<Movie> movies = new ArrayList<Movie>();

                if (movieListData.getResults() != null) {
                    for (Result result : movieListData.getResults()) {
                        movies.add(new Movie(String.valueOf(result.getId()), result.getTitle(), posterBasePath + result.getPosterPath(), backdropBasePath + result.getBackdropPath()));
                    }
                }

                return new MovieList(movieListData.getPage(), movieListData.getTotalPage(), movies);
            }
        });
    }

    @Override
    public Observable<Movie> getMovieById(int id, final String posterBasePath, final String backdropBasePath) {
        return mMovieDbMovieServiceStore.getService().getMovieById(mApiKey, id).map(new Func1<MovieData, Movie>() {
            @Override
            public Movie call(MovieData movieData) {
                Movie movie = new Movie(String.valueOf(movieData.getId()), movieData.getTitle(), posterBasePath + movieData.getPosterPath(), backdropBasePath + movieData.getBackdropPath());
                movie.setDescription(movieData.getOverview());

                List<String> genres = new ArrayList<>();
                if (movie.getGenres() != null) {
                    for (Genre genre : movieData.getGenres()) {
                        genres.add(genre.getName());
                    }
                }

                movie.setGenres(genres);
                return movie;
            }
        });
    }
}
