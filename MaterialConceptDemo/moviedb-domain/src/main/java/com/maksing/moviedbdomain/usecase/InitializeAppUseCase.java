package com.maksing.moviedbdomain.usecase;

import com.maksing.moviedbdomain.entity.MovieDbConfig;
import com.maksing.moviedbdomain.repository.ConfigurationRepository;

import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by maksing on 24/12/14.
 */
public class InitializeAppUseCase implements UseCase {
    private ConfigurationRepository mConfigurationRepository;
    private Scheduler mObserverScheduler;

    public InitializeAppUseCase(ConfigurationRepository repository, Scheduler ObserverScheduler) {
        mConfigurationRepository = repository;
        mObserverScheduler = ObserverScheduler;
    }

    public void execute(final Callback callback) {
        mConfigurationRepository.getMovieDbConfiguration().cache().subscribe(new Subscriber<MovieDbConfig>() {
            @Override
            public void onCompleted() {
                callback.onInitialized();
            }

            @Override
            public void onError(Throwable e) {
                callback.onOutageOccured();
            }

            @Override
            public void onNext(MovieDbConfig movieDbConfig) {

            }
        });
    }

    public interface Callback {
        void onInitialized();
        void onOutageOccured();
        void onSessionExpired();
    }
}
