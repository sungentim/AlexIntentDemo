package com.alex.alexintentdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alex.alexintentdemo.R;
import com.alex.alexintentdemo.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebViewActivity extends BaseActivity {

    private static String mUrl = "";

    @InjectView(R.id.web_view)
    WebView mWebView;

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);

        ButterKnife.inject(this);

        if (getIntent() != null) {
            mUrl = getIntent().getStringExtra("url");
        }

        init();
    }

    private void init() {
        //WebView加载web资源
        mWebView.loadUrl(mUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                startLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                stopLoading();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
