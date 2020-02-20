package com.bawei.guanshongtao20200218.fragment;

import android.support.v4.app.Fragment;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.bawei.guanshongtao20200218.R;
import com.bawei.guanshongtao20200218.basse.BaseFragment;

public class FragmentTwo extends BaseFragment {
    private android.webkit.WebView webview;

    @Override
    protected int initLayoutId() {
        return R.layout.layout_two;
    }

    @Override
    protected void initView() {

        webview = rootView.findViewById(R.id.webview);
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initData() {
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.loadUrl("https://www.sina.com.cn/");
    }
}
