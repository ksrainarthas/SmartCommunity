package com.lee.smartcommunity.ui.activity;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.databinding.ActivityServiceOrderBinding;
import com.lee.smartcommunity.mvvm.BaseActivity;
import com.lee.smartcommunity.viewmodel.AppViewModel;

/**
 * 服务下单
 * 文件名: ServiceOrderActivity
 * 创建者: WangYu
 * 创建日期: 2021/4/13 11:40
 */
public class ServiceOrderActivity extends BaseActivity<ActivityServiceOrderBinding, AppViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_order;
    }

    @Override
    protected boolean isContainToolBar() {
        return false;
    }

    @Override
    protected void initView() {
        viewBinding.tvBack.setOnClickListener(v -> finish());
    }
}