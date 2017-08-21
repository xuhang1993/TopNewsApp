package com.xu.appframwork.callback;

/**
 * frambaseCallBack
 */
public interface XuBaseFramWorkCallBack <T>{
    /**
     * 回调成功方法
     */
    void loadSuccess(T data);

    /**
     * 回调失败方法
     */
   void loadFailure(String string);

    /**
     * 回调之前
     */
    void loadBefore();
    /**
     * 回调成功后
     */
    void loadComplete();

}
