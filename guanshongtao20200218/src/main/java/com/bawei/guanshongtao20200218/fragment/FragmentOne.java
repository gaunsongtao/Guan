package com.bawei.guanshongtao20200218.fragment;

import android.support.v4.app.Fragment;

import com.bawei.guanshongtao20200218.R;
import com.bawei.guanshongtao20200218.adapter.MyAdapter;
import com.bawei.guanshongtao20200218.basse.BaseFragment;
import com.bawei.guanshongtao20200218.bean.DataBean;
import com.bawei.guanshongtao20200218.bean.Listdata;
import com.bawei.guanshongtao20200218.util.NetUtil;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.List;

public class FragmentOne extends BaseFragment {
    private com.qy.xlistview.XListView xlistview;
    private MyAdapter adapter;
    private int page = 1;
    @Override
    protected int initLayoutId() {
        return R.layout.layout_one;
    }

    @Override
    protected void initView() {
        xlistview = rootView.findViewById(R.id.xlistview);

        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                initData();
                xlistview.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
                xlistview.stopLoadMore();

            }
        });
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initData() {
        NetUtil instance = NetUtil.getInstance();
        String path = "";
        if (page==1){
            path = "http://blog.zhaoliang5156.cn/api/news/lawyer.json";
        }
        if (page==2){
            path = "http://blog.zhaoliang5156.cn/api/news/lawye2.json";
        }
        if (page==3){
            path = "http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
        }

        instance.doGet(path, new NetUtil.CallBack() {
            @Override
            public void onSuccess(String json) {
                Gson gson = new Gson();
                DataBean dataBean = gson.fromJson(json, DataBean.class);
                List<Listdata> listdata = dataBean.getListdata();
                if (adapter==null){
                    adapter = new MyAdapter(getActivity(),listdata);
                    xlistview.setAdapter(adapter);
                }else {
                    if (page==1){
                        adapter.clearData();
                    }
                    adapter.addData(listdata);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFeature(String msg) {

            }
        });


    }
}
