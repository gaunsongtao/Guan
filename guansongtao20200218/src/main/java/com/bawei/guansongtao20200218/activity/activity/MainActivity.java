package com.bawei.guansongtao20200218.activity.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bawei.guansongtao20200218.R;
import com.bawei.guansongtao20200218.activity.base.BaseActivity;
import com.bawei.guansongtao20200218.activity.fragment.Fragment_faw;
import com.bawei.guansongtao20200218.activity.fragment.Fragment_four;
import com.bawei.guansongtao20200218.activity.fragment.Fragment_one;
import com.bawei.guansongtao20200218.activity.fragment.Fragment_three;
import com.bawei.guansongtao20200218.activity.fragment.Fragment_two;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private ImageView iv;
    private TabLayout tab;
    private ViewPager vp;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabs = new ArrayList<>();

    @Override
    protected int getLayId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getViewId() {
        //找控件
        iv = findViewById(R.id.iv);
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
    }

    @Override
    protected void initData() {
        //TabLayout
        tabs.add("最新");
        tabs.add("婆媳");
        tabs.add("房产");
        tabs.add("仲裁");
        tabs.add("北京");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));
        tab.addTab(tab.newTab().setText(tabs.get(4)));

        //Fragment
        Fragment_one fragment_one = new Fragment_one();
        Fragment_two fragment_two = new Fragment_two();
        Fragment_three fragment_three = new Fragment_three();
        Fragment_four fragment_four = new Fragment_four();
        Fragment_faw fragment_faw = new Fragment_faw();
        fragments.add(fragment_one);
        fragments.add(fragment_two);
        fragments.add(fragment_three);
        fragments.add(fragment_four);
        fragments.add(fragment_faw);

        FragmentPageAdap fragmentPageAdap = new FragmentPageAdap(getSupportFragmentManager());
        vp.setAdapter(fragmentPageAdap);
        //关联
        tab.setupWithViewPager(vp);
        //点击事件
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        Picasso.get().load(data1).into(iv);
    }

    public class FragmentPageAdap extends FragmentPagerAdapter {

        public FragmentPageAdap(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
