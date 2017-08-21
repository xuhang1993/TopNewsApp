package com.xu.topnews.module.newsdetail.model.impl;

import com.xu.appinterface.callback.XuBaseApiCallBack;
import com.xu.appinterface.webapi.api.XuBaseWebApi;
import com.xu.appinterface.webapi.apimodel.topnewsdetail.XuTopNewsDetailModel;
import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.newsdetail.model.imodel.INewsDetailModel;

/**
 * Created by 16413 on 2017/6/5.
 */

public class NewsDetailModelImpl implements INewsDetailModel {
    @Override
    public void loadNewsDetailData(String postId, final OnLoadDataListener listener) {
        XuBaseWebApi.getInstance().getNewsDetail(postId, new XuBaseApiCallBack<XuTopNewsDetailModel>() {
            @Override
            public void loadSuccess(XuTopNewsDetailModel data) {
                listener.loadSuccess(data);
            }

            @Override
            public void loadFailure(String string) {
                listener.loadFailure(string);
            }

            @Override
            public void loadBefore() {

            }

            @Override
            public void loadComplete() {

            }
        });
    }
}
