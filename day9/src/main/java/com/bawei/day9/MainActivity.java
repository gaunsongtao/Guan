package com.bawei.day9;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ImageView iv;

    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager vp;

    //获取布局资源id

    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    //找控件

    protected void initView() {
        vp = findViewById(R.id.vp);
        dl = findViewById(R.id.dl);
        iv = findViewById(R.id.iv);


    }

    //获取数据

    protected void getData() {

        //侧滑里面显示一张图片
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578386243402&di=69a9aa1cdff047832bb0d97f3e4412b1&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170204%2F20170204153149_3194e8a88a0216743bdf8b8b53c3ad50_5.jpeg").into(iv);

        dl.setDrawerListener(new DrawerLayout.DrawerListener() {


            /**
             * 当抽屉被滑动的时候调用此方法
             * arg1 表示 滑动的幅度(0-1)
             */
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Log.i("xxx","当抽屉被滑动的时候调用此方法");

            }

            /**
             * 当一个抽屉被完全打开的时候被调用
             */
            @Override
            public void onDrawerOpened(@NonNull View view) {
                Log.i("xxx","当一个抽屉被完全打开的时候被调用");

            }

            //当一个抽屉完全关闭的时候调用此方法
            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.i("xxx","当一个抽屉完全关闭的时候调用此方法");

            }
            /**
             * 当抽屉滑动状态改变的时候被调用
             * 状态值是STATE_IDLE（闲置-0），STATE_DRAGGING（拖拽-1），STATE_SETTLING（固定-2）中之一。
             * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
             */


            @Override
            public void onDrawerStateChanged(int i) {

                Log.i("xxx","当一个抽屉完全关闭的时候调用此方法:"+i);


            }
        });


        //主界面显示ViewPager

        FragmentOne fragmentOne = new FragmentOne();
        fragmentList.add(fragmentOne);
        FragmentTwo fragmentTwo = new FragmentTwo();
        fragmentList.add(fragmentTwo);

        //创建适配器
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        //设置适配器
        vp.setAdapter(adapter);

    }



    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
