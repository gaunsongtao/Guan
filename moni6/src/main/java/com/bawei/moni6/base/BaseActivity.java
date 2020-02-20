package com.bawei.moni6.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResID());
        //初始控件
        initView();
        //初始数据
        initData();

    }
    //返回数据
    protected abstract void initData();
    //返回控件
    protected abstract void initView();
    //返回布局资源ID
    protected abstract int getLayoutResID();
}
