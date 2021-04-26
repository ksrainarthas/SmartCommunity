package com.lee.smartcommunity.net;

/**
 * 网络请求接口
 *
 * @author Lee
 */
public class AppUrl {

    //全局域名 必须以"/"结尾
    //public static final String BASE_URL = "http://we7.ljyjy.me/";

    //测试地址
    public static final String BASE_URL = "http://www.ljyjy.ink/";

    //获取通知公告
    public static final String GET_NEWS = "api.php?g=Api&c=House&a=getNews";

    //获取小区报修列表
    public static final String GET_REPAIR_LIST = "api.php?g=Api&c=House&a=getRepair";

    //物业缴费
    public static final String GET_PROPERTY_PAYMENT = "api.php?g=Api&c=House&a=getProperty";

    //获取快店 分类ID、分类名称、子分类ID、子分类名称
    public static final String GET_SHOP_GOODS_CATEGORY = "api.php?g=Api&c=ShopGoods&a=getShopGoodsCategory";

    //根据 分类ID 获取商品列表
    public static final String GET_SHOP_GOODS_LIST_BY_ID = "api.php?g=Api&c=ShopGoods&a=getShopGoods";

    //生成订单
    public static final String CREATE_ORDER = "api.php?g=Api&c=ShopGoods&a=createOrder";

    //更新订单
    public static final String UPDATE_ORDER = "api.php?g=Api&c=ShopGoods&a=updateOrderInfo";

    //获取商品分类
    public static final String GET_GOODS_CATEGORY = "api/index.php?c=goods&a=Goods&do=getGoodsCategory";

    //根据ID获取商品
    public static final String GET_GOODS_BY_ID = "api/index.php?c=goods&a=Goods&do=getGoodsList";

    //生成订单信息
    public static final String CREATE_ORDER_INFO = "api/index.php?c=goods&a=Goods&do=createOrder";

    //生成订单详情
    public static final String CREATE_ORDER_DETAIL = "api/index.php?c=goods&a=Goods&do=createOrderDetail";

    //更新订单状态
    public static final String UPDATE_ORDER_STATUS = "api/index.php?c=goods&a=Goods&do=updateOrderInfo";
}
