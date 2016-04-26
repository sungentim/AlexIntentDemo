package com.alex.alexintentdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alex.alexintentdemo.R;
import com.alex.alexintentdemo.ui.base.BaseActivity;

/**
 * Created by lizetong on 16/4/25.
 */
public class Test2Activity extends BaseActivity {

    private String name = "";
    private boolean flag = false;
    private int code = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2_layout);

        init();
    }

    private void init() {
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            flag = intent.getBooleanExtra("flag", false);
            code = intent.getIntExtra("code", 0);
        }

        String text = "name:" + name + "  flag:" + flag + "  code:" + code;
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
