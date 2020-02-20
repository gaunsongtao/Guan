package com.bawei.day8;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter  extends BaseAdapter {

    Context context;
    List<NewsInfo.ResultsBean> result;

    public MyAdapter(Context context, List<NewsInfo.ResultsBean> result) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder  holder = null;
        int type = getItemViewType(position);
        if (convertView == null){
            convertView = View.inflate(context,R.layout.item1,null);
            holder = new ViewHolder();
            holder.iv = convertView.findViewById(R.id.iv);
            holder.tv_name = convertView.findViewById(R.id.tv_name);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsInfo.ResultsBean resultsBean = result.get(position);
        String url = resultsBean.getUrl();
        String createdAt = resultsBean.getCreatedAt();
        holder.tv_name.setText(createdAt);
        Glide.with(context).load(url).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(holder.iv);

        return convertView;
    }

    private class ViewHolder{
        private ImageView iv;
        private TextView tv_name;
    }
}
