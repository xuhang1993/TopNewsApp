package com.xu.appframwork.global;

import android.content.Context;

import com.xu.appcommonutils.util.FileUtils;
import com.xu.appcommonutils.util.Utils;
import com.xu.appframwork.configer.XuBaseFramWorkConfig;
import com.xu.appframwork.framworkenum.XuBaseFramWorkEnum;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseGlobalConfiguration
 * @Title:
 * @Description: 全局配置信息
 * @Package com.xu.appframwork.global
 * @date 2017/9/4 17:17
 */

public class XuBaseGlobalConfiguration {

    private static XuBaseGlobalConfiguration mXuBaseGlobalConfiguration = null;

    /**
     * 单例
     */
    public static XuBaseGlobalConfiguration getInstance() {
        if (mXuBaseGlobalConfiguration == null) {
            synchronized (XuBaseGlobalConfiguration.class) {
                mXuBaseGlobalConfiguration = new XuBaseGlobalConfiguration();
            }
        }
        return mXuBaseGlobalConfiguration;
    }

    /**
     * 配置全局信息
     * @param type
     * @param host
     * @param path
     * @param context
     */
    public void configurationThings(XuBaseFramWorkEnum.HttpType type, String host, String path, Context context){
        //配置host
        XuBaseFramWorkConfig.getInstance().setApiTheme(type);
        XuBaseFramWorkConfig.getInstance().setApiHost(host);
        //初始化util
        Utils.init(context);
        //创建文件夹
        FileUtils.createOrExistsDir(path);
    }
}
