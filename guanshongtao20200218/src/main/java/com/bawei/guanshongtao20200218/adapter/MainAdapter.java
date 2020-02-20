package com.bawei.guanshongtao20200218.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.guanshongtao20200218.fragment.FragmentFive;
import com.bawei.guanshongtao20200218.fragment.FragmentFour;
import com.bawei.guanshongtao20200218.fragment.FragmentOne;
import com.bawei.guanshongtao20200218.fragment.FragmentThree;
import com.bawei.guanshongtao20200218.fragment.FragmentTwo;

import java.util.ArrayList;

public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments =new ArrayList<>();
    private ArrayList<String> strings =new ArrayList<>();
    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
        fragments.add(new FragmentFour());
        fragments.add(new FragmentFive());
        strings.add("最新");
        strings.add("婆媳");
        strings.add("房产");
        strings.add("仲裁");
        strings.add("北京");

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
        return strings.get(position);
    }
}
