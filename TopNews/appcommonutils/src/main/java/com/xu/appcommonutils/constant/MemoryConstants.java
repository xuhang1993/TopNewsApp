package com.xu.appcommonutils.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: MemoryConstants
 * @Title:
 * @Description: 存储相关常量
 * @Package com.xu.appcommonutils.constant
 * @date 2017/5/18 13:47
 */

public final class MemoryConstants {

    /**
     * Byte与Byte的倍数
     */
    public static final int BYTE = 1;
    /**
     * KB与Byte的倍数
     */
    public static final int KB   = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB   = 1048576;
    /**
     * GB与Byte的倍数
     */
    public static final int GB   = 1073741824;

    @IntDef({BYTE, KB, MB, GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
