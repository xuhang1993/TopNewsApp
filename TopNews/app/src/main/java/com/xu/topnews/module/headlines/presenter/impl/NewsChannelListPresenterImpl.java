package com.xu.topnews.module.headlines.presenter.impl;

import com.xu.topnews.module.OnLoadDataListener;
import com.xu.topnews.module.headlines.model.imodel.INewsHeadlineModel;
import com.xu.topnews.module.headlines.model.impl.NewsHeadlineModelImpl;
import com.xu.topnews.module.headlines.presenter.ipresenter.INewsChannelListPresenter;
import com.xu.topnews.module.headlines.ui.iview.INewsHeadLinesView;

/**
 * Created by 16413 on 2017/9/6.
 */

public class NewsChannelListPresenterImpl implements INewsChannelListPresenter{

    private INewsHeadLinesView mHeadLiesView;
    private final INewsHeadlineModel mModel;

    public NewsChannelListPresenterImpl(INewsHeadLinesView mHeadLiesView) {
        this.mHeadLiesView = mHeadLiesView;
        mModel = new NewsHeadlineModelImpl();
    }

    @Override
    public void loadNewsChannel() {
        mModel.loadChannelList(new OnLoadDataListener() {
            @Override
            public void loadSuccess(Object o) {
                mHeadLiesView.loadDataSuccess(o);
            }

            @Override
            public void loadFailure(String errorMessage) {
                mHeadLiesView.loadDataFail(errorMessage);
            }
        });
    }
}
