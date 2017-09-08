package com.xu.topnews.module.headlines.model.imodel;

import com.xu.topnews.module.OnLoadDataListener;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: INewsHeadlineModel
 * @Title:
 * @Description: 获取频道列表
 * @Package com.xu.topnews.module.headlines.model.imodel
 * @date 2017/9/6 11:23
 */

public interface INewsHeadlineModel {
    /**
     *
     * @param listner
     */
    void loadChannelList(OnLoadDataListener listner);

}
