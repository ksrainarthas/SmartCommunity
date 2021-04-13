package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.app.AppManager;
import com.lee.smartcommunity.databinding.ActivityMainBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * @author Lee
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void initView() {

    }

    @Override
    public void onBackPressed() {
        AppManager.getAppManager().appExit();
        super.onBackPressed();
    }
}