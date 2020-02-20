package com.bawei.day10lianxi.Base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.day10lianxi.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        setContentView(getLayoutResID());
        //找控件
        initView();
        //获取数据
        getData();
    }

    protected abstract int getLayoutResID();
    //找控件
    protected abstract void initView();
    //获取数据
    protected abstract void getData();
}
