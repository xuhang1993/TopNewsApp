package com.xu.topnews.module.newlists.model.impl;

import com.xu.appinterface.callback.XuBaseApiCallBack;
import com.xu.appinterface.webapi.api.XuBaseWebApi;
import com.xu.appinterface.webapi.apimodel.topnews.XuTopNewsApiModel;
import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.newlists.model.imodel.INewsListModel;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: NewsListModelImpl
 * @Title:
 * @Description: 获取新闻列表实现类
 * @Package com.xu.topnews.module.newlists.model.impl
 * @date 2017/5/22 15:26
 */

public class NewsListModelImpl implements INewsListModel{

    @Override
    public void loadDataList(final int index, final int rowsCount, final OnLoadDataListener listener) {
        XuBaseWebApi.getInstance().getTopNews("headline", "T1348647909107", index, rowsCount, new XuBaseApiCallBack<XuTopNewsApiModel>() {
            @Override
            public void loadSuccess(XuTopNewsApiModel data) {
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
