package com.xu.topnews.app;

import android.support.multidex.MultiDexApplication;

import com.xu.appframwork.framworkenum.XuBaseFramWorkEnum;
import com.xu.appframwork.global.XuBaseGlobalConfiguration;
import com.xu.appframwork.resourcedefinition.XuBaseResourcedefinition;

/**
 * Created by 16413 on 2017/5/11.
 */
public class Application extends MultiDexApplication {
    //apihost
    private static final String APIHOST = "c.m.163.com";
    //BombAppId
    private static final String APPID = "be3d90b9868805f850571e7a6a2dee52";

    @Override
    public void onCreate() {
        super.onCreate();
        setConfig();
    }

    private void setConfig(){
        XuBaseGlobalConfiguration.getInstance().configurationThings(XuBaseFramWorkEnum.HttpType.HTTP, APIHOST, XuBaseResourcedefinition.appFilePath(), getApplicationContext());
//        XuBaseThirdSetting.getInstance().thirdSetting(getApplicationContext(), APPID);
    }

}
