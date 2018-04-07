package com.imooc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.imooc.R;

/**
 * 作者：张玉辉 on 2018/4/7 15:24.
 */

public class LoadListView extends ListView implements AbsListView.OnScrollListener{
    /** 底部布局 **/
    private View footerView;
    /** 总item数**/
    private int totalItemCount;
    /** 最后一个可见的item **/
    private int lastVisibleItem;
    /** 正在加载 **/
    private boolean isLoading;
    /** 数据回调 **/
    private ILoadListener mLoadListener;

    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public void loadComplete(){
        if(isLoading){
            isLoading = false;
            footerView.findViewById(R.id.ll_loading).setVisibility(GONE);
        }
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        footerView = inflater.inflate(R.layout.footer_layout,null);
        footerView.findViewById(R.id.ll_loading).setVisibility(GONE);
        isLoading = false;
        addFooterView(footerView);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (lastVisibleItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
            if(!isLoading) {
                isLoading = true;
                footerView.findViewById(R.id.ll_loading).setVisibility(VISIBLE);
                //加载更多数据
                if(this.mLoadListener !=null){
                    mLoadListener.onLoad();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem+visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void setLoadListener(ILoadListener loadListener){
        this.mLoadListener = loadListener;
    }
    /** 加载更多数据的回调接口 **/
    public interface ILoadListener{
        void onLoad();
    }
}
