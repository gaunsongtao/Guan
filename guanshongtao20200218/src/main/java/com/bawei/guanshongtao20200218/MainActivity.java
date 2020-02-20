package com.bawei.guanshongtao20200218;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bawei.guanshongtao20200218.adapter.MainAdapter;
import com.bawei.guanshongtao20200218.basse.BaseActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity {


    private android.widget.ImageView toxiang;
    private android.support.design.widget.TabLayout tebHome;
    private android.support.v4.view.ViewPager vpHome;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        toxiang = findViewById(R.id.toxiang);
        tebHome = findViewById(R.id.tebHome);
        vpHome = findViewById(R.id.vpHome);
    }

    @Override
    protected void initLayout() {
        toxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void initData() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        vpHome.setAdapter(mainAdapter);
        tebHome.setupWithViewPager(vpHome);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==RESULT_OK){
            Uri uri = data.getData();
            //获取相册图片需要添加sd权限
           Glide.with(this).load(uri).into(toxiang);

        }
    }
}
