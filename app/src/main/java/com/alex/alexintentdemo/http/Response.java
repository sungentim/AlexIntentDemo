package com.alex.alexintentdemo.http;

import android.content.Context;

import com.alex.alexintentdemo.utils.AlexLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by lizetong on 15/9/15.
 * ********************************************************
 * ******************  使用说明  ***************************
 * ********************************************************
 * 此类目的是Http请求后的回调方法；
 * 正常情况下只需要重写方法onSuccess方法；
 * 各个方法的使用，看注释
 *
 * @param <T> 返回的数据类型
 */
public class Response<T> extends Subscriber<T> {

    private Context mContext;

    public Response(Context context) {
        this.mContext = context;
    }


    /**
     * 此方法现在onNext或者onError之后都会调用
     * 所以一般要处理请求结束时的信息是，需要重写此方法
     * 例如，loading结束时，刷新下拉刷新结果时等…………
     */
    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {

    }

    /**
     * 除非非要获取网络错误信息，否则一般不需要重写此方法；
     * 例如：网络400，404，断网，超时，等等…………
     */
    @Override
    public void onError(Throwable e) {
        onCompleted();
        if (e == null || mContext == null)
            return;
        try {
            if (e instanceof ConnectException || e instanceof UnknownHostException) {
                AlexLog.e("exception", e.getMessage());
            } else if (e instanceof SocketTimeoutException) {
                AlexLog.e("exception", e.getMessage());
            } else if (e instanceof HttpException) {
                AlexLog.e("exception", e.getMessage());
            }
        } catch (Exception ignored) {

        }
        if (e.getMessage() != null)
            AlexLog.e("exception", e.getMessage());
    }

}