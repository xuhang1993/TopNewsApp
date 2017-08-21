package com.xu.topnews.module.newsdetail.presenter.impl;

import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.newsdetail.model.imodel.INewsDetailModel;
import com.xu.topnews.module.newsdetail.model.impl.NewsDetailModelImpl;
import com.xu.topnews.module.newsdetail.presenter.ipresenter.INewsDetailPresenter;
import com.xu.topnews.module.newsdetail.ui.iview.INewsDetailActivityView;

/**
 * Created by 16413 on 2017/6/5.
 */

public class NewsDetailPresenterImpl implements INewsDetailPresenter{

    private INewsDetailModel detailModel;

    private INewsDetailActivityView activityView;

    public NewsDetailPresenterImpl(INewsDetailActivityView activityView) {
        this.activityView = activityView;
        detailModel = new NewsDetailModelImpl();
    }

    @Override
    public void loadNewsDetail(String postId) {
        detailModel.loadNewsDetailData(postId, new OnLoadDataListener() {
            @Override
            public void loadSuccess(Object o) {
                activityView.loadSuccess(o);
            }

            @Override
            public void loadFailure(String errorMessage) {
                activityView.loadFailer(errorMessage);
            }
        });
    }
}
