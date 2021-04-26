package com.lee.smartcommunity.net;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.lee.retrofit.client.RetrofitClient;
import com.lee.retrofit.model.Resource;
import com.lee.retrofit.network.NetworkOnlyResource;
import com.lee.retrofit.utils.RetrofitUtils;
import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.model.GetPropertyResult;
import com.lee.smartcommunity.model.GetRepairResult;
import com.lee.smartcommunity.model.GetShopGoodsCategoryResult;
import com.lee.smartcommunity.model.GetShopGoodsResult;

import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * 用户相关业务处理
 */
public class AppTask {
    private final AppService appService;
    private final Context context;

    public AppTask(Context context) {
        this.context = context;
        appService = RetrofitClient.getInstance().setBaseUrl(AppUrl.BASE_URL).createService(AppService.class);
    }

    public LiveData<Resource<GetNewsResult>> getNews(String villageId) {
        return new NetworkOnlyResource<GetNewsResult, GetNewsResult>() {

            @Override
            protected LiveData<GetNewsResult> createCall() {
                HashMap<String, Object> body = new HashMap<>();
                body.put("villageId", villageId);
                RequestBody requestBody = RetrofitUtils.createJsonRequestBody(body);
                return appService.getNews(requestBody);
            }

            @Override
            protected GetNewsResult transformRequestType(GetNewsResult getNewsResult) {
                return getNewsResult;
            }
        }.asLiveData();
    }

    public LiveData<Resource<GetRepairResult>> getRepair(String villageId, String bindId) {
        return new NetworkOnlyResource<GetRepairResult, GetRepairResult>() {

            @Override
            protected LiveData<GetRepairResult> createCall() {
                HashMap<String, Object> body = new HashMap<>();
                body.put("villageId", villageId);
                body.put("bindId", bindId);
                RequestBody requestBody = RetrofitUtils.createJsonRequestBody(body);
                return appService.getRepair(requestBody);
            }

            @Override
            protected GetRepairResult transformRequestType(GetRepairResult getRepairResult) {
                return getRepairResult;
            }
        }.asLiveData();
    }

    public LiveData<Resource<GetPropertyResult>> getPropertyPayment(int villageId, int bindId) {
        return new NetworkOnlyResource<GetPropertyResult, GetPropertyResult>() {

            @Override
            protected LiveData<GetPropertyResult> createCall() {
                HashMap<String, Object> body = new HashMap<>();
                body.put("villageId", villageId);
                body.put("bindId", bindId);
                RequestBody requestBody = RetrofitUtils.createJsonRequestBody(body);
                return appService.getPropertyPayment(requestBody);
            }

            @Override
            protected GetPropertyResult transformRequestType(GetPropertyResult getPropertyResult) {
                return getPropertyResult;
            }
        }.asLiveData();
    }

    public LiveData<Resource<GetShopGoodsCategoryResult>> getShopGoodsCategory(int storeId) {
        return new NetworkOnlyResource<GetShopGoodsCategoryResult, GetShopGoodsCategoryResult>() {

            @Override
            protected LiveData<GetShopGoodsCategoryResult> createCall() {
                return appService.getShopGoodsCategory(storeId);
            }

            @Override
            protected GetShopGoodsCategoryResult transformRequestType(GetShopGoodsCategoryResult getShopGoodsCategoryResult) {
                return getShopGoodsCategoryResult;
            }
        }.asLiveData();
    }

    public LiveData<Resource<GetShopGoodsResult>> getShopGoodsListById(int sortId) {
        return new NetworkOnlyResource<GetShopGoodsResult, GetShopGoodsResult>() {

            @Override
            protected LiveData<GetShopGoodsResult> createCall() {
                return appService.getShopGoodsListById(sortId);
            }

            @Override
            protected GetShopGoodsResult transformRequestType(GetShopGoodsResult getShopGoodsResult) {
                return getShopGoodsResult;
            }
        }.asLiveData();
    }
}
