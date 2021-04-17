package com.lee.smartcommunity.net;

import androidx.lifecycle.LiveData;

import com.lee.smartcommunity.model.AnnouncementResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 网络请求
 *
 * @author Lee
 */
public interface AppService {
    @POST(AppUrl.GET_ANNOUNCEMENT)
    LiveData<AnnouncementResult> getAnnouncement(@Query("c") String c, @Query("g") String g, @Query("a") String a, @Body RequestBody body);
}
