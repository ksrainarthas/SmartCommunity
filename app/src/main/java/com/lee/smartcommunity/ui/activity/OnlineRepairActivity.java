package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityOnlineRepairBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 在线报修
 *
 * @author Lee
 */
public class OnlineRepairActivity extends BaseActivity<ActivityOnlineRepairBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_repair;
    }

    @Override
    protected String setTitle() {
        return getString(R.string.online_repair);
    }

    @Override
    protected void initView() {

    }
}