package com.xu.appthird.setting;

import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by 16413 on 2017/9/5.
 */

public class XuBaseThirdSetting {

    private static XuBaseThirdSetting mXuBaseThirdSetting = null;

    /**
     * 单例
     */
    public static XuBaseThirdSetting getInstance() {
        if (mXuBaseThirdSetting == null) {
            synchronized (XuBaseThirdSetting.class) {
                mXuBaseThirdSetting = new XuBaseThirdSetting();
            }
        }
        return mXuBaseThirdSetting;
    }

    /**
     * 第三方设置
     * @param context
     * @param appId
     */
    public void thirdSetting(Context context, String appId){
        //初始化Bomb
        Bmob.initialize(context, appId);
    }


}
