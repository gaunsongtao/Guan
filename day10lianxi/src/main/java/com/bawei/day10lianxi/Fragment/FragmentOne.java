package com.bawei.day10lianxi.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bawei.day10lianxi.Base.BaseFragment;
import com.bawei.day10lianxi.Bean.BannerInfo;
import com.bawei.day10lianxi.R;
import com.bawei.day10lianxi.utils.NetUtils;
import com.google.gson.Gson;

import java.util.List;

public class FragmentOne extends BaseFragment {

    private String path = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=banner";
    private XBanner xbanner;

    //获取布局资源
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_one;
    }

    //找控件
    @Override
    protected void initView(View view) {
//        xbanner = view.findViewById(R.id.xbanner);

    }


    //获取数据
    @Override
    protected void getData() {

        boolean netWorkActive = NetUtils.getInstance().isNetWorkActive(getActivity());
        //判断网络
        if (netWorkActive) {

            NetUtils.getInstance().getJson(path, new NetUtils.ICallBack() {
                @Override
                public void onSucess(String json) {
                    Log.i("xxx", json);
                    //解析
                    Gson gson = new Gson();
                    BannerInfo bannerInfo = gson.fromJson(json, BannerInfo.class);
                    final List<BannerInfo.BannerBean> banners = bannerInfo.getBanner();

                    //设置数据源
//                    xbanner.setBannerData(banners);

                    //加载图片
//                    xbanner.loadImage(new XBanner.XBannerAdapter() {
//                        @Override
//                        public void loadBanner(XBanner banner, Object model, View view, int position) {
//                            //加载对应的轮播图片
//                            BannerInfo.BannerBean bannerBean = banners.get(position);
//                            String image_url = bannerBean.getImage_url();
//                            //异步加载图片
//                            NetUtils.getInstance().getPic(image_url,(ImageView)view);
//
//                        }
//                    });

                }

                @Override
                public void onError(String msg) {
                    Log.i("xxx",msg);

                }
            });
        }


    }

    private class XBanner {
    }
}
