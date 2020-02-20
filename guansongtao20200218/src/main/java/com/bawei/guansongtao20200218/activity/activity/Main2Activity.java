package com.bawei.guansongtao20200218.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bawei.guansongtao20200218.R;
import com.bawei.guansongtao20200218.activity.base.BaseActivity;

public class Main2Activity extends BaseActivity {


    private WebView web;

    @Override
    protected int getLayId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void getViewId() {
        web = findViewById(R.id.web);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.loadUrl(url);
    }
}
