package com.maksing.moviedbdomain.query;

import com.maksing.moviedbdomain.usecase.UseCase;

/**
 * Created by maksing on 25/1/15.
 */
public interface DiscoverQuery extends Query {
    public String getQuery();
    public int getPage();
}
