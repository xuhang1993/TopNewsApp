package com.xu.appframwork.configer;

import com.xu.appframwork.framworkenum.XuBaseFramWorkEnum;

/**
 * Created by 16413 on 2017/4/26.
 */
public class XuBaseFramWorkConfig {
    /**
     * apibaseurl
     */
    private String apiHost;

    /**
     * api theme
     */

    private String apiTheme;

    /**
     * baseurl
     */

    private String baseUrl;

    private static volatile  XuBaseFramWorkConfig framWorkConfig = null;

    public static XuBaseFramWorkConfig getInstance(){
        if (framWorkConfig == null){
            synchronized (XuBaseFramWorkConfig.class){
                if (framWorkConfig == null){
                    framWorkConfig = new XuBaseFramWorkConfig();
                }
            }
        }
        return framWorkConfig;
    }

    /**
     * 获取host
     * @return
     */
    public String getApiHost() {
        return apiHost;
    }

    /**
     * 设置host
     * @param apiHost
     */
    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    /**
     * 获取theme
     * @return
     */
    public String getApiTheme() {
        return apiTheme;
    }

    /**
     * 设置theme
     * @param httpType
     */
    public void setApiTheme(XuBaseFramWorkEnum.HttpType httpType) {
        switch (httpType.getValue()) {
            case 0:
                apiTheme = "http://";
                break;
            case 1:
                apiTheme = "https://";
                break;
            default:
                apiTheme = "http://";
                break;

        }
    }

    /**
     * 返回baseUrl
     * @return
     */
    public String getBaseUrl() {
        return apiTheme + apiHost;
    }
}
