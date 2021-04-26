package com.lee.smartcommunity.net;

import androidx.lifecycle.LiveData;

import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.model.GetPropertyResult;
import com.lee.smartcommunity.model.GetRepairResult;
import com.lee.smartcommunity.model.GetShopGoodsCategoryResult;
import com.lee.smartcommunity.model.GetShopGoodsResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 网络请求
 *
 * @author Lee
 */
public interface AppService {
    @POST(AppUrl.GET_NEWS)
    LiveData<GetNewsResult> getNews(@Body RequestBody body);

    @POST(AppUrl.GET_REPAIR_LIST)
    LiveData<GetRepairResult> getRepair(@Body RequestBody body);

    @POST(AppUrl.GET_PROPERTY_PAYMENT)
    LiveData<GetPropertyResult> getPropertyPayment(@Body RequestBody body);

    @FormUrlEncoded
    @POST(AppUrl.GET_SHOP_GOODS_CATEGORY)
    LiveData<GetShopGoodsCategoryResult> getShopGoodsCategory(@Field("storeId") int storeId);

    @FormUrlEncoded
    @POST(AppUrl.GET_SHOP_GOODS_LIST_BY_ID)
    LiveData<GetShopGoodsResult> getShopGoodsListById(@Field("sortId") int sortId);
}
