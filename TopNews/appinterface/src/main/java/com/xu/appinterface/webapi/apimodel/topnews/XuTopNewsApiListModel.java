package com.xu.appinterface.webapi.apimodel.topnews;

import com.xu.appinterface.webapi.apimodel.base.XuBaseWebApiModel;

import java.util.List;

/**
 * Created by 16413 on 2017/5/17.
 */
public class XuTopNewsApiListModel extends XuBaseWebApiModel {

    /**
     * postid": "PHOT24QJ7000100A",
     "hasCover": false,
     "hasHead": 1,
     "replyCount": 5155,
     "hasImg": 1,
     "digest": "",
     "hasIcon": false,
     "docid": "9IG74V5H00963VRO_CKGIASD4caoruiupdateDoc",
     "title": "彭丽媛邀请各国领导人夫人参观故宫",
     "order": 1,
     "priority": 354,
     "lmodify": "2017-05-15 19:56:48",
     "boardid": "photoview_bbs",
     */

    /**
     * photosetID": "00AN0001|2255463",
     "imgsum": 6,
     "topic_background": "http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348646712614.jpg",
     "template": "normal1",
     "votecount": 3725,
     "skipID": "00AN0001|2255463",
     "alias": "Top News",
     "skipType": "photoset",
     "cid": "C1348646712614",
     "hasAD": 1,
     */
    /**
     *  "source": "新华视点",
     "ename": "androidnews",
     "tname": "头条",
     "imgsrc": "http://cms-bucket.nosdn.127.net/c0d95c5962014ba583d95eab0251183e20170515192905.jpeg",
     "ptime": "2017-05-15 19:29:58"
     */
    public String postid;
    public boolean hasCover;
    public int hasHead;
    public int replyCount;
    public int hasImg;
    public String digest;
    public boolean hasIcon;
    public String docid;
    public String title;
    public int order;
    public int priority;
    public String lmodify;
    public String boardid;
    public String photosetID;
    public String template;
    public int votecount;
    public String skipID;
    public String alias;
    public String skipType;
    public String cid;
    public int hasAD;
    public String imgsrc;
    public String tname;
    public String ename;
    public String ptime;
    public String source;
    /**
     * title : 哈萨克斯坦中亚在建第1高楼爆炸起火
     * tag : photoset
     * imgsrc : http://img5.cache.netease.com/3g/2016/2/13/2016021318005710210.jpg
     * subtitle :
     * url : 00AN0001|110630
     */

    public List<XuTopNewsApiAdsModel> ads;
    /**
     * imgsrc : http://img5.cache.netease.com/3g/2016/2/13/201602131446132dc50.jpg
     */

    public List<XuTopNewsApiImgextraModel> imgextra;
    /**
     * 是否阅读过
     */
    public boolean whetherRead;


}
