package com.alex.alexintentdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alex.alexintentdemo.R;
import com.alex.alexintentdemo.ui.base.BaseActivity;

/**
 * Created by lizetong on 16/4/25.
 */
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);
    }
}
