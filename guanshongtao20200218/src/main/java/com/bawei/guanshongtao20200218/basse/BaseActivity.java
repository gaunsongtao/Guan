package com.bawei.guanshongtao20200218.basse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = initLayoutId();
        setContentView(layout);
        initView();
        initLayout();
        initData();
    }
    //获取id
    protected abstract int initLayoutId();
    //初始化对象
    protected abstract void initView();
    //监听控件
    protected abstract void initLayout();
    //内容
    protected abstract void initData();
}
