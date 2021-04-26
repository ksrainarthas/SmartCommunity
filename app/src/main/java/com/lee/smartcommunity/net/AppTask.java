package com.lee.smartcommunity.net;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.lee.retrofit.client.RetrofitClient;
import com.lee.retrofit.model.Resource;
import com.lee.retrofit.network.NetworkOnlyResource;
import com.lee.retrofit.utils.RetrofitUtils;
import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.model.GetRepairResult;

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

    public LiveData<Resource<GetNewsResult>> getNews(int villageId) {
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

    public LiveData<Resource<GetRepairResult>> getRepair(int villageId, int bindId) {
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
}
