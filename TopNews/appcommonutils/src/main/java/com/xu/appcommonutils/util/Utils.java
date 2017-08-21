package com.xu.appcommonutils.util;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: Utils
 * @Title:
 * @Description: Utils初始化相关
 * @Package com.xu.appcommonutils.util
 * @date 2017/5/18 14:12
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}