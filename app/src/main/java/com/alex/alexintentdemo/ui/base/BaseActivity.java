package com.alex.alexintentdemo.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.alex.alexintentdemo.view.LoadingDialog;

/**
 * Created by lizetong on 16/4/25.
 */
public class BaseActivity extends AppCompatActivity {

    private LoadingDialog loadingDialog;

    public void startLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    public void stopLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
