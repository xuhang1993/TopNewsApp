package com.xu.appbaseui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.xu.appbaseui.R;
import com.xu.appbaseui.loadingview.XuBaseUILoadingView;
import com.xu.appbaseui.recyclerview.adapter.XuBaseUIRecyclerViewAdapter;

/**
 * Created by 16413 on 2017/5/22.
 */

public abstract class XuBaseListFragment extends XuBaseFragment{
    //recyclerView
    protected RecyclerView mRecyclerView;
    //swipeRefreshLayout
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    //progressBar
    protected XuBaseUILoadingView mLoadingView;
    //最后显示的itemposition
    private int mLastVisibleItem = 0;
    //XuBaseUIRecyclerViewAdapter
    protected XuBaseUIRecyclerViewAdapter mAdapter;
    //layoutManager
    protected RecyclerView.LayoutManager mLayoutManager;

    protected void initRecyclerView(View view, final XuBaseUIRecyclerViewAdapter adapter, final RecyclerView.LayoutManager layoutManager){
        mAdapter = adapter;
        mLayoutManager = layoutManager;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.base_recyclerview);
        //设置layoutManager
        mRecyclerView.setLayoutManager(layoutManager);
        //如果item高度固定的话,设置这个可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //添加横线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.base_swipe_refresh_widget);
        mLoadingView = (XuBaseUILoadingView) view.findViewById(R.id.base_loadingView);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.cyan,R.color.blue,R.color.red);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataList();
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState ==RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItem + 1 == mAdapter.getItemCount()) {
                    loadMoreDataList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLayoutManager instanceof LinearLayoutManager){
                    mLastVisibleItem = ((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();
                }else if (mLayoutManager instanceof GridLayoutManager){
                    mLastVisibleItem = ((GridLayoutManager)mLayoutManager).findLastVisibleItemPosition();
                }else if (mLayoutManager instanceof StaggeredGridLayoutManager){
                    //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                    //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                    int[] lastPositions = new int[((StaggeredGridLayoutManager) mLayoutManager).getSpanCount()];
                    ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(lastPositions);
                    mLastVisibleItem = findMax(lastPositions);
                }
            }
        });

        isCanLoadData();

    }

    //找到数组中的最大值
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    protected abstract void loadDataList();

    protected abstract void loadMoreDataList();

    @Override
    protected void isCanLoadData() {
        if (mIsInit && mIsLoad){
            return;
        }
        loadDataList();
    }
}
