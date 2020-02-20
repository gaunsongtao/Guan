package com.bawei.guansongtao20200218.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.guansongtao20200218.R;
import com.bawei.guansongtao20200218.activity.bean.JsonBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaseAdap extends BaseAdapter {
    Context context;
    ArrayList<JsonBean.ListdataBean> list;

    public BaseAdap(Context context, ArrayList<JsonBean.ListdataBean> list) {
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        JsonBean.ListdataBean listdataBean = list.get(position);
        int type = listdataBean.getType();
        if (type==1){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if (convertView==null){
            int itemViewType = getItemViewType(position);
            switch (itemViewType){
                case 0:
                    convertView=View.inflate(context, R.layout.kong1,null);
                    break;
                case 1:
                    convertView=View.inflate(context, R.layout.kong2,null);
                    break;
            }
            holder = new ViewHolder();
            holder.tu=convertView.findViewById(R.id.iv);
            holder.zi1=convertView.findViewById(R.id.tv1);
            holder.zi2=convertView.findViewById(R.id.tv2);
            holder.zi3=convertView.findViewById(R.id.tv3);
            convertView.setTag(holder);

        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        JsonBean.ListdataBean listdataBean = list.get(position);
        String name = listdataBean.getName();
        String info = listdataBean.getInfo();
        String content = listdataBean.getContent();
        String avatar = listdataBean.getAvatar();
        holder.zi1.setText(name);
        holder.zi2.setText(info);
        holder.zi3.setText(content);
        Picasso.get().load(avatar).into(holder.tu);

        return convertView;
    }
    public class ViewHolder{
        ImageView tu;
        TextView zi1;
        TextView zi2;
        TextView zi3;
    }
}
