package com.alex.alexintentdemo.http;


import com.alex.alexintentdemo.model.IntentModel;
import com.alex.alexintentdemo.utils.Constants;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by lizetong on 15/7/9.
 */
public interface ApiService {

    //判断手机号是否已经注册
    @FormUrlEncoded
    @POST(Constants.API_INTENT_RULE)
    Observable<IntentModel> getIntentRule(@FieldMap Map<String, String> params);


}
