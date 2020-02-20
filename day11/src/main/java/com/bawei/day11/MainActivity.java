package com.bawei.day11;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImageView> imageViewList = new ArrayList<>();




    private ArrayList<UserInfo> userInfoList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1，找控件
        ViewPager vp = findViewById(R.id.vp);
        final RadioGroup rg = findViewById(R.id.rg);
        Button bt1 = findViewById(R.id.rb1);
        Button bt2 = findViewById(R.id.rb2);




        //6，在activity中默认动态创建一个fragment
        //获取Fragment管理器
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction =
                fragmentManager.beginTransaction();
        FragmentOne fragmentOne = new FragmentOne();
        //控制Fragment
//        transaction.replace(R.id.f1, fragmentOne);
        //添加回退栈
        transaction.addToBackStack(null);
        //出栈
        fragmentManager.popBackStack();
        //提交事务
        transaction.commit();

        //7，遍历数据源存放到集合中


        //使用setArguments传值
        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("list", userInfoList);
        fragmentOne.setArguments(bundle);

        //8,点击按钮动态创建fragment

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取Fragment管理器
                FragmentManager fragmentManager =
                        getSupportFragmentManager();
                //开启事务
                FragmentTransaction transaction =
                        fragmentManager.beginTransaction();
                FragmentOne fragmentOne = new FragmentOne();
                //控制Fragment
//                transaction.replace(R.id.fl, fragmentOne);
                //提交事务
                transaction.commit();
                //使用setArguments传值
                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("list", userInfoList);
                fragmentOne.setArguments(bundle);

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取Fragment管理器
                FragmentManager fragmentManager =
                        getSupportFragmentManager();
                //开启事务
                FragmentTransaction transaction =
                        fragmentManager.beginTransaction();
                FragmentTwo fragmentTwo = new FragmentTwo();
                //控制Fragment
//                transaction.replace(R.id.fl, fragmentTwo);
                //提交事务
                transaction.commit();
            }
        });


    }

    //定义适配器
    private class MyViewPagerAdapter extends PagerAdapter {

        //返回数量

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        //关联
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        //加载图片
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            ImageView imageView = imageViewList.get(position);
            container.addView(imageView);
            return imageView;
        }

        //移除图片
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
