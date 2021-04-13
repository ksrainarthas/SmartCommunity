package com.lee.smartcommunity.net;

import android.content.Context;

import com.lee.retrofit.adapter.LiveDataCallAdapterFactory;
import com.lee.retrofit.client.RetrofitClient;
import com.lee.retrofit.model.OkHttpConfig;

import okhttp3.OkHttpClient;

/**
 * 用户相关业务处理
 */
public class AppTask {
    private final AppService appService;
    private final Context mContext;

    public AppTask(Context context) {
        mContext = context;
        appService = RetrofitClient.getInstance().setBaseUrl(AppUrl.BASE_URL)
                .setCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .setOkHttpClient(createOkHttp())
                .createService(AppService.class);
    }

    private OkHttpClient createOkHttp() {
        return new OkHttpConfig.Builder(mContext).build();
    }
}
