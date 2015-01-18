package com.maksing.moviedbdomain.usecase.session;

import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.entity.User;
import com.maksing.moviedbdomain.service.ServiceHolder;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 31/12/14.
 */
public class GetUserDataUseCase extends SessionUseCase {
    public GetUserDataUseCase(ServiceHolder serviceHolder) {
        super(serviceHolder);
    }

    public Observable<User> getObservable() {
        return getCurrentSession().map(new Func1<Session, User>() {
            @Override
            public User call(Session session) {
                return session.getUser();
            }
        });
    }
}
