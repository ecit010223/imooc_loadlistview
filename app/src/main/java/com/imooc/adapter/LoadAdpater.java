package com.imooc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.imooc.R;
import com.imooc.entity.ApkEntity;
import com.imooc.view.LoadListView;

import java.util.ArrayList;

/**
 * 作者：张玉辉 on 2018/4/7 16:06.
 */

public class LoadAdpater extends BaseAdapter {
    private ArrayList<ApkEntity> apkList;
    private LayoutInflater mInflater;

    public LoadAdpater(Context context,ArrayList<ApkEntity> apkList){
        this.mInflater = LayoutInflater.from(context);
        this.apkList = apkList;
    }

    public void onDataChange(ArrayList<ApkEntity> apkList){
        this.apkList = apkList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return apkList.size();
    }

    @Override
    public Object getItem(int position) {
        return apkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApkEntity entity = apkList.get(position);
        ViewHolder viewHolder;
        if(convertView ==null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_load_listview,null);
            viewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.item3_apkname);
            viewHolder.tvDes = (TextView) convertView
                    .findViewById(R.id.item3_apkdes);
            viewHolder.tvInfo = (TextView) convertView
                    .findViewById(R.id.item3_apkinfo);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(entity.getName());
        viewHolder.tvDes.setText(entity.getDes());
        viewHolder.tvInfo.setText(entity.getInfo());
        return convertView;
    }

    class ViewHolder{
        TextView tvName;
        TextView tvDes;
        TextView tvInfo;
    }
}
