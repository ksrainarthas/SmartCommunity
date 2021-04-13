package com.lee.smartcommunity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.lee.smartcommunity.mvvm.BaseViewModel;
import com.lee.smartcommunity.net.AppTask;

/**
 * @author Lee
 */
public class MainViewModel extends BaseViewModel {

    private final AppTask appTask;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appTask = new AppTask(application);
    }
}
