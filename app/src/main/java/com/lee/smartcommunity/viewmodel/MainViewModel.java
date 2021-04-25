package com.lee.smartcommunity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.lee.retrofit.livedata.SingleSourceLiveData;
import com.lee.retrofit.model.Resource;
import com.lee.smartcommunity.model.AnnouncementResult;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.smartcommunity.net.AppTask;

/**
 * @author Lee
 */
public class MainViewModel extends BaseViewModel {

    private final AppTask appTask;
    private final SingleSourceLiveData<Resource<AnnouncementResult>> announcementResult = new SingleSourceLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        appTask = new AppTask(application);
    }

    public void getAnnouncement(int villageId) {
        announcementResult.setSource(appTask.getAnnouncement(villageId));
    }

    public SingleSourceLiveData<Resource<AnnouncementResult>> getAnnouncementResult() {
        return announcementResult;
    }
}
