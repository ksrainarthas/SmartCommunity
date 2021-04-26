package com.lee.smartcommunity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.lee.retrofit.livedata.SingleSourceLiveData;
import com.lee.retrofit.model.Resource;
import com.lee.smartcommunity.model.GetNewsResult;
import com.lee.smartcommunity.model.GetPropertyResult;
import com.lee.smartcommunity.model.GetRepairResult;
import com.lee.smartcommunity.model.GetShopGoodsCategoryResult;
import com.lee.smartcommunity.model.GetShopGoodsResult;
import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.smartcommunity.net.AppTask;

/**
 * @author Lee
 */
public class AppViewModel extends BaseViewModel {

    private final AppTask appTask;
    private final SingleSourceLiveData<Resource<GetNewsResult>> getNewsResult = new SingleSourceLiveData<>();
    private final SingleSourceLiveData<Resource<GetRepairResult>> getRepairResult = new SingleSourceLiveData<>();
    private final SingleSourceLiveData<Resource<GetPropertyResult>> getPropertyResult = new SingleSourceLiveData<>();
    private final SingleSourceLiveData<Resource<GetShopGoodsCategoryResult>> getShopGoodsCategoryResult = new SingleSourceLiveData<>();
    private final SingleSourceLiveData<Resource<GetShopGoodsResult>> getShopGoodsResult = new SingleSourceLiveData<>();

    public AppViewModel(@NonNull Application application) {
        super(application);
        appTask = new AppTask(application);
    }

    public void getNews(String villageId) {
        getNewsResult.setSource(appTask.getNews(villageId));
    }

    public SingleSourceLiveData<Resource<GetNewsResult>> getNewsResult() {
        return getNewsResult;
    }

    public void getRepair(String villageId, String bindId) {
        getRepairResult.setSource(appTask.getRepair(villageId, bindId));
    }

    public SingleSourceLiveData<Resource<GetRepairResult>> getRepairResult() {
        return getRepairResult;
    }

    public void getPropertyPayment(int villageId, int bindId) {
        getPropertyResult.setSource(appTask.getPropertyPayment(villageId, bindId));
    }

    public SingleSourceLiveData<Resource<GetPropertyResult>> getPropertyPaymentResult() {
        return getPropertyResult;
    }

    public void getShopGoodsCategory(int storeId) {
        getShopGoodsCategoryResult.setSource(appTask.getShopGoodsCategory(storeId));
    }

    public SingleSourceLiveData<Resource<GetShopGoodsCategoryResult>> getShopGoodsCategoryResult() {
        return getShopGoodsCategoryResult;
    }

    public void getShopGoodsListById(int sortId) {
        getShopGoodsResult.setSource(appTask.getShopGoodsListById(sortId));
    }

    public SingleSourceLiveData<Resource<GetShopGoodsResult>> getShopGoodsListByIdResult() {
        return getShopGoodsResult;
    }
}
