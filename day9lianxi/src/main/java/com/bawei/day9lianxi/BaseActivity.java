package com.bawei.day9lianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(getLayoutResID());
        //找控件
        initView();
        //获取数据
        getData();
    }





    //获取布局资源id
    protected abstract int getLayoutResID();
    //找控件
    protected abstract void initView();
    //获取数据
    protected abstract void getData();
}
