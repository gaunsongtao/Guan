package com.bawei.zhoukao2lianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int page = 1;

    private XListView xlv;
    List<NewsInfo.ResultBean> results = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xlv = findViewById(R.id.xlv);

        boolean netWorkActive = NetUtils.getInstance().isNetWorkActive(this);

        if (netWorkActive){
            getServerJson(page);
            xlv.setPullRefreshEnable(true);
            xlv.setPullLoadEnable(true);
            xlv.setXListViewListener(new XListView.IXListViewListener() {
                @Override
                public void onRefresh() {
                    results.clear();
                    page = 1;
                    getServerJson(page);
                    xlv.stopRefresh();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    getServerJson(page);
                    xlv.stopLoadMore();
                }
            });

        }else {
            Toast.makeText(this,"无网络",Toast.LENGTH_SHORT).show();
        }
    }

    public void  getServerJson(int page){
        String jsonpath = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?page="+ page +"&count=3";
        Log.i("xxx",jsonpath);
        Log.i("xxx",page+"");

        NetUtils.getInstance().getJson(jsonpath, new NetUtils.ICallBack() {
            @Override
            public void onSucess(String json) {

                Gson gson = new Gson();
                NewsInfo newsInfo = gson.fromJson(json,NewsInfo.class);
                List<NewsInfo.ResultBean> result = newsInfo.getResult();
                results.addAll(result);
                Log.i("XXX",json);
                MyAdapter myAdapter = new MyAdapter(MainActivity.this,result);
                xlv.setAdapter(myAdapter);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
