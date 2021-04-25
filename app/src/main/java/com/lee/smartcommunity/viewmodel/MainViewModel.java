package com.lee.smartcommunity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.lee.retrofit.livedata.SingleSourceLiveData;
import com.lee.retrofit.model.Resource;
import com.lee.smartcommunity.model.AnnouncementResult;
import com.lee.smartcommunity.model.AreaListResult;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.smartcommunity.net.AppTask;

/**
 * @author Lee
 */
public class MainViewModel extends BaseViewModel {

    private final AppTask appTask;
    private final SingleSourceLiveData<Resource<AnnouncementResult>> announcementResult = new SingleSourceLiveData<>();
    private final SingleSourceLiveData<Resource<AreaListResult>> areaListResult = new SingleSourceLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        appTask = new AppTask(application);
    }

    public void getAnnouncement(int villageId) {
        announcementResult.setSource(appTask.getAnnouncement(villageId));
    }

    public void getAreaList(int villageId, int bindId) {
        areaListResult.setSource(appTask.getAreaList(villageId, bindId));
    }

    public SingleSourceLiveData<Resource<AreaListResult>> getAreaListResult() {
        return areaListResult;
    }
    public SingleSourceLiveData<Resource<AnnouncementResult>> getAnnouncementResult() {
        return announcementResult;
    }
}
