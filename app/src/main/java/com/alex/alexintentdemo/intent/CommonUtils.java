package com.alex.alexintentdemo.intent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alex.alexintentdemo.ui.WebViewActivity;
import com.alex.alexintentdemo.utils.AlexLog;

import java.net.URISyntaxException;

public class CommonUtils {

    private static final String SCHEME_INTENT = "intent";
    private static final String SCHEME_HTTP = "http";
    private static final String SCHEME_HTTPS = "https";

    /**
     * @param strUri 跳转时的规则
     * @return  如果成功则返回Result.SUCCESS
     *          否则返回Result.FAILED
     */
    public static int jumpWithUri(Context context, String strUri) {
        try {
            if (strUri == null || strUri.trim().length() <= 0) {
                return Result.FAILED;
            }
            String uri = strUri.trim();
            Uri curUri = Uri.parse(uri);
            String scheme = curUri.getScheme();
            if (scheme == null) {
                throw new URISyntaxException(uri, "scheme is null");
            }
            if (scheme.startsWith(SCHEME_INTENT)) {
                Intent intent = Intent.parseUri(uri, Intent.URI_INTENT_SCHEME);

                String appPackage = context.getPackageName();
                ComponentName componentName = intent.getComponent();
                if (componentName == null || !appPackage.contains(componentName.getPackageName()))//外部的intent
                {
                    context.startActivity(intent);
                    return Result.SUCCESS;
                }

                intent = UriUtils.parseIntentUri(uri, Intent.URI_INTENT_SCHEME);
                componentName = intent.getComponent();

                Class<?> cls = Class.forName(componentName.getClassName());
                intent.setAction(null);
                intent.setComponent(null);
                intent.setClass(context, cls);


                AlexLog.d(CommonUtils.class.getSimpleName(), "" + intent.toString());
                context.startActivity(intent);
            } else if (scheme.startsWith(SCHEME_HTTP) || scheme.startsWith(SCHEME_HTTPS)) {
                WebViewActivity.launch(context, uri);
            } else {
                return Result.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAILED;
        }
        return Result.SUCCESS;
    }
}
