package com.alex.alexintentdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alex.alexintentdemo.R;
import com.alex.alexintentdemo.intent.CommonUtils;
import com.alex.alexintentdemo.http.ApiManager;
import com.alex.alexintentdemo.http.Response;
import com.alex.alexintentdemo.http.RxHttp;
import com.alex.alexintentdemo.model.IntentModel;
import com.alex.alexintentdemo.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.button_1)
    Button toActivity;
    @InjectView(R.id.button_2)
    Button toWebView;

    private IntentModel intentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        getIntentRule();

        toActivity.setOnClickListener(this);
        toWebView.setOnClickListener(this);

    }

    private void getIntentRule() {
        startLoading();
        Map<String, String> param = new HashMap<>();
        new RxHttp<IntentModel>().send(ApiManager.getService().getIntentRule(param),
                new Response<IntentModel>(MainActivity.this) {

                    @Override
                    public void onNext(IntentModel model) {
                        intentModel = model;
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        stopLoading();
                    }
                });
    }

    /**
     * Button1 是直接打开Activity，没有参数{
     * 跳转的规则如下：
     * "intent:#Intent;component=com.alex.alexintentdemo/.ui.TestActivity;end"
     * }
     * Button2 是直接打开WebViewActivity，没有参数
     * {
     * 跳转的规则如下：
     * "http://www.baidu.com""
     * }
     *
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                CommonUtils.jumpWithUri(MainActivity.this, intentModel.getData().getIntent_rule_1());
                break;
            case R.id.button_2:
                CommonUtils.jumpWithUri(MainActivity.this, intentModel.getData().getIntent_rule_2());
                break;
            default:
                break;
        }
    }
}
