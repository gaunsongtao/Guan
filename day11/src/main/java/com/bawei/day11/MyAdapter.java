package com.bawei.day11;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<UserInfo> list;

    public MyAdapter(Context context, ArrayList<UserInfo> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明ViewHolder
        ViewHolder holder = null;
        if (convertView==null){
            //加载布局
            convertView = View.inflate(context,R.layout.layout,null);
            //创建ViewHolder
            holder = new ViewHolder();
            //找控件
            holder.iv = convertView.findViewById(R.id.iv);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            //设置
            convertView.setTag(holder);
        }else {
            //复用
            holder = (ViewHolder) convertView.getTag();
        }

        //获取数据

        UserInfo userInfo = list.get(position);
        int imageId = userInfo.getImageId();
        String name = userInfo.getName();
        //赋值
        holder.iv.setImageResource(imageId);
        holder.tv_name.setText(name);
        return convertView;
    }

    //定义
    private class ViewHolder {
     private ImageView iv;
     private TextView tv_name;
    }
}
