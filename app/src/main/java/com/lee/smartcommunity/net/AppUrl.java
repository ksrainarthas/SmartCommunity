package com.lee.smartcommunity.net;

/**
 * 网络请求接口
 *
 * @author Lee
 */
public class AppUrl {

    //全局域名 必须以"/"结尾
//    public static final String BASE_URL = "http://we7.ljyjy.me/";

    //测试地址
    public static final String BASE_URL = "http://www.ljyjy.ink/";

    //获取通知公告
    public static final String GET_ANNOUNCEMENT = "api.php?g=Api&c=House&a=getNews";

    //获取小区报修列表
    public static final String GET_AREA_LIST = "api.php?g=Api&c=House&a=getRepair";
}
