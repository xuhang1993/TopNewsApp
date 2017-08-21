package com.xu.appframwork.resourcedefinition;

import android.os.Environment;

/**
 * 资源路径定义
 */

public class XuBaseResourcedefinition {

    //APPNAME
    private static String APPNAME = "TopNews";

    public static String getAPPNAME() {
        return APPNAME;
    }

    public static void setAPPNAME(String APPNAME) {
        XuBaseResourcedefinition.APPNAME = APPNAME;
    }

    /**
     * 设置app文件路径
     * @return
     */
    public static String appFilePath(){
        String path = String.format("%1$s/%2$s", Environment.getExternalStorageDirectory().getPath(),getAPPNAME());
        return path;
    }

    /**
     * 视频资源路径
     * @return
     */
    public static String appFileVideoPath(){
        String path = String.format("%1$s/%2$s", appFilePath(),"video");
        return path;
    }

    /**
     * 图片资源路径
     * @return
     */
    public static String appFileImagePath(){
        String path = String.format("%1$s/%2$s", appFilePath(),"image");
        return path;
    }

    /**
     * 缓存路径
     * @return
     */
    public static String appFileCachePath(){
        String path = String.format("%1$s/%2$s", appFilePath(),"cache");
        return path;
    }

}
