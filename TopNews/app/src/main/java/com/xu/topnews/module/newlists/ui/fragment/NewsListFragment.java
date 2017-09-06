package com.xu.topnews.module.newlists.ui.fragment;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.appbaseui.fragment.XuBaseListFragment;
import com.xu.appbaseui.recyclerview.adapter.XuBaseUIRecyclerViewAdapter;
import com.xu.appbaseui.recyclerview.listener.OnItemClickListener;
import com.xu.appbaseui.recyclerview.listener.OnLoadMoreDataListener;
import com.xu.appbaseui.recyclerview.viewholder.XuBaseUIViewHolder;
import com.xu.appframwork.glide.XuBaseGlideUtils;
import com.xu.appinterface.webapi.apimodel.topnews.XuTopNewsApiListModel;
import com.xu.appinterface.webapi.apimodel.topnews.XuTopNewsApiModel;
import com.xu.topnews.R;
import com.xu.topnews.constant.XuConstant;
import com.xu.topnews.module.newlists.presenter.impl.NewsListPresenterImpl;
import com.xu.topnews.module.newlists.ui.iview.INewsListFragmentView;
import com.xu.topnews.module.newsdetail.ui.activity.NewsDetailActivity;

import java.util.List;

import butterknife.BindString;

import static com.xu.topnews.R.id.news_thum_imageview;


/**
 * @author xuhang
 * @version V1.0
 * @ClassName: NewsListFragment
 * @Title:
 * @Description: 新闻列表
 * @Package com.xu.topnews.module.newlists.ui.fragment
 * @date 2017/8/21 16:57
 */
public class NewsListFragment extends XuBaseListFragment implements INewsListFragmentView{

    private XuTopNewsApiModel mApiModel;
    private List<XuTopNewsApiListModel> mTopNewsLists;
    private NewsListPresenterImpl mPersenter;
    private int mIndex;

    @BindString(R.string.app_news_load_all)
    String mNewsLoadAll;

    @BindString(R.string.app_news_comments)
    String mNewsComments;

    @Override
    protected void iniView(View mView) {
        mIsInit = true;
        mPersenter = new NewsListPresenterImpl(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        initRecyclerView(mView,new MyAdapter(mTopNewsLists,getActivity(),linearLayoutManager),linearLayoutManager);
        mAdapter.setOnLoadmoreDataListener(new OnLoadMoreDataListener() {
            @Override
            public void loadMoreData() {
                loadMoreDataList();
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClickLinstener(View view, int positon) {
                XuTopNewsApiListModel apiListModel =  mTopNewsLists.get(positon);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("postId", apiListModel.postid);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "thum_image").toBundle());
                }
            }
        });
    }

    @Override
    protected void loadDataList() {
        mIsLoad = true;
        mIndex = 0;
        mPersenter.loadNewsList(mIndex,XuConstant.ROWS_COUNT);
    }

    @Override
    protected void loadMoreDataList() {
        mIndex += XuConstant.PAGEINDEX_SIZE;
        mPersenter.loadNewsList(mIndex,XuConstant.ROWS_COUNT);
    }

    @Override
    protected int getmContentId() {
        return R.layout.fragment_news_list;
    }


    @Override
    public void loadDataSuccess(Object o) {
        mSwipeRefreshLayout.setRefreshing(false);
        mApiModel = (XuTopNewsApiModel) o;
        if (mApiModel != null){
            List<XuTopNewsApiListModel> newsLists = mApiModel.T1348647909107;
            if (newsLists != null && newsLists.size() > 0){
                if (mIndex == 0){
                    mTopNewsLists = newsLists;
                }else {
                    mTopNewsLists.addAll(newsLists);
                }
            }else {
                //// TODO: 2017/5/25 加载完毕
                Snackbar.make(mView,mNewsLoadAll,Snackbar.LENGTH_LONG).show();
            }
        }
        mAdapter.loadSuccess(mTopNewsLists);
    }

    @Override
    public void showProgress() {
        mLoadingView.play();
    }

    @Override
    public void hideProgress() {
       mLoadingView.stop();
    }

    @Override
    public void loadDataFailer(String message) {
        mIndex -= XuConstant.PAGEINDEX_SIZE;
        mIndex =  (mIndex <= 0) ? 0 : mIndex;
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.loadFailer();
        Snackbar.make(mView,message,Snackbar.LENGTH_LONG).show();
    }

    public class MyAdapter extends XuBaseUIRecyclerViewAdapter<XuTopNewsApiListModel>{

        public MyAdapter(List<XuTopNewsApiListModel> dataLists, Context context, RecyclerView.LayoutManager layoutManager) {
            super(dataLists, context, layoutManager);
        }

        @Override
        public int getItemLayoutId() {
            return R.layout.item_topnews_list_layot;
        }

        @Override
        public void bindView(XuBaseUIViewHolder holder, int position, XuTopNewsApiListModel item) {
            TextView titleTextView = holder.getTextView(R.id.news_title_textview);
            titleTextView.setText(item.title);
            holder.getTextView(R.id.news_resource_textview).setText(item.source);
            holder.getTextView(R.id.news_reply_textview).setText(item.replyCount + mNewsComments);
            ImageView imageView = holder.getImageView(news_thum_imageview);
            if (item.imgextra == null || item.imgextra.size() == 0){
               //图片加载
                XuBaseGlideUtils.loadDefaultImageView(getActivity(),item.imgsrc,R.drawable.default_image_resource,R.drawable.default_image_load_fail,imageView);
            }else {
                XuBaseGlideUtils.loadDefaultImageView(getActivity(),item.imgextra.get(0).imgsrc,R.drawable.default_image_resource,R.drawable.default_image_load_fail,imageView);
            }
        }

        @Override
        public void refreshData(List<XuTopNewsApiListModel> dataLists) {
            super.mDataLists = dataLists;
            notifyDataSetChanged();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
