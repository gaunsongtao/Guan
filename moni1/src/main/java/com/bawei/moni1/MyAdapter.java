package com.bawei.moni1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context context;
    List<NewsInfo.ResultBean> result;

    public MyAdapter(Context context, List<NewsInfo.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
       NewsInfo.ResultBean resultBean = result.get(position);
        int type = resultBean.getType();
        if (type==1){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView==null){
            if (type==0){
                convertView = View.inflate(context,R.layout.item1,null);
            }else {
                convertView = View.inflate(context,R.layout.item2,null);
            }
            holder = new ViewHolder();
            holder.iv = convertView.findViewById(R.id.iv);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsInfo.ResultBean resultBean = result.get(position);
        String imageUrl = resultBean.getImageUrl();
        String name = resultBean.getName();
        holder.tv_name .setText(name);
        Glide.with(context).load(imageUrl).error(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.iv);
        return convertView;
    }

    private class ViewHolder{

        private ImageView iv;
        private TextView tv_name;

    }
}
