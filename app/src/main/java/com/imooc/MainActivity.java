package com.imooc;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.imooc.adapter.LoadAdpater;
import com.imooc.entity.ApkEntity;
import com.imooc.view.LoadListView;

import java.util.ArrayList;

/**
 * 作者：张玉辉 on 2018/4/7 15:47.
 */

public class MainActivity extends AppCompatActivity implements LoadListView.ILoadListener{
    private ArrayList<ApkEntity> mApkEntityList = new ArrayList<ApkEntity>();
    private LoadListView mLoadListView;
    private LoadAdpater mLoadAdpater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        showListView(mApkEntityList);
    }

    private void initData(){
        for(int i=0;i<10;i++){
            ApkEntity apkEntity = new ApkEntity();
            apkEntity.setName("hn "+i);
            apkEntity.setDes("yy hn "+i);
            apkEntity.setInfo("mt yy hn "+i);
            mApkEntityList.add(apkEntity);
        }
    }

    private void showListView(ArrayList<ApkEntity> apkEntityList){
        if(mLoadListView == null){
            mLoadListView = (LoadListView)findViewById(R.id.load_listview);
            mLoadAdpater = new LoadAdpater(this, mApkEntityList);
            mLoadListView.setAdapter(mLoadAdpater);
        }else{
            mLoadAdpater.onDataChange(mApkEntityList);
        }
    }

    private void loadMoreData(){
        for(int i=0;i<2;i++){
            ApkEntity apkEntity = new ApkEntity();
            apkEntity.setName("hn "+(10+i));
            apkEntity.setDes("yy hn "+(10+i));
            apkEntity.setInfo("mt yy hn "+(10+i));
            mApkEntityList.add(apkEntity);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMoreData();
                showListView(mApkEntityList);
                mLoadListView.loadComplete();
            }
        },2000);
    }
}
