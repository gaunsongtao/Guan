package com.bawei.guanshongtao20200218.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.guanshongtao20200218.R;
import com.bawei.guanshongtao20200218.bean.Listdata;
import com.bawei.guanshongtao20200218.util.NetUtil;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Listdata> listdata;

    public MyAdapter(Context context, List<Listdata> list) {
        this.context = context;
        this.listdata = list;
    }

    //清除数据
    public void clearData(){
        listdata.clear();
    }

    public void addData(List<Listdata> listdata) {
        this.listdata.addAll(listdata);
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public int getItemViewType(int position) {
        Listdata listdata = this.listdata.get(position);
        int type = listdata.getType();
        if (type ==1){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (type==0){
            final ViewHolderFirst viewHolder;
            if (convertView == null) {
                viewHolder=new ViewHolderFirst();
                convertView = View.inflate(context, R.layout.item_one,null);
                viewHolder.ivHead=convertView.findViewById(R.id.images);
                viewHolder.tvName=convertView.findViewById(R.id.name);
                viewHolder.zhuanye = convertView.findViewById(R.id.zhuaye);
                viewHolder.biaoti = convertView.findViewById(R.id.biaoti);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolderFirst) convertView.getTag();
            }
            Listdata listData = this.listdata.get(0);
            String name = listData.getName();
            String info = listData.getInfo();
            String content = listData.getContent();
            String avatar = listData.getAvatar();
            viewHolder.tvName.setText(name);
            viewHolder.zhuanye.setText(info);
            viewHolder.biaoti.setText(content);
            NetUtil instance = NetUtil.getInstance();
            instance.doPoto(avatar,viewHolder.ivHead);
        }else if (type==1){
            final ViewHolderSecond viewHolder;
            if (convertView == null) {
                viewHolder=new ViewHolderSecond();
                convertView = View.inflate(context,R.layout.item_two,null);
                viewHolder.ivHead=convertView.findViewById(R.id.images);
                viewHolder.tvName=convertView.findViewById(R.id.name);
                viewHolder.zhuanye = convertView.findViewById(R.id.zhuaye);
                viewHolder.biaoti = convertView.findViewById(R.id.biaoti);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolderSecond) convertView.getTag();
            }
            Listdata listData = this.listdata.get(0);
            String name = listData.getName();
            String info = listData.getInfo();
            String content = listData.getContent();
            String avatar = listData.getAvatar();
            viewHolder.tvName.setText(name);
            viewHolder.zhuanye.setText(info);
            viewHolder.biaoti.setText(content);
            NetUtil instance = NetUtil.getInstance();
            instance.doPoto(avatar,viewHolder.ivHead);
        }
        return convertView;
    }

    class ViewHolderFirst {
        TextView tvName,zhuanye,biaoti;
        ImageView ivHead;
    }

    class ViewHolderSecond {
        TextView tvName,zhuanye,biaoti;
        ImageView ivHead;
    }
}
