package com.xu.appinterface.webapi.apimodel;

import com.xu.appinterface.webapi.apimodel.base.XuBaseWebApiModel;

/**
 * Created by 16413 on 2017/4/26.
 */
public class XuBaseApiModel extends XuBaseWebApiModel {

    /**
     * 课程是否已结束
     */
    public boolean isEnded;
    /**
     * 名字
     */
    public String name;
    /**
     * 课程Id
     */
    public long courseId;
    /**
     * 是否是开课中
     */
    public boolean isOpenCourse;
    /**
     * 图片
     */
    public String cover;
    /**
     * 课程编号
     */
    public String serialNumber;
    /**
     * 报名开始时间
     */
    public String applyStartTime;
    /**
     * 报名结束时间
     */
    public String applyEndTime;
    /**
     * 课程开课开始时间
     */
    public String courseStartTime;
    /**
     * 课程开课结束时间
     */
    public String courseEndTime;

    /**
     *课程类型
     */
    public int courseType;
    /**
     * 课程描述
     */
    public String description;

    /**
     * 课程类型
     */
    public int courseState;
    /**
     * 学校名称
     */
    public String institutionName;
    /**
     * 创建课程的用户信息
     */
//    public UserInfo_ApiDto  user;
    /**
     * 课程内的学生数量
     */
    public int stuCount;
    /**
     * 课程价格
     */
    public double price;
    /**
     * 带批准数量
     */
    public int unApproveCount;
    /**
     * 带回答数量
     */
    public int unAnswerCount;
    /**
     * 带评判数量
     */
    public int unJudgeCount;
    /**
     * 测试数量
     */
    public int testCount;
    /**
     * 通知数量
     */
    public int noticeCount;
    /**
     * 课程资源包Id
     */
    public long SubjectCourseId;

}
