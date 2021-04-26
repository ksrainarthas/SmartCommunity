package com.lee.smartcommunity.net;

import androidx.lifecycle.LiveData;

import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.model.GetRepairResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
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
}
