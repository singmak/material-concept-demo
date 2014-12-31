package com.maksing.moviedbdata.service;

import android.content.Context;

import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.data.movie.Genre;
import com.maksing.moviedbdata.data.movie.MovieData;
import com.maksing.moviedbdata.data.movie.MovieListData;
import com.maksing.moviedbdata.data.movie.Result;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdata.datastore.MovieDbMovieDataStore;
import com.maksing.moviedbdata.datastore.MovieDbMovieDataStoreFactory;
import com.maksing.moviedbdomain.entity.Movie;
import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.entity.MovieList;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maksing on 24/12/14.
 */
public class MovieDataService implements MovieService {
    private final String mApiKey;

    private static volatile MovieDataService sInstance;
    private final MovieDbMovieDataStoreFactory mMovieDbMovieDataStoreFactory;

    public static MovieDataService getInstance(MovieDbMovieDataStoreFactory factory, String apiKey) {
        if (sInstance == null) {
            synchronized (MovieDataService.class) {
                if (sInstance == null) {
                    sInstance = new MovieDataService(factory, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private MovieDataService(MovieDbMovieDataStoreFactory factory, String apiKey) {
        if (factory == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mMovieDbMovieDataStoreFactory = factory;
        mApiKey = apiKey;
    }

    @Override
    public Observable<MovieList> getDiscoverMovieList(String query, int page, final String posterBasePath, final String backdropBasePath) {
        return mMovieDbMovieDataStoreFactory.create().getTopMovies(mApiKey, "popularity.desc", page).map(new Func1<MovieListData, MovieList>() {
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
        return mMovieDbMovieDataStoreFactory.create().getMovieById(mApiKey, id).map(new Func1<MovieData, Movie>() {
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
