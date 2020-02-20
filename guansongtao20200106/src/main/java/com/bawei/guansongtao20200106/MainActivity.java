package com.bawei.guansongtao20200106;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int page = 1;
    List<NewsInfo.ResultBean> results = new ArrayList<>();
    private XListView xlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xlv = findViewById(R.id.xlv);

        boolean netWorkActive = NetUtils.getInstance().isWorkAction(this);
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
        }

    }


    public  void  getServerJson(int page){
        String jsonPath = "http://blog.zhaoliang5156.cn/baweiapi/news?page=1&pageSize=15";
        Log.i("xxx",jsonPath);
        Log.i("xxx",page+"");
        NetUtils.getInstance().getJson(jsonPath, new NetUtils.ICallBack() {
            @Override
            public void onSucess(String json) {
                Log.i("xxx",json);
                Gson gson = new Gson();
                NewsInfo newsInfo = gson.fromJson(json,NewsInfo.class);
                List<NewsInfo.ResultBean> results = newsInfo.getResult();

                results.addAll(results);

                MyAdapter myAdapter = new MyAdapter(MainActivity.this,results);

                xlv.setAdapter(myAdapter);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
