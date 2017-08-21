package com.xu.appbaseui.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.xu.appbaseui.R;
import com.xu.appbaseui.loadingview.XuBaseUILoadingView;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseRecyclerView
 * @Title:
 * @Description: BaseRecyclerView
 * @Package com.xu.appbaseui.recyclerview
 * @date 2017/5/22 15:41
 */

public class XuBaseRecyclerView extends RelativeLayout {
    //recyclerView;
    private RecyclerView mRecyclerView;
    //swipeRefreshLayout
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //progressBar
    private XuBaseUILoadingView mProgressBar;

    public XuBaseRecyclerView(Context context) {
        this(context,null);
    }

    public XuBaseRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XuBaseRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.base_recycler_layout, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.base_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_swipe_refresh_widget);
        mProgressBar = (XuBaseUILoadingView) findViewById(R.id.base_loadingView);
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public SwipeRefreshLayout getmSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void setmSwipeRefreshLayout(SwipeRefreshLayout mSwipeRefreshLayout) {
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
    }

    public XuBaseUILoadingView getmProgressBar() {
        return mProgressBar;
    }

    public void setmProgressBar(XuBaseUILoadingView mProgressBar) {
        this.mProgressBar = mProgressBar;
    }
}
