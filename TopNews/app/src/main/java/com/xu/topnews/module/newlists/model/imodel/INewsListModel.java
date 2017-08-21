package com.xu.topnews.module.newlists.model.imodel;

import com.xu.topnews.module.OnLoadDataListener;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: INewsListModel
 * @Title:
 * @Description: 获取新闻列表ModelInterface
 * @Package com.xu.topnews.module.newlists.model.imodel
 * @date 2017/5/22 15:25
 */

public interface INewsListModel {

    void loadDataList(final int index, final int rowsCount, final OnLoadDataListener listener);

}
