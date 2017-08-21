package com.xu.appframwork.framworkenum;

/**
 * Created by 16413 on 2017/4/26.
 */
public class XuBaseFramWorkEnum {
    /**
     * 服务器类型
     */
    public enum HttpType implements XuBaseEnum<Integer>{
        /**
         * Http
         */
        HTTP(0),
        /**
         * Https
         */
        HTTPS(1);

        private final int value;

        private HttpType(int value) {
            this.value = value;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        public static HttpType findByValue(int value){
            HttpType httpType = null;
            switch (value){
                case 0:
                    httpType = HTTP;
                    break;
                case 1:
                    httpType = HTTPS;
                    break;
                default:
                    httpType = HTTP;
                    break;
            }
            return httpType;
        }

    }


}
