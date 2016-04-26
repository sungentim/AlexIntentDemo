package com.alex.alexintentdemo.http;

import com.alex.alexintentdemo.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizetong on 15/7/3.
 */
public class ApiManager {

    private static ApiManager mInstance;

    private ApiService mApiService;

    public ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ROOT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpUtils.getInstance())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    //单例模式
    public static ApiService getService() {
        if (mInstance == null) {
            mInstance = new ApiManager();
        }
        return mInstance.mApiService;
    }

}
