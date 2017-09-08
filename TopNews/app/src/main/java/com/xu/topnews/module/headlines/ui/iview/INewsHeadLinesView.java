package com.xu.topnews.module.headlines.ui.iview;

/**
 * Created by 16413 on 2017/9/6.
 */

public interface INewsHeadLinesView {
    /**
     * 加载成功
     */
    void loadDataSuccess(Object o);

    /**
     * 加载失败
     */
    void loadDataFail(String error);


}
