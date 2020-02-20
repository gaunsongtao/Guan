package com.bawei.guanshongtao20200218.basse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    public View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout = initLayoutId();
        rootView = View.inflate(getContext(),layout,null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
