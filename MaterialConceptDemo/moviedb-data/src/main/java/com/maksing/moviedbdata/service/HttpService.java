package com.maksing.moviedbdata.service;

import com.google.gson.Gson;
import com.maksing.moviedbdata.net.OKHttpManager;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by maksing on 1/1/15.
 */
public class HttpService {
    private final OkHttpClient client = OKHttpManager.getInstance().getClient();
    private final Gson gson = new Gson();

    protected enum Method {
        GET, POST
    }

    protected Observable requestGet(final String url, final Class model) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    Response response = get(url);
                    Object obj = gson.fromJson(response.body().charStream(), model);
                    subscriber.onNext(obj);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response;
    }

    private Response post(String url, RequestBody requestBody) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response;
    }
}
