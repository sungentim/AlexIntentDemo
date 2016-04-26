package com.alex.alexintentdemo.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.alex.alexintentdemo.R;


/**
 * Created by lizetong on 15/8/30.
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context, R.style.custom_loading_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ncf_loading_layout);
        setCanceledOnTouchOutside(false);
    }
}
