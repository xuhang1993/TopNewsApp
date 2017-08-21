package com.xu.topnews.app;

import android.support.multidex.MultiDexApplication;

import com.xu.appcommonutils.util.FileUtils;
import com.xu.appcommonutils.util.Utils;
import com.xu.appframwork.configer.XuBaseFramWorkConfig;
import com.xu.appframwork.framworkenum.XuBaseFramWorkEnum;
import com.xu.appframwork.resourcedefinition.XuBaseResourcedefinition;

/**
 * Created by 16413 on 2017/5/11.
 */
public class Application extends MultiDexApplication {

    private static final String APIHOST = "c.m.163.com";

    @Override
    public void onCreate() {
        super.onCreate();
        setConfig();
    }

    private void setConfig(){
        //配置host
        XuBaseFramWorkConfig.getInstance().setApiTheme(XuBaseFramWorkEnum.HttpType.HTTP);
        XuBaseFramWorkConfig.getInstance().setApiHost(APIHOST);
        //创建文件夹
        FileUtils.createOrExistsDir(XuBaseResourcedefinition.appFilePath());
        //初始化util
        Utils.init(getApplicationContext());
    }

}
