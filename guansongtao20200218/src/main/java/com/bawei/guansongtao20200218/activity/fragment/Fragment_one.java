package com.bawei.guansongtao20200218.activity.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bawei.guansongtao20200218.R;
import com.bawei.guansongtao20200218.activity.activity.Main2Activity;
import com.bawei.guansongtao20200218.activity.adapter.BaseAdap;
import com.bawei.guansongtao20200218.activity.bean.JsonBean;
import com.bawei.guansongtao20200218.activity.utils.Utils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_one extends Fragment {
    private PullToRefreshListView pull;
    ArrayList<JsonBean.ListdataBean> list = new ArrayList<>();
    int i=1;

    protected int getLayId() {
        return R.layout.item1;
    }


    protected void getViewId(View view) {
        pull = view.findViewById(R.id.pull);
    }


    protected void initData() {
        getDate("");
        //设置模式
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        //监听器
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                getDate("2");
                pull.onRefreshComplete();
                i=1;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                i++;
                getDate(""+i);
                Log.i("xxx",""+i);
                pull.onRefreshComplete();
            }
        });
        //单击事件
        pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JsonBean.ListdataBean listdataBean = list.get(position);
                String url = listdataBean.getUrl();
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }
    //获取json
    public void getDate(String page){
        String path="http://blog.zhaoliang5156.cn/api/news/lawyer"+page+".json";
        Log.i("xxx","执行");
        Boolean wifi = Utils.getInstance().isWifi(getActivity());
        if (wifi){
            Utils.getInstance().getJson(path, new Utils.Inter() {
                @Override
                public void Ok(String json) {
                    Log.i("xxx","json");
                    Gson gson = new Gson();
                    JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
                    List<JsonBean.ListdataBean> listdata = jsonBean.getListdata();
                    list.addAll(listdata);
                    BaseAdap baseAdap = new BaseAdap(getActivity(), list);
                    pull.setAdapter(baseAdap);
                }
            });
        }else {
            Log.i("xxx","无网络");
        }
    }

}
