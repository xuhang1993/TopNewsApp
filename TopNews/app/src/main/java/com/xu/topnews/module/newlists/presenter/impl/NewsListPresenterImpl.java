package com.xu.topnews.module.newlists.presenter.impl;

import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.newlists.model.imodel.INewsListModel;
import com.xu.topnews.module.newlists.model.impl.NewsListModelImpl;
import com.xu.topnews.module.newlists.presenter.ipresenter.INewsListPersenter;
import com.xu.topnews.module.newlists.ui.iview.INewsListFragmentView;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: NewsListPresenterImpl
 * @Title:
 * @Description: 连接view与model 实现类
 * @Package com.xu.topnews.module.newlists.presenter.impl
 * @date 2017/5/22 15:27
 */

public class NewsListPresenterImpl implements INewsListPersenter{

    private INewsListFragmentView mListView;
    private INewsListModel mListModel;

    public NewsListPresenterImpl(INewsListFragmentView listView) {
        mListView = listView;
        mListModel = new NewsListModelImpl();
    }

    @Override
    public void loadNewsList(int index, int rowsCount) {
        mListView.showProgress();
        mListModel.loadDataList(index, rowsCount, new OnLoadDataListener() {
            @Override
            public void loadSuccess(Object o) {
                mListView.loadDataSuccess(o);
                mListView.hideProgress();
            }

            @Override
            public void loadFailure(String errorMessage) {
                mListView.loadDataFailer(errorMessage);
                mListView.hideProgress();
            }
        });

    }
}
