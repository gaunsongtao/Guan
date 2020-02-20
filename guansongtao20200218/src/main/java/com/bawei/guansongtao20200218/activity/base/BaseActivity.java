package com.bawei.guansongtao20200218.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayId());
        getViewId();
        initData();
    }
    protected abstract int getLayId();
    protected abstract void getViewId();
    protected abstract void initData();




}