package com.lee.smartcommunity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.lee.retrofit.livedata.SingleSourceLiveData;
import com.lee.retrofit.model.Resource;
import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.model.GetRepairResult;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.smartcommunity.net.AppTask;

/**
 * @author Lee
 */
public class AppViewModel extends BaseViewModel {

    private final AppTask appTask;
    private final SingleSourceLiveData<Resource<GetNewsResult>> getNewsResult = new SingleSourceLiveData<>();
    private final SingleSourceLiveData<Resource<GetRepairResult>> getRepairResult = new SingleSourceLiveData<>();

    public AppViewModel(@NonNull Application application) {
        super(application);
        appTask = new AppTask(application);
    }

    public void getNews(int villageId) {
        getNewsResult.setSource(appTask.getNews(villageId));
    }

    public SingleSourceLiveData<Resource<GetNewsResult>> getNewsResult() {
        return getNewsResult;
    }

    public void getRepair(int villageId, int bindId) {
        getRepairResult.setSource(appTask.getRepair(villageId, bindId));
    }

    public SingleSourceLiveData<Resource<GetRepairResult>> getRepairResult() {
        return getRepairResult;
    }
}
