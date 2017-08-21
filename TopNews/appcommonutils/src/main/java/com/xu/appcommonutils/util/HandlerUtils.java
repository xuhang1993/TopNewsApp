package com.xu.appcommonutils.util;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: HandlerUtils
 * @Title:
 * @Description: Handler相关工具类
 * @Package com.xu.appcommonutils.util
 * @date 2017/5/18 14:06
 */
public final class HandlerUtils {

    private HandlerUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static class HandlerHolder extends Handler {
        WeakReference<Handler.Callback> mListenerWeakReference;

        /**
         * 使用必读：推荐在Activity或者Activity内部持有类中实现该接口，不要使用匿名类，可能会被GC
         *
         * @param listener 收到消息回调接口
         */
        public HandlerHolder(Handler.Callback listener) {
            mListenerWeakReference = new WeakReference<>(listener);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mListenerWeakReference != null && mListenerWeakReference.get() != null) {
                mListenerWeakReference.get().handleMessage(msg);
            }
        }
    }
}
