package com.bawei.day9lianxi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view = View.inflate(getActivity(), getLayoutResID(), null);

        //找控件
        initView(view);

        //获取数据
        getData();

        return view;
    }



    //获取布局资源id
    protected abstract int getLayoutResID();

    //找控件
    protected abstract void initView(View view);

    //获取数据
    protected abstract void getData();

}
