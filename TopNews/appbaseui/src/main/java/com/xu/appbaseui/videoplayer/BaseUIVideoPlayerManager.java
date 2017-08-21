package com.xu.appbaseui.videoplayer;

/**
 * Put JCVideoPlayer into layout
 * From a JCVideoPlayer to another JCVideoPlayer
 * Created by Nathen on 16/7/26.
 */
public class BaseUIVideoPlayerManager {

    public static BaseUIVideoPlayer FIRST_FLOOR_JCVD;
    public static BaseUIVideoPlayer SECOND_FLOOR_JCVD;

    public static void setFirstFloor(BaseUIVideoPlayer baseUIVideoPlayer) {
        FIRST_FLOOR_JCVD = baseUIVideoPlayer;
    }

    public static void setSecondFloor(BaseUIVideoPlayer baseUIVideoPlayer) {
        SECOND_FLOOR_JCVD = baseUIVideoPlayer;
    }

    public static BaseUIVideoPlayer getFirstFloor() {
        return FIRST_FLOOR_JCVD;
    }

    public static BaseUIVideoPlayer getSecondFloor() {
        return SECOND_FLOOR_JCVD;
    }

    public static BaseUIVideoPlayer getCurrentJcvd() {
        if (getSecondFloor() != null) {
            return getSecondFloor();
        }
        return getFirstFloor();
    }

    public static void completeAll() {
        if (SECOND_FLOOR_JCVD != null) {
            SECOND_FLOOR_JCVD.onCompletion();
            SECOND_FLOOR_JCVD = null;
        }
        if (FIRST_FLOOR_JCVD != null) {
            FIRST_FLOOR_JCVD.onCompletion();
            FIRST_FLOOR_JCVD = null;
        }
    }
}
