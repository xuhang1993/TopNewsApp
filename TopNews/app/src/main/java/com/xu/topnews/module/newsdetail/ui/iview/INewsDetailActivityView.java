package com.xu.topnews.module.newsdetail.ui.iview;

/**
 * Created by 16413 on 2017/5/25.
 */

public interface INewsDetailActivityView {

    void showProgress();

    void hideProgress();

    void loadSuccess(Object object);

    void loadFailer(String string);

}
