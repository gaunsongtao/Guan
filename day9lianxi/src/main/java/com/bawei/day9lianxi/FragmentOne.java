package com.bawei.day9lianxi;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FragmentOne extends BaseFragment{
    private ImageView iv_one;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView(View view) {

        iv_one = view.findViewById(R.id.iv_one);
    }

    @Override
    protected void getData() {

        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578386243402&di=69a9aa1cdff047832bb0d97f3e4412b1&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170204%2F20170204153149_3194e8a88a0216743bdf8b8b53c3ad50_5.jpeg").into(iv_one);


    }
}
