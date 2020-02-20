package com.bawei.moni6.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.moni6.R;
import com.bawei.moni6.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }
}
