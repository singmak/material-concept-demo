package com.maksing.moviedbdomain.service;

/**
 * Created by maksing on 26/12/14.
 */
public interface ServiceHolder {
    public ConfigurationService getConfigurationService();
    public MovieService getMovieService();
    public SessionService getSessionService();
    public NavigationService getNavigationService();
}
