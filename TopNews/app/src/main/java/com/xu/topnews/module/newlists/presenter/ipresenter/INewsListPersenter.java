package com.xu.topnews.module.newlists.presenter.ipresenter;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: INewsListPersenter
 * @Title:
 * @Description: 连接view与model persenter interface
 * @Package com.xu.topnews.module.newlists.presenter.ipresenter
 * @date 2017/5/22 15:26
 */

public interface INewsListPersenter {

    void loadNewsList(final int index, final int rowsCount);

}
