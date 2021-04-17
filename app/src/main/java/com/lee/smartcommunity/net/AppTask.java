package com.lee.smartcommunity.net;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.lee.retrofit.adapter.LiveDataCallAdapterFactory;
import com.lee.retrofit.client.RetrofitClient;
import com.lee.retrofit.model.OkHttpConfig;
import com.lee.retrofit.model.Resource;
import com.lee.retrofit.network.NetworkOnlyResource;
import com.lee.retrofit.utils.RetrofitUtils;
import com.lee.smartcommunity.model.AnnouncementResult;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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

    public LiveData<Resource<AnnouncementResult>> getAnnouncement(int villageId) {
        return new NetworkOnlyResource<AnnouncementResult, AnnouncementResult>() {

            @Override
            protected LiveData<AnnouncementResult> createCall() {
                HashMap<String, Object> body = new HashMap<>();
                body.put("villageId", villageId);
                RequestBody requestBody = RetrofitUtils.createJsonRequestBody(body);
                return appService.getAnnouncement("Api", "House", "getNews", requestBody);
            }

            @Override
            protected AnnouncementResult transformRequestType(AnnouncementResult announcementResult) {
                return announcementResult;
            }
        }.asLiveData();
    }

    private OkHttpClient createOkHttp() {
        return new OkHttpConfig.Builder(mContext).build();
    }
}
