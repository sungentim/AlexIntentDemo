package com.alex.alexintentdemo.http;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lizetong on 15/10/21.
 */
public class RxHttp<T> {

    public void send(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe((subscriber));
    }
}
