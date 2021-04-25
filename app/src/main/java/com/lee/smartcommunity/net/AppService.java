package com.lee.smartcommunity.net;

import androidx.lifecycle.LiveData;

import com.lee.smartcommunity.model.AnnouncementResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求
 *
 * @author Lee
 */
public interface AppService {
    @POST(AppUrl.GET_ANNOUNCEMENT)
    LiveData<AnnouncementResult> getAnnouncement(@Body RequestBody body);
}
