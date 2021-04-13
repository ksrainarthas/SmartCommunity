package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityHealthManageBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.MainViewModel;

/**
 * 健康管理
 * 文件名: HealthManageActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:33
 */
public class HealthManageActivity extends BaseActivity<ActivityHealthManageBinding, MainViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_manage;
    }

    @Override
    protected void initView() {

    }
}